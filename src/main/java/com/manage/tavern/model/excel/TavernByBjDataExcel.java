package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @description: 宝寓保洁信息
 * @author: dll
 * @date: Created in 2023/5/8 16:19
 * @version:
 * @modified By:
 */
@Data
public class TavernByBjDataExcel {

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
    @ExcelProperty("地址")
    private String address;

    @ColumnWidth(20)
    @ExcelProperty("唯一标识")
    private Integer id;

    @ColumnWidth(20)
    @ExcelProperty("打扫日期")
    private String taskDate;

    @ColumnWidth(20)
    @ExcelProperty("房间")
    private String roomNo;


    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    @ColumnWidth(20)
    @ExcelProperty("保洁员")
    private String workerName;

    @ColumnWidth(20)
    @ExcelProperty("保洁员手机号")
    private String workerMobile;

    @ColumnWidth(20)
    @ExcelProperty("类型")
    private String cleanLevelName;

    @ColumnWidth(20)
    @ExcelProperty("价格")
    private String price;

    @ColumnWidth(20)
    @ExcelProperty("时间段")
    private String planTime;

    @ColumnWidth(20)
    @ExcelProperty("状态")
    private String stateName;

    @ColumnWidth(20)
    @ExcelProperty("员工ID")
    private String emplId;

    @ColumnWidth(20)
    @ExcelProperty("员工名称")
    private String emplName;

    @ColumnWidth(20)
    @ExcelProperty("房态")
    private String tag;

    @ColumnWidth(20)
    @ExcelProperty("数据操作日期")
    private String opTime;


    @ColumnWidth(20)
    @ExcelProperty("月份")
    private String month;

}
