package com.manage.tavern.model.vo;

import lombok.Data;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/7/1 16:11
 * @version:
 * @modified By:
 */
@Data
public class TavernOverviewInfo {

    /**
     * 月份
     */
    private String month;

    /**
     * 账号名
     */
    private String loginName;

    /**
     * 中文名
     */
    private String userName;

    /**
     * 身份(角色信息)
     */
    private String userRole;

    /**
     * 拥有房产
     */
    private String roomCounts;

    /**
     * 结算金额
     */
    private String settlementAmount;

    /**
     * 净利润
     */
    private String netProfits;

    /**
     * 平台打款
     */
    private String priceClean;

    /**
     * 开支总计
     */
    private String sumExpend;

    /**
     * 不可分摊金额
     */
    private String bkftFee;

    /**
     * 房租
     */
    private String actualZj;

    /**
     * 客耗品(天数 * 客耗基础标准)
     */
    private String khFee;

    /**
     * 保洁
     */
    private String bjFee;

    /**
     * 布草开支
     */
    private String bcExpend;

    /**
     * 日常开支
     */
    private String dailyExpend;

    /**
     * 佣金
     */
    private String commission;

}
