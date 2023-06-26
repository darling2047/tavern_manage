package com.manage.tavern.model.vo;

import lombok.Data;

/**
 * @description: 钉钉财务报销品类清单
 * @author: dll
 * @date: Created in 2023/5/18 19:43
 * @version:
 * @modified By:
 */
@Data
public class TavernDingBxDataVo {


    /**
     * 托管房东
     */
    private String tgfd;

    /**
     * 月份
     */
    private String month;

    /**
     * 发起时间
     */
    private String createTime;

    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 标题
     */
    private String title;

    /**
     * 发起人
     */
    private String creator;

    /**
     * 区域
     */
    private String itemArea;

    /**
     * 报销品类
     */
    private String bxType;

    /**
     * 房屋名称
     */
    private String itemRoom;

    /**
     * 报销金额
     */
    private String amount;

    /**
     * 金额小计
     */
    private String jexj;

    /**
     * 费用说明
     */
    private String remark;

    /**
     * 手续费
     */
    private String commission;


}
