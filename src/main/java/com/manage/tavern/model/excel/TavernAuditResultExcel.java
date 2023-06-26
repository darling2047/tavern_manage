package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class TavernAuditResultExcel {

    @ColumnWidth(20)
    @ExcelProperty("ID")
    private Integer id;

    @ColumnWidth(20)
    @ExcelProperty("月份")
    private String month;

    @ColumnWidth(20)
    @ExcelProperty("触发来源")
    private String source;

    @ColumnWidth(20)
    @ExcelProperty("数据账期")
    private String opTime;

    @ColumnWidth(20)
    @ExcelProperty("修改人")
    private String updator;

    @ColumnWidth(20)
    @ExcelProperty("修改人姓名")
    private String updatorName;

    @ColumnWidth(20)
    @ExcelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ColumnWidth(20)
    @ExcelProperty("省份")
    private String proName;

    @ColumnWidth(20)
    @ExcelProperty("地市")
    private String cityName;

    @ColumnWidth(20)
    @ExcelProperty("区县")
    private String countyName;

    @ColumnWidth(20)
    @ExcelProperty("区域")
    private String area;

    @ColumnWidth(20)
    @ExcelProperty("房屋名称")
    private String roomName;

    @ColumnWidth(20)
    @ExcelProperty("房型")
    private String roomType;

    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    @ColumnWidth(20)
    @ExcelProperty("入住天数")
    private String rzDays;

    @ColumnWidth(20)
    @ExcelProperty("平台打款")
    private String priceClean;

    @ColumnWidth(20)
    @ExcelProperty("房租")
    private String actualZj;

    @ColumnWidth(20)
    @ExcelProperty("客耗品")
    private String khFee;

    @ColumnWidth(20)
    @ExcelProperty("保洁")
    private String bjFee;

    @ColumnWidth(20)
    @ExcelProperty("布草开支")
    private String bcExpend;

    @ColumnWidth(20)
    @ExcelProperty("日常开支")
    private String dailyExpend;

    @ColumnWidth(20)
    @ExcelProperty("开支总计")
    private String sumExpend;

    @ColumnWidth(20)
    @ExcelProperty("佣金")
    private String commission;

    @ColumnWidth(20)
    @ExcelProperty("净利润")
    private String netProfits;

    @ColumnWidth(20)
    @ExcelProperty("结算金额")
    private String settlementAmount;

    @ColumnWidth(20)
    @ExcelProperty("备注")
    private String remark;

    @ColumnWidth(20)
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;



}
