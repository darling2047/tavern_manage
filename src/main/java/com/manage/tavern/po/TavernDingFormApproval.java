package com.manage.tavern.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: OA审批-新居Times审批7-收款审批数据获取  PROC-B7FC540E-AEE5-4BAB-951E-F8CF55CDDE5C
 * @author: dll
 * @date: Created in 2023/5/4 16:08
 * @version:
 * @modified By:
 */
@Data
public class TavernDingFormApproval {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 流程实例ID
     */
    private String proId;

    /**
     * 流程完成时间
     */
    private Date finishTime;

    /**
     * 流程开始时间
     */
    private Date createTime;

    /**
     * 标题
     */
    private String title;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 部门id
     */
    private String originatorDeptId;

    /**
     * 部门名称
     */
    private String originatorDeptName;

    /**
     * 区域
     */
    private String area;

    /**
     * 表格json数据
     */
    private String tableData;

    /**
     * 费用总计
     */
    private String feeSum;

    /**
     * 收款方式
     */
    private String payType;

    /**
     * 流程状态
     */
    private String status;

    /**
     * 入库时间
     */
    private Date opTime;

    /**
     * 发起人userId
     */
    private String originatorUserId;

    /**
     * 审批类型
     * 1:收款审批 2:财务报销
     */
    private Integer approvalType;

    /**
     * 数据账期
     */
    private String dataTime;

    /**
     * 数据月份
     */
    private String month;

    /**
     * 审批结果
     */
    private String result;

}
