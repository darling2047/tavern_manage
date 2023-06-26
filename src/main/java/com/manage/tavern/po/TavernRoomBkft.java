package com.manage.tavern.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DLL
 * @since 2023-06-16
 */
@TableName("tavern_room_bkft")
@Data
public class TavernRoomBkft implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建人
     */
    private String creator;

    private Date createTime;


    @Override
    public String toString() {
        return "RoomBkft{" +
        ", id=" + id +
        ", tgfd=" + tgfd +
        ", finishTime=" + finishTime +
        ", promoter=" + promoter +
        ", area=" + area +
        ", bxType=" + bxType +
        ", roomName=" + roomName +
        ", amount=" + amount +
        ", remark=" + remark +
        ", month=" + month +
        ", creator=" + creator +
        ", createTime=" + createTime +
        "}";
    }
}
