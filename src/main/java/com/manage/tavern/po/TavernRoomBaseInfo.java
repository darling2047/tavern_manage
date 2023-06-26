package com.manage.tavern.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @description: 房屋基础信息
 * @author: dll
 * @date: Created in 2023/5/21 14:40
 * @version:
 * @modified By:
 */
@Data
public class TavernRoomBaseInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 状态  0:失效(已删除)；1：有效
     */
    private Integer status;

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
     * 床数
     */
    private String typeLevel;

    /**
     * 托管房东
     */
    private String tgfd;

    /**
     * 合作方式
     */
    private String interactWay;

    /**
     * 合同开始日期
     */
    private String contractStartDate;

    /**
     * 合同结束日期
     */
    private String contractEndDate;

    /**
     * 付款方式
     */
    private String payWay;

    /**
     * 付款月份
     */
    private String fkyf;

    /**
     * 支付时间要求
     */
    private String zfyq;

    /**
     * 交租日期
     */
    private String jzrq;

    /**
     * 合同租金
     */
    private String htzj;

    /**
     * 实际租金
     */
    private String actualZj;

    /**
     * 合同押金
     */
    private String contractDeposit;

    /**
     * 违约金
     */
    private String wyj;

    /**
     * 是否自动续租
     */
    private String autoXz;

    /**
     * 最近交租开始时间
     */
    private String jzStartDate;

    /**
     * 最近交租结束时间
     */
    private String jzEndDate;

    /**
     * 百度网盘链接
     */
    private String baiduUrl;

    /**
     * 百度网盘备注
     */
    private String baiduRemark;

    /**
     * 房租负责人
     */
    private String fzLiablePerson;

    /**
     * 看房负责人
     */
    private String lookLiablePerson;

    /**
     * 租房负责人
     */
    private String zfLiablePerson;

    /**
     * 装修负责人
     */
    private String zxLiablePerson;

    /**
     * 备注1
     */
    private String remarkOne;

    /**
     * 备注3
     */
    private String remarkThree;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 电号
     */
    private String electricNumber;

    /**
     * 缴费金额
     */
    private String electricFee;

    /**
     * 水号
     */
    private String waterNumber;

    /**
     * 缴费金额
     */
    private String waterFee;

    /**
     * 气号
     */
    private String airNumber;

    /**
     * 缴费金额
     */
    private String airFee;

    /**
     * 床型
     */
    private String bedType;

    /**
     * 沙发床
     */
    private String sofaBed;

    /**
     * 创建时间
     */
    private Date createTime;



}
