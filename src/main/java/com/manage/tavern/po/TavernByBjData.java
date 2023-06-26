package com.manage.tavern.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description: 宝寓保洁信息 Tavern_By_Bj_Data
 * @author: dll
 * @date: Created in 2023/5/8 16:19
 * @version:
 * @modified By:
 */
@Data
public class TavernByBjData {

    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房间
     */
    private String roomName;

    /**
     * 日期
     */
    private String dataDate;


    /**
     * 保洁员
     */
    private String bjName;


    /**
     * 保洁员电话
     */
    private String workerMobile;

    /**
     * 时间段
     */
    private String planTime;

    /**
     * 类型
     */
    private String type;

    /**
     * 价格
     */
    private String price;

    /**
     * 备注
     */
    private String remark;

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

}
