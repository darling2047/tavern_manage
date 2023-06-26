package com.manage.tavern.model.vo;

import lombok.Data;

/**
 * @description: 房屋清算
 * @author: dll
 * @date: Created in 2023/5/7 19:31
 * @version:
 * @modified By:
 */
@Data
public class RoomAuditInfo {

    /**
     * 省份
     */
    private String proName;

    /**
     * 地市
     */
    private String cityName;

    /**
     * 区县
     */
    private String countyName;

    /**
     * 区域
     */
    private String area;

    /**
     * 房屋名称
     */
    private String roomName;

    /**
     * 房型
     */
    private String roomType;

    /**
     * 床数
     */
    private String typeLevel;

    /**
     * 托管房东
     */
    private String tgfd;

    /**
     * 入住天数
     */
    private String rzDays;

    /**
     * 平台打款
     */
    private String priceClean;

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
     * 开支总计
     */
    private String sumExpend;

    /**
     * 佣金
     */
    private String commission;

    /**
     * 净利润
     */
    private String netProfits;

    /**
     * 金总收款金额
     */
    private String jinFee;

    /**
     * 结算金额
     */
    private String settlementAmount;

    /**
     * 备注
     */
    private String remark;



}
