package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: 房屋清算结果表    tavern_audit_result
 * @author: dll
 * @date: Created in 2023/5/7 19:31
 * @version:
 * @modified By:
 */
@Data
public class TavernAuditResultExcelModel {

    @ColumnWidth(20)
    @ExcelProperty("ID")
    private Integer id;

    @ColumnWidth(20)
    @ExcelProperty("房屋名称")
    private String roomName;

    @ColumnWidth(20)
    @ExcelProperty("入住天数")
    @NumberFormat(value = "0")
    private String rzDays;

    @ColumnWidth(20)
    @ExcelProperty("平台打款")
    @NumberFormat(value = "0")
    private String priceClean;

    @ColumnWidth(20)
    @ExcelProperty("房租")
    @NumberFormat(value = "0")
    private String actualZj;

    @ColumnWidth(20)
    @ExcelProperty("客耗品")
    @NumberFormat(value = "0")
    private String khFee;

    @ColumnWidth(20)
    @ExcelProperty("保洁")
    @NumberFormat(value = "0")
    private String bjFee;

    @ColumnWidth(20)
    @ExcelProperty("布草开支")
    @NumberFormat(value = "0")
    private String bcExpend;

    @ColumnWidth(20)
    @ExcelProperty("日常开支")
    @NumberFormat(value = "0")
    private String dailyExpend;

    @ColumnWidth(20)
    @ExcelProperty("开支总计")
    @NumberFormat(value = "0")
    private String sumExpend;

    @ColumnWidth(20)
    @ExcelProperty("佣金")
    @NumberFormat(value = "0")
    private String commission;

    @ColumnWidth(20)
    @ExcelProperty("净利润")
    @NumberFormat(value = "0")
    private String netProfits;

    @ColumnWidth(20)
    @ExcelProperty("结算金额")
    @NumberFormat(value = "0")
    private String settlementAmount;

    @ColumnWidth(20)
    @ExcelProperty("备注")
    private String remark;

}
