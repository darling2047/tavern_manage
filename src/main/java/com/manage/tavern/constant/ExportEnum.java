package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: huangfuliang
 * @date: 2020-03-13.
 * 导入日志枚举类
 */
@Getter
@AllArgsConstructor
public enum ExportEnum {

    /** 初始数据 */
    INIT(0, "导入中"),

    /** 导入成功 */
    ESUC(1, "导入成功"),

    /** 导入失败 */
    EFAIL(2, "导入失败");

    private Integer code;

    private String desc;
}
