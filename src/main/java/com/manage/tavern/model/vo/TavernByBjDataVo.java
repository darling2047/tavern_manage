package com.manage.tavern.model.vo;

import lombok.Data;

/**
 * @description: 宝寓保洁信息
 * @author: dll
 * @date: Created in 2023/5/8 16:19
 * @version:
 * @modified By:
 */
@Data
public class TavernByBjDataVo {

    /**
     * 托管房东
     */
    private String tgfd;

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
     * 地址
     */
    private String address;

    /**
     * 唯一标识
     */
    private Integer id;

    /**
     * 打扫日期
     */
    private String taskDate;

    /**
     * 房间
     */
    private String roomNo;

    /**
     * 保洁员
     */
    private String workerName;

    /**
     * 保洁员手机号
     */
    private String workerMobile;

    /**
     * 类型
     */
    private String cleanLevelName;

    /**
     * 价格
     */
    private String price;

    /**
     * 时间段
     */
    private String planTime;

    /**
     * 状态
     */
    private String stateName;

    /**
     * 员工ID
     */
    private String emplId;

    /**
     * 员工名称
     */
    private String emplName;

    /**
     * 房态
     */
    private String tag;

    /**
     * 数据操作日期
     */
    private String opTime;

    /**
     * 入库时间
     */
    private String createTime;

    /**
     * 月份
     */
    private String month;

}
