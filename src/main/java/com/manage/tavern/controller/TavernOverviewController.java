package com.manage.tavern.controller;

import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernOverviewInfo;
import com.manage.tavern.service.ISystemService;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/7/1 16:19
 * @version:
 * @modified By:
 */
@RestController
@RequestMapping("/overview")
public class TavernOverviewController {

    @Resource
    private TavernAuditResultService tavernAuditResultService;

    @Resource
    private ISystemService systemService;


    @SysLog("总览查询")
    @GetMapping("/getOverview")
    public ResponResult<TavernOverviewInfo> getOverview(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        TavernOverviewInfo res = tavernAuditResultService.getOverview(query);
        return ResponResult.success(res);
    }

    @SysLog("获取最后一次登录时间")
    @RequestMapping("/getLastLoginTime")
    public ResponResult<String> getLastLoginTime() {
        return ResponResult.success(systemService.getLastLoginTime());
    }

}
