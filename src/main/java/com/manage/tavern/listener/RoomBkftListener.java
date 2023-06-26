package com.manage.tavern.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.manage.tavern.constant.BaseExportStatus;
import com.manage.tavern.constant.TavernExportLogEnum;
import com.manage.tavern.model.excel.RoomBkftExcelModel;
import com.manage.tavern.model.form.TavernExportLogForm;
import com.manage.tavern.po.TavernBkftDetailsLog;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.RoomBkftService;
import com.manage.tavern.service.TavernExcelService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.TokenUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author dll
 * @create 2020/12/14 16:54
 * @describe
 */
public class RoomBkftListener extends AnalysisEventListener<RoomBkftExcelModel> {

    private TavernExcelService tavernExcelService;

    private RoomBkftService roomBkftService;

    private ThreadLocal<Long> exportIdLocal = new ThreadLocal<>();

    private ThreadLocal<Integer> tlAwardId = new ThreadLocal<>();

    /**
     * 导入成功数
     */
    private int sucTotal = 0;

    /**
     * 导入失败数
     */
    private int failTotal = 0;


    private List<RoomBkftExcelModel> dataList;

    private List<RoomBkftExcelModel> resList = new ArrayList<>();

    public RoomBkftListener(TavernExcelService service,RoomBkftService roomBkftService){
        this.tavernExcelService = service;
        this.roomBkftService = roomBkftService;
        this.dataList = Lists.newArrayList();
    }



    @Override
    public void invoke(RoomBkftExcelModel data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAfterAllAnalysed(AnalysisContext context) {
        UserInfoModel user = TokenUtil.getUser();
        TavernExportLogForm gridExportLogForm = new TavernExportLogForm();
        gridExportLogForm.setUserId(user.getLoginName());
        Integer code;
        code = TavernExportLogEnum.BKFT_ROOM_EXPORT.getCode();

        gridExportLogForm.setType(code);
        // 初始化导入日志
        Long initId = tavernExcelService.addInit(gridExportLogForm);
        exportIdLocal.set(initId);
        // 校验数据并保存校验通过的数据
        List<TavernBkftDetailsLog> logList = dataList.stream().map(item -> {
            TavernBkftDetailsLog log = BeanCopierUtils.convert(item, TavernBkftDetailsLog.class);
            // 非空检查
            boolean checkData = checkData(item);
            if (checkData) {
                failTotal++;
                log.setStatusDesc(BaseExportStatus.EMTY.getStatusDesc());
                log.setDetailDesc(BaseExportStatus.EMTY.getDetailDesc());
                return log;
            }
            try {
                // 保存导入数据
                roomBkftService.saveBkftFee(item);
                sucTotal++;
                resList.add(item);
                log.setStatusDesc(BaseExportStatus.SUC.getStatusDesc());
                log.setDetailDesc(BaseExportStatus.SUC.getDetailDesc());
            }catch (Exception e) {
                failTotal++;
                log.setStatusDesc(BaseExportStatus.FAIL.getStatusDesc());
                log.setDetailDesc(e.toString());
            }
            return log;
        }).collect(Collectors.toList());
        // 保存导入记录的日志
        roomBkftService.saveExcelDetailLogs(exportIdLocal,logList);
        // 修改导入日志状态
        updateLogStatus(failTotal,sucTotal);
    }


    /**
     * 非空校验
     * @param item
     * @return
     */
    private boolean checkData(RoomBkftExcelModel item) {
        boolean tgfdFlag = StringUtils.isBlank(item.getTgfd());
        boolean amountFlag = StringUtils.isBlank(item.getAmount());
        return tgfdFlag || amountFlag;
    }

    private void updateLogStatus(int failTotal, int sucTotal) {
        TavernExportLogForm gridExportLogForm = new TavernExportLogForm();
        gridExportLogForm.setSucCount(sucTotal);
        gridExportLogForm.setFailCount(failTotal);
        tavernExcelService.addEsuc(exportIdLocal.get(), gridExportLogForm);
    }

}
