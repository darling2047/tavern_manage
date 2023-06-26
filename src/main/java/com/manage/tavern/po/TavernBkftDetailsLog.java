package com.manage.tavern.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-06-17
 */
@TableName("tarven_bkft_details_log")
@Data
public class TavernBkftDetailsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 托管房东
     */
    private String tgfd;

    /**
     * 审批完成时间
     */
    private String finishTime;

    /**
     * 发起人姓名
     */
    private String promoter;

    /**
     * 区域
     */
    private String area;

    /**
     * 报销品类
     */
    private String bxType;

    /**
     * 房屋名称
     */
    private String roomName;

    /**
     * 报销金额
     */
    private String amount;

    /**
     * 费用说明
     */
    private String remark;

    /**
     * 月份
     */
    private String month;

    private Date createTime;

    private String statusDesc;

    private String detailDesc;

    private Integer SOURCE;

}
