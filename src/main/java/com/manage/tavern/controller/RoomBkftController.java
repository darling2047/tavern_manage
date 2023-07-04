package com.manage.tavern.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.listener.RoomBkftListener;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.excel.RoomBkftExcel;
import com.manage.tavern.model.excel.RoomBkftExcelModel;
import com.manage.tavern.model.query.BkftRoomQuery;
import com.manage.tavern.model.vo.RoomBkftVo;
import com.manage.tavern.po.TavernRoomBkft;
import com.manage.tavern.service.RoomBkftService;
import com.manage.tavern.service.TavernExcelService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DLL
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/roomBkft")
public class RoomBkftController {


    @Resource
    private RoomBkftService roomBkftService;

    @Resource
    private TavernExcelService tavernExcelService;

    @SysLog("不可分摊费用列表查询")
    @GetMapping(value = "/getList")
    public PaginationModel<RoomBkftVo> list(BkftRoomQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<RoomBkftVo> res = roomBkftService.getList(query);
        return res;
    }


    @SysLog("不可分摊费用新增")
    @PostMapping(value = "/create")
    public ResponResult<Object> create(RoomBkftVo params) {
        TavernRoomBkft tavernRoomBkft = BeanCopierUtils.convert(params, TavernRoomBkft.class);
        tavernRoomBkft.setCreator(TokenUtil.getUser().getLoginName());
        roomBkftService.save(tavernRoomBkft);
        return ResponResult.success();
    }

    @SysLog("不可分摊费用删除")
    @PostMapping(value = "/delete")
    public ResponResult<Object> delete(Long id) {
        roomBkftService.removeById(id);
        return ResponResult.success();
    }

    @SysLog("不可分摊费用修改")
    @PostMapping(value = "/update")
    public ResponResult<Object> update(RoomBkftVo params) {
        TavernRoomBkft tavernRoomBkft = BeanCopierUtils.convert(params, TavernRoomBkft.class);
        roomBkftService.updateById(tavernRoomBkft);
        return ResponResult.success();
    }

    @SysLog("不可分摊列表下载")
    @GetMapping("/downLoad")
    public void downLoad(BkftRoomQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<RoomBkftVo> res = roomBkftService.getList(query);
        List<RoomBkftVo> data = res.getData();
        List<RoomBkftExcel> excels = BeanCopierUtils.convertList(data, RoomBkftExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+query.getMonth()+"不可分摊列表", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "不可分摊列表").head(RoomBkftExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }

    @SysLog("不可分摊模板下载")
    @GetMapping("/templateExport")
    public void templateExport(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode("不可分摊费用导入模板", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename="+encodeName+".xlsx");
        RoomBkftExcelModel excel = new RoomBkftExcelModel();
        List<RoomBkftExcelModel> list = new ArrayList<>();
        list.add(excel);
        EasyExcel.write(response.getOutputStream(), RoomBkftExcelModel.class).sheet("不可分摊费用导入模板").doWrite(list);
    }

    @SysLog(value = "不可分摊费用导入")
    @PostMapping("/bkftImport")
    public ResponResult awardImport(@RequestParam MultipartFile file, Integer awardId) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空!");
        }
        // 解析excel
        RoomBkftListener listener = new RoomBkftListener(tavernExcelService,roomBkftService);
        EasyExcel.read(file.getInputStream(), RoomBkftExcelModel.class, listener).sheet().doRead();
        return ResponResult.success();
    }
}
