package com.manage.tavern.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/6/23 13:08
 * @version:
 * @modified By:
 */
@Data
public class TavernByDataRoomVo {

    private String id;

    private String opTime;

    private String title;

    private String chatRenterNum;

    private String newContractNum;

    private String newContractPrice;

    private String roomCountSold;

    private String price;

    private String priceAgent;

    private String priceClean;

    private String priceDiscount;

    private String priceCoupon;

    private String priceInvestor;

    private String financePriceIncome;

    private String financePriceExpense;

    private String financePriceClean;

    private String roomCount;

    private String roomSoldPercent;

    private String priceAvg;

    private String priceCleanAvg;

    private String revPar;

    private String revParClean;

    private String roomSoldPercentOfAll;

    private String pricePercentOfAll;

    private String priceCleanPercentOfAll;

    private String newContractPercentOfAll;

    private String priceAgentPercentOfAll;

    /**
     * 数据账期
     */
    private String dataTime;

    /**
     * 数据月份
     */
    private String month;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String tgfd;

}
