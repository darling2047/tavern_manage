package com.manage.tavern.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: 房屋基础信息
 * @author: dll
 * @date: Created in 2023/5/21 14:40
 * @version:
 * @modified By:
 */
@Data
public class RoomBaseInfoExcel {

    @ColumnWidth(20)
    @ExcelProperty("ID")
    private Integer id;

    
     
     
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
    @ExcelProperty("床数")
    private String typeLevel;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("托管房东")
    private String tgfd;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("合作方式")
    private String interactWay;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("合同开始日期")
    private String contractStartDate;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("合同结束日期")
    private String contractEndDate;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("付款方式")
    private String payWay;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("付款月份")
    private String fkyf;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("支付时间要求")
    private String zfyq;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("交租日期")
    private String jzrq;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("合同租金")
    private String htzj;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("实际租金")
    private String actualZj;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("合同押金")
    private String contractDeposit;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("违约金")
    private String wyj;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("是否自动续租")
    private String autoXz;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("最近交租开始时间")
    private String jzStartDate;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("最近交租结束时间")
    private String jzEndDate;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("百度网盘链接")
    private String baiduUrl;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("百度网盘备注")
    private String baiduRemark;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("房租负责人")
    private String fzLiablePerson;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("看房负责人")
    private String lookLiablePerson;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("租房负责人")
    private String zfLiablePerson;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("装修负责人")
    private String zxLiablePerson;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("备注1")
    private String remarkOne;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("备注3")
    private String remarkThree;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("详细地址")
    private String address;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("经纬度")
    private String location;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("电号")
    private String electricNumber;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("缴费金额")
    private String electricFee;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("水号")
    private String waterNumber;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("缴费金额")
    private String waterFee;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("气号")
    private String airNumber;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("缴费金额")
    private String airFee;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("床型")
    private String bedType;

    
     
     
    @ColumnWidth(20)
    @ExcelProperty("沙发床")
    private String sofaBed;




    @ColumnWidth(20)
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;



}
