package com.manage.tavern.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.SelectData;
import com.manage.tavern.model.excel.RoomAuditInfoExcel;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;
import com.manage.tavern.service.RoomAuditService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @description: 清算报表
 * @author: dll
 * @date: Created in 2023/5/7 19:39
 * @version:
 * @modified By:
 */
@RequestMapping("/roomAudit")
@RestController
@Slf4j
public class RoomAuditController {


    @Resource
    private RoomAuditService roomAuditService;

    @SysLog("房屋清算列表查询")
    @GetMapping("/getList")
    public PaginationModel<RoomAuditInfo> getList(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<RoomAuditInfo> res = roomAuditService.getList(query);
        return res;
    }

    @SysLog("房屋清算列表下载")
    @GetMapping("/downLoad")
    public void downLoad(RoomAuditQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<RoomAuditInfo> res = roomAuditService.getList(query);
        List<RoomAuditInfo> data = res.getData();
        List<RoomAuditInfoExcel> excels = BeanCopierUtils.convertList(data, RoomAuditInfoExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+query.getMonth()+"月清算表", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "清算表").head(RoomAuditInfoExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }

    @SysLog("托管房东列表查询")
    @RequestMapping("/getSelectData")
    public List<SelectData> getSelectData(String name,Integer type) {
        List<SelectData> res = roomAuditService.getSelectData(name,type);
        return res;
    }

}
