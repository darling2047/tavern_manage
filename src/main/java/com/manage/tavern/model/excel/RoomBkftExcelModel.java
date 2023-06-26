package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DLL
 * @since 2023-06-16
 */
@Data
public class RoomBkftExcelModel implements Serializable {


    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    @ColumnWidth(20)
    @ExcelProperty("审批完成时间")
    private String finishTime;

    @ColumnWidth(20)
    @ExcelProperty("发起人姓名")
    private String promoter;

    @ColumnWidth(20)
    @ExcelProperty("区域")
    private String area;

    @ColumnWidth(20)
    @ExcelProperty("报销品类")
    private String bxType;

    @ColumnWidth(20)
    @ExcelProperty("房屋名称")
    private String roomName;

    @ColumnWidth(20)
    @ExcelProperty("报销金额")
    @NumberFormat(value = "0.00")
    private String amount;

    @ColumnWidth(20)
    @ExcelProperty("费用说明")
    private String remark;

    @ColumnWidth(20)
    @ExcelProperty("月份")
    @NumberFormat(value = "0")
    private String month;

}
