package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @description: 钉钉财务报销品类清单
 * @author: dll
 * @date: Created in 2023/5/18 19:43
 * @version:
 * @modified By:
 */
@Data
public class TavernDingBxDataExcel {


    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    @ColumnWidth(20)
    @ExcelProperty("月份")
    private String month;

    @ColumnWidth(20)
    @ExcelProperty("发起时间")
    private String createTime;

    @ColumnWidth(20)
    @ExcelProperty("完成时间")
    private String finishTime;

    @ColumnWidth(20)
    @ExcelProperty("标题")
    private String title;

    @ColumnWidth(20)
    @ExcelProperty("发起人")
    private String creator;

    @ColumnWidth(20)
    @ExcelProperty("区域")
    private String itemArea;

    @ColumnWidth(20)
    @ExcelProperty("报销品类")
    private String bxType;

    @ColumnWidth(20)
    @ExcelProperty("房屋名称")
    private String itemRoom;

    @ColumnWidth(20)
    @ExcelProperty("报销金额")
    private String amount;

    @ColumnWidth(20)
    @ExcelProperty("费用说明")
    private String remark;


}
