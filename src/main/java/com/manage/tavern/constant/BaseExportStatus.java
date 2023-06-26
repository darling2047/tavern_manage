package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: huangfuliang
 * @date: 2020-04-01.
 * 导入状态
 */
@Getter
@AllArgsConstructor
public enum BaseExportStatus {

    SUC("导入成功", ""),

    FAIL ("导入失败", ""),

    EMTY("必填字段不能为空", ""),

    DATE_ERROR_DAY("日期格式不对", ""),

    REPEAT("重复数据", "");

    private String statusDesc;

    private String detailDesc;

}
