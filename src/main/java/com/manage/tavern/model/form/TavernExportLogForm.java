package com.manage.tavern.model.form;

import lombok.Data;

/**
 * @author: huangfuliang
 * @date: 2020-03-13.
 */
@Data
public class TavernExportLogForm {

    private String userId;

    private String cityId;

    private Integer sucCount;

    private Integer failCount;

    /** 导入记录 */
    private Integer status;

    private String failId;

    /** 导入类型 */
    private Integer type;

}
