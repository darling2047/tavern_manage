package com.manage.tavern.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.excel.RoomBaseInfoExcel;
import com.manage.tavern.model.form.RoomBaseInfoForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomBaseInfoVo;
import com.manage.tavern.service.TavernRoomBaseInfoService;
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
 * @date: Created in 2023/5/21 19:49
 * @version:
 * @modified By:
 */
@RestController
@RequestMapping("/room")
public class TavernRoomBaseInfoController {


    @Resource
    private TavernRoomBaseInfoService roomBaseInfoService;


    @SysLog("房屋信息查询")
    @RequestMapping("/getList")
    public PaginationModel<RoomBaseInfoVo> getList(RoomAuditQuery query) {
        PaginationModel<RoomBaseInfoVo> res = roomBaseInfoService.getList(query);
        return res;
    }

    @SysLog("房屋信息下载")
    @GetMapping("/downLoad")
    public void downLoad(RoomAuditQuery query, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(query.getMonth())) {
            String month = DateUtils.getTime(new Date(), "yyyyMM");
            query.setMonth(month);
        }
        query.setPageSize(10000);
        PaginationModel<RoomBaseInfoVo> res = roomBaseInfoService.getList(query);
        List<RoomBaseInfoVo> data = res.getData();
        List<RoomBaseInfoExcel> excels = BeanCopierUtils.convertList(data, RoomBaseInfoExcel.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encodeName = URLEncoder.encode(query.getTgfd()+"房屋基础信息表", StandardCharsets.UTF_8.toString());
        response.setHeader("Content-disposition", "attachment;filename=" + encodeName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(1, "房屋列表").head(RoomBaseInfoExcel.class).build();
        excelWriter.write(excels, writeSheet);
        excelWriter.finish();
    }


    @SysLog("房屋信息编辑")
    @RequestMapping("/edit")
    public ResponResult edit(RoomBaseInfoForm form) {
        roomBaseInfoService.edit(form);
        return ResponResult.success();
    }

    @SysLog("房屋信息删除")
    @RequestMapping("/delRoomById")
    public ResponResult delRoomById(Integer id) {
        roomBaseInfoService.delRoomById(id);
        return ResponResult.success();
    }

}
