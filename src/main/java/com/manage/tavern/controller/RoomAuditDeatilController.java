package com.manage.tavern.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.excel.TavernByBjDataExcel;
import com.manage.tavern.model.excel.TavernDingBxDataExcel;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernByBjDataVo;
import com.manage.tavern.model.vo.TavernByDataRoomVo;
import com.manage.tavern.model.vo.TavernDingBxDataVo;
import com.manage.tavern.service.RoomAuditDeatilService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/16 17:42
 * @version:
 * @modified By:
 */
@RequestMapping("/details")
@RestController
public class RoomAuditDeatilController {

    @Resource
    private RoomAuditDeatilService roomAuditDeatilService;

    @SysLog("保洁清单查询")
    @GetMapping("/getBjDetails")
    public PaginationModel<TavernByBjDataVo> getList(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<TavernByBjDataVo> res = roomAuditDeatilService.getBjDetails(query);
        return res;
    }

    @SysLog("钉钉财务报销清单查询")
    @GetMapping("/getDingBxDetails")
    public PaginationModel<TavernDingBxDataVo> getDingBxDetails(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<TavernDingBxDataVo> res = roomAuditDeatilService.getDingBxDetails(query);
        return res;
    }

    @SysLog("保洁清单下载")
    @GetMapping("/bjDetailDownLoad")
    public void downLoad(RoomAuditQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<TavernByBjDataVo> res = roomAuditDeatilService.getBjDetails(query);
        List<TavernByBjDataVo> data = res.getData();
        List<TavernByBjDataExcel> excels = BeanCopierUtils.convertList(data, TavernByBjDataExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+query.getMonth()+"月保洁清算明细", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "保洁清单").head(TavernByBjDataExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }

    @SysLog("钉钉财务报销清单下载")
    @GetMapping("/dingBxDetailDownLoad")
    public void dingBxDetailDownLoad(RoomAuditQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<TavernDingBxDataVo> res = roomAuditDeatilService.getDingBxDetails(query);
        List<TavernDingBxDataVo> data = res.getData();
        List<TavernDingBxDataExcel> excels = BeanCopierUtils.convertList(data, TavernDingBxDataExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+query.getMonth()+"月钉钉报销品类清算明细", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "报销品类清单").head(TavernDingBxDataExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }



    @SysLog("宝寓财务清单查询")
    @GetMapping("/getBycwDetails")
    public PaginationModel<TavernByDataRoomVo> getBycwDetails(RoomAuditQuery query) {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getLastMonth("yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<TavernByDataRoomVo> res = roomAuditDeatilService.getBycwDetails(query);
        return res;
    }


}
