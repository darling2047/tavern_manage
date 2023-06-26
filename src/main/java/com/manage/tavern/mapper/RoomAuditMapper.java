package com.manage.tavern.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.tavern.model.base.SelectData;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/7 19:46
 * @version:
 * @modified By:
 */
public interface RoomAuditMapper {

    /**
     * 分页获取房屋清算列表
     * @param page
     * @param query
     * @return
     */
    List<RoomAuditInfo> getList(IPage<RoomAuditInfo> page, RoomAuditQuery query);

    /**
     * 获取指定房间的日常开支
     * ①房屋损耗、②日常流水、③物业费、④水电气宽带、⑤运营推广费，
     * 这5个报销品类取“报销金额”
     * @param roomName
     * @return
     */
    String getDailyExpend(@Param("roomName") String roomName,@Param("query") RoomAuditQuery query);

    /**
     * 获取指定房间的其他日常开支
     * ①拉新、②退单退款，这2个报销品类取“手续费”
     * @param roomName
     * @return
     */
    String getOtherDailyExpend(@Param("roomName") String roomName,@Param("query") RoomAuditQuery query);

    /**
     * 获取指定房间的客耗
     * @param roomName
     * @return
     */
    String getKhFee(@Param("roomName") String roomName,@Param("query") RoomAuditQuery query);

    /**
     * 获取指定房间的客耗
     * @param roomName
     * @return
     */
    String getBjFee(@Param("roomName") String roomName,@Param("query") RoomAuditQuery query);

    /**
     * 后去下拉列表数据
     * @param name
     * @return
     */
    List<SelectData> getSelectData(@Param("name") String name);

    /**
     * 获取房屋升级费用
     * @param roomName
     * @param query
     * @return
     */
    String getFwsjFee(@Param("roomName") String roomName,@Param("query") RoomAuditQuery query);

    /**
     * 获取指定月份的最新数据账期
     * @param query
     * @return
     */
    String getOpTime(@Param("query") RoomAuditQuery query);

    /**
     * 更新审批结果
     * @param result
     * @param busId
     */
    void updateDingFormResult(String result, String busId);
}
