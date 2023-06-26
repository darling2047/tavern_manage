package com.manage.tavern.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: OA审批-新居Times审批7-收款审批表格数据获取
 *               tavern_ding_sk_table_data
 * @author: dll
 * @date: Created in 2023/5/4 16:08
 * @version:
 * @modified By:
 */
@Data
public class TavernDingSkTableData {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 唯一标识
     */
    private String approvalId;

    /**
     * 流程实例ID
     */
    private String proId;

    /**
     * 所选区域
     */
    private String itemArea;

    /**
     * 房价号
     */
    private String itemRoom;

    /**
     * 收款原因
     */
    private String itemReason;

    /**
     * 入住时间
     */
    private Date checkInTime;

    /**
     * 退房时间
     */
    private Date outTime;

    /**
     * 金额
     */
    private String amount;

    /**
     * 备注、费用明细说明
     */
    private String remark;

    /**
     * 收款凭证
     */
    private String skSupport;

    /**
     * 入库时间
     */
    private Date opTime;

    /**
     * 报销品类
     */
    private String bxType;

    /**
     * 岗位
     */
    private String gw;

    /**
     * 保洁管家
     */
    private String houseKeeper;

    /**
     * 日常客耗名称
     */
    private String khName;

    /**
     * 订单平台名称
     */
    private String orderPlatName;

    /**
     * 实收房费
     */
    private String actualFee;

    /**
     * 手续费
     */
    private String commission;

    /**
     * 房租开始时间
     */
    private Date startDate;


    /**
     * 房租结束时间
     */
    private Date endDate;

    /**
     * 数据账期
     */
    private String dataTime;

    /**
     * 数据月份
     */
    private String month;


}
