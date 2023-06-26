package com.manage.tavern.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @description: 房屋清算结果表    tavern_audit_result
 * @author: dll
 * @date: Created in 2023/5/7 19:31
 * @version:
 * @modified By:
 */
@Data
public class TavernAuditResult {

    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 月份
     */
    private String month;

    /**
     * 触发来源:SYS,CONSUMER
     */
    private String source;

    /**
     * 数据账期(日)
     */
    private String opTime;

    /**
     * 是否允许编辑(1:允许;0:不允许)
     */
    private String editFlag;

    /**
     * 修改人账号
     */
    private String updator;

    /**
     * 修改人姓名
     */
    private String updatorName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 省份
     */
    private String proName;

    /**
     * 地市
     */
    private String cityName;

    /**
     * 区县
     */
    private String countyName;

    /**
     * 区域
     */
    private String area;

    /**
     * 房屋名称
     */
    private String roomName;

    /**
     * 房型
     */
    private String roomType;

    /**
     * 托管房东
     */
    private String tgfd;

    /**
     * 入住天数
     */
    private String rzDays;

    /**
     * 平台打款
     */
    private String priceClean;

    /**
     * 房租
     */
    private String actualZj;

    /**
     * 客耗品(天数 * 客耗基础标准)
     */
    private String khFee;

    /**
     * 保洁
     */
    private String bjFee;

    /**
     * 布草开支
     */
    private String bcExpend;

    /**
     * 日常开支
     */
    private String dailyExpend;

    /**
     * 开支总计
     */
    private String sumExpend;

    /**
     * 佣金
     */
    private String commission;

    /**
     * 净利润
     */
    private String netProfits;

    /**
     * 金总收款金额
     */
    private String jinFee;

    /**
     * 结算金额
     */
    private String settlementAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;



}
