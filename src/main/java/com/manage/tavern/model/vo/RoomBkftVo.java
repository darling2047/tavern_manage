package com.manage.tavern.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
public class RoomBkftVo implements Serializable {

    private static final long serialVersionUID = 1L;

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


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
