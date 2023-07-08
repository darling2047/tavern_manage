package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: huangfuliang
 * @date: 2020-03-18.
 * 导出的枚举
 */
@Getter
@AllArgsConstructor
public enum AuditResultFeeEnum {

    actualZj("actualZj", "房租"),

    khFee("khFee", "客耗品"),

    bjFee("bjFee", "保洁"),

    bcExpend("bcExpend", "布草开支"),

    dailyExpend("dailyExpend", "日常开支"),

    sumExpend("sumExpend", "开支总计"),

    commission("commission", "佣金"),

    netProfits("netProfits", "净利润"),

    settlementAmount("settlementAmount", "结算金额");

    private String code;

    private String desc;

}
