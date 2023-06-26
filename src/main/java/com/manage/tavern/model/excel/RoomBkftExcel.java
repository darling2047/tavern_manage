package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class RoomBkftExcel implements Serializable {


    @ColumnWidth(20)
    @ExcelProperty("ID")
    private Long id;

    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    @ColumnWidth(20)
    @ExcelProperty("审批完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

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
    private String amount;

    @ColumnWidth(20)
    @ExcelProperty("费用说明")
    private String remark;

    @ColumnWidth(20)
    @ExcelProperty("月份")
    private String month;

    @ColumnWidth(20)
    @ExcelProperty("创建人")
    private String creator;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ColumnWidth(20)
    @ExcelProperty("创建时间")
    private Date createTime;


    @Override
    public String toString() {
        return "RoomBkft{" +
        ", id=" + id +
        ", tgfd=" + tgfd +
        ", finishTime=" + finishTime +
        ", promoter=" + promoter +
        ", area=" + area +
        ", bxType=" + bxType +
        ", roomName=" + roomName +
        ", amount=" + amount +
        ", remark=" + remark +
        ", month=" + month +
        ", creator=" + creator +
        ", createTime=" + createTime +
        "}";
    }
}
