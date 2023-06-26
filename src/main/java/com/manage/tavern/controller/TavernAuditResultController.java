package com.manage.tavern.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.listener.AuditResultListener;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.excel.TavernAuditResultExcel;
import com.manage.tavern.model.excel.TavernAuditResultExcelModel;
import com.manage.tavern.model.form.TavernAuditResultForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernAuditResultVo;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
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
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/29 21:40
 * @version:
 * @modified By:
 */
@RestController
@RequestMapping("/auditResult")
public class TavernAuditResultController {


    @Resource
    private TavernAuditResultService tavernAuditResultService;

    @SysLog("清算结果查询")
    @GetMapping("/getList")
    public PaginationModel<TavernAuditResultVo> getList(RoomAuditQuery query) {
        // 默认展示当前月的数据
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        PaginationModel<TavernAuditResultVo> res = tavernAuditResultService.getList(query);
        return res;
    }

    @SysLog("跑指定月份的清算结果")
    @GetMapping("/publishResult")
    public ResponResult insertResult(String month) {
        tavernAuditResultService.insertResult(month,"CONSUMER");
        return ResponResult.success();
    }

    @SysLog("修改清算结果")
    @PostMapping("/update")
    public ResponResult update(TavernAuditResultForm params) {
        tavernAuditResultService.update(params);
        return ResponResult.success();
    }

    @SysLog("清算结果下载")
    @GetMapping("/downLoad")
    public void downLoad(RoomAuditQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<TavernAuditResultVo> res = tavernAuditResultService.getList(query);
        List<TavernAuditResultVo> data = res.getData();
        List<TavernAuditResultExcel> excels = BeanCopierUtils.convertList(data, TavernAuditResultExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+"结算清单", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "结算清单列表").head(TavernAuditResultExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }

    @SysLog("清算模板下载")
    @GetMapping("/templateExport")
    public void templateExport(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=结算结果导入模板.xlsx");
        TavernAuditResultExcelModel excel = new TavernAuditResultExcelModel();
        List<TavernAuditResultExcelModel> list = new ArrayList<>();
        list.add(excel);
        EasyExcel.write(response.getOutputStream(), TavernAuditResultExcelModel.class).sheet("结算结果导入模板").doWrite(list);
    }

    @SysLog(value = "清算结果导入")
    @PostMapping("/resultImport")
    public ResponResult awardImport(@RequestParam MultipartFile file, Integer awardId) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空!");
        }
        // 解析excel
        AuditResultListener listener = new AuditResultListener(tavernAuditResultService);
        EasyExcel.read(file.getInputStream(), TavernAuditResultExcelModel.class, listener).sheet().doRead();
        return ResponResult.success();
    }

    @SysLog(value = "获取清算结果最近跟新日期")
    @GetMapping("/getLastUpdate")
    public ResponResult<String> getLastUpdate(RoomAuditQuery query) throws IOException {
        // 默认展示当前月的数据
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        String date = tavernAuditResultService.getLastUpdate(query);
        return ResponResult.success(date);
    }
}
