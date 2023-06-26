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
public enum TavernExportLogEnum {

    AWARD_DETAILS_EXPORT(888, "清算结果导入"),

    BKFT_ROOM_EXPORT(999, "不可分摊费用导入");

    private Integer code;

    private String desc;

}
