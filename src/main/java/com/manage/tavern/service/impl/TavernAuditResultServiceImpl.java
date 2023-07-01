package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.tavern.constant.ExportEnum;
import com.manage.tavern.mapper.RoomAuditMapper;
import com.manage.tavern.mapper.TarvenResultDetailsLogMapper;
import com.manage.tavern.mapper.TavernAuditResultMapper;
import com.manage.tavern.mapper.sys.TavernExportLogMapper;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.excel.TavernAuditResultExcelModel;
import com.manage.tavern.model.form.TavernAuditResultForm;
import com.manage.tavern.model.form.TavernExportLogForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;
import com.manage.tavern.model.vo.TavernAuditResultVo;
import com.manage.tavern.model.vo.TavernOverviewInfo;
import com.manage.tavern.po.TarvenResultDetailsLog;
import com.manage.tavern.po.TavernAuditResult;
import com.manage.tavern.po.sys.TavernExportLog;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.RoomAuditService;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/29 21:48
 * @version:
 * @modified By:
 */
@Service
@Slf4j
public class TavernAuditResultServiceImpl implements TavernAuditResultService {

    @Resource
    private TavernAuditResultMapper tavernAuditResultMapper;

    @Resource
    private RoomAuditService roomAuditService;

    @Resource
    private TavernExportLogMapper tavernExportLogMapper;

    @Resource
    private TarvenResultDetailsLogMapper tarvenResultDetailsLogMapper;

    @Resource
    private RoomAuditMapper roomAuditMapper;


    @Override
    public PaginationModel<TavernAuditResultVo> getList(RoomAuditQuery query) {
        PaginationModel<TavernAuditResultVo> res = new PaginationModel<>();
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        QueryWrapper<TavernAuditResult> qw = getQw(query);
        IPage<TavernAuditResult> ipage = new Page<>(query.getCurrentPageNo(), query.getPageSize());
        IPage<TavernAuditResult> page = tavernAuditResultMapper.selectPage(ipage, qw);
        List<TavernAuditResult> records = page.getRecords();
        List<TavernAuditResultVo> list = BeanCopierUtils.convertList(records, TavernAuditResultVo.class);
        res.setData(list);
        res.setCode("0");
        res.setCount(page.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    @Override
    public void insertResult(String month,String source) {
        RoomAuditQuery query = new RoomAuditQuery();
        query.setPageSize(10000);
        query.setMonth(month);
        query.setFlag(1);
        PaginationModel<RoomAuditInfo> page = roomAuditService.getList(query);
        List<RoomAuditInfo> data = page.getData();
        tavernAuditResultMapper.delete(
                new QueryWrapper<TavernAuditResult>().lambda()
                        .eq(TavernAuditResult::getOpTime,DateUtils.getCurrDate("yyyyMMdd")));
        for (RoomAuditInfo roomAuditInfo : data) {
            TavernAuditResult result = BeanCopierUtils.convert(roomAuditInfo, TavernAuditResult.class);
            result.setMonth(month);
            result.setSource(source);
            result.setOpTime(DateUtils.getCurrDate("yyyyMMdd"));
            tavernAuditResultMapper.insert(result);
        }

    }

    @Override
    public void update(TavernAuditResultForm params) {
        UserInfoModel user = TokenUtil.getUser();
        TavernAuditResult result = BeanCopierUtils.convert(params,TavernAuditResult.class);
        result.setUpdateTime(new Date());
        result.setUpdator(user.getLoginName());
        result.setSource("CONSUMER");
        result.setUpdatorName(user.getUserName());
        tavernAuditResultMapper.updateById(result);
    }

    @Override
    public Long addExcelLog(TavernExportLogForm gridExportLogForm) {
        TavernExportLog gridExportLog = new TavernExportLog();
        BeanUtils.copyProperties(gridExportLogForm, gridExportLog);
        Long id = System.currentTimeMillis();
        gridExportLog.setId(id);
        gridExportLog.setCreateTime(new Date());
        tavernExportLogMapper.insert(gridExportLog);
        return id;
    }

    @Override
    public void updateExportResult(TavernAuditResultExcelModel item) {
        UserInfoModel user = TokenUtil.getUser();
        TavernAuditResult result = BeanCopierUtils.convert(item,TavernAuditResult.class);
        result.setUpdateTime(new Date());
        result.setUpdator(user.getLoginName());
        result.setSource("CONSUMER");
        result.setUpdatorName(user.getUserName());
        tavernAuditResultMapper.update(result,new QueryWrapper<TavernAuditResult>().lambda().eq(TavernAuditResult::getRoomName,item.getRoomName()));
    }

    @Override
    public void saveExcelDetailLogs(ThreadLocal<Long> exportIdLocal, List<TarvenResultDetailsLog> logList) {
        logList.stream().forEach(log -> {
            log.setId(exportIdLocal.get());
            log.setCreateTime(new Date());
            tarvenResultDetailsLogMapper.insert(log);
        });
    }

    @Override
    public void addEsuc(Long id, TavernExportLogForm tavernExportLogForm) {
        tavernExportLogForm.setStatus(ExportEnum.ESUC.getCode());
        TavernExportLog tavernExportLog = new TavernExportLog();
        BeanUtils.copyProperties(tavernExportLogForm, tavernExportLog);
        tavernExportLog.setId(id);
        tavernExportLog.setUpdateTime(new Date());
        tavernExportLogMapper.updateById(tavernExportLog);
    }

    @Override
    public String getLastUpdate(RoomAuditQuery query) {
        String res = tavernAuditResultMapper.getLastUpdate(query);
        return res;
    }

    @Override
    public TavernOverviewInfo getOverview(RoomAuditQuery query) {
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        TavernOverviewInfo res = tavernAuditResultMapper.getOverview(query);
        if (Objects.isNull(res)) {
            return new TavernOverviewInfo();
        }
        String bkftFee = tavernAuditResultMapper.getBkftFee(query);
        Integer roomCounts = tavernAuditResultMapper.getRoomCounts(query);
        res.setBkftFee(bkftFee);
        res.setRoomCounts(String.valueOf(roomCounts));
        res.setLoginName(user.getLoginName());
        res.setUserName(user.getUserName());
        List<String> roleNames = new ArrayList();
        for (String roleId : user.getRoleIds()) {
            String roleName = tavernAuditResultMapper.getRoleName(roleId);
            roleNames.add(roleName);
        }
        res.setUserRole(roleNames.toString());
        res.setMonth(query.getMonth());
        return res;
    }

    private QueryWrapper<TavernAuditResult> getQw(RoomAuditQuery query) {
        QueryWrapper<TavernAuditResult> qw = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getTgfd())) {
            qw.lambda().eq(TavernAuditResult::getTgfd,query.getTgfd());
        }
        if (StringUtils.isNotBlank(query.getMonth())) {
            qw.lambda().eq(TavernAuditResult::getMonth,query.getMonth());
        }
        if (StringUtils.isNotBlank(query.getRoomName())) {
            qw.lambda().like(TavernAuditResult::getRoomName,query.getRoomName());
        }
        String opTime = roomAuditMapper.getOpTime(query);
        qw.lambda().eq(TavernAuditResult::getOpTime,opTime);
        return qw;
    }
}
