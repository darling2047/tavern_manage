package com.manage.tavern.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author dll      Tarven_Result_Details_Log
 * @create 2023/3/13 15:08
 * @describe 导入清单日志，记录失败原因
 */
@Data
public class TarvenResultDetailsLog {

    private Long id;

    private String roomName;

    private String opTime;

    private String rzDays;

    private String priceClean;

    private String actualZj;

    private String khFee;

    private String bjFee;

    private String bcExpend;

    private String dailyExpend;

    private String sumExpend;

    private String commission;

    private String netProfits;

    private String settlementAmount;

    private String remark;
    private String statusDesc;
    private String detailDesc;
    private Date createTime;

}
