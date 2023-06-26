package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description: 托管房东佣金规则
 * @author: dll
 * @date: Created in 2023/5/13 15:16
 * @version:
 * @modified By:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TgfdCommissionRules {

    TEACHER_ZHANG("张老师", "平台打款-客耗品-保洁-布草洗涤",new BigDecimal("0.18")),
    CL("陈龙", "平台打款",new BigDecimal("0.18")),
    SMGC("世贸广场402", "平台打款",new BigDecimal("0.18")),
    JJK("金坚坤", "平台打款",new BigDecimal("0.16"));


    private String tgfdName;

    private String expression;

    private BigDecimal factor;


}
