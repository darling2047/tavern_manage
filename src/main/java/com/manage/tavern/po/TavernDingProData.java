package com.manage.tavern.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 根据表单id获取到的审批实例数据   tavern_ding_pro_data
 * @author: dll
 * @date: Created in 2023/5/4 16:19
 * @version:
 * @modified By:
 */
@Data
public class TavernDingProData {

    /**
     *唯一标识
     */
    private String id;

    /**
     * 表单id
     */
    private String formId;

    /**
     * 实例id
     */
    private String proId;

    /**
     * 审批类型
     * 1：收款审批 2:财务报销
     */
    private Integer approvalType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 数据账期
     */
    private String dataTime;

    /**
     * 数据月份
     */
    private String month;


}
