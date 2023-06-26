package com.manage.tavern.po.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Table: TAVERN_EXPORT_LOG
 */
@Data
public class TavernExportLog implements Serializable {
    /** 流水号 */
    private Long id;

    /** 用户名称 */
    private String userId;

    /** 地市编号 */
    private String cityId;

    /** 成功行数 */
    private Integer sucCount;

    /** 失败行数 */
    private Integer failCount;

    /** 失败记录 */
    private String failId;

    /** 状态  */
    private Integer status;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 导入的类型  */
    private Integer type;

    private static final long serialVersionUID = 1L;
}