package com.manage.tavern.controller;

import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernOverviewInfo;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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


    @SysLog("总览查询")
    @GetMapping("/getOverview")
    public TavernOverviewInfo getOverview(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        TavernOverviewInfo res = tavernAuditResultService.getOverview(query);
        return res;
    }

}
