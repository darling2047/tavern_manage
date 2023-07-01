package com.manage.tavern.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernOverviewInfo;
import com.manage.tavern.po.TavernAuditResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/29 21:53
 * @version:
 * @modified By:
 */
public interface TavernAuditResultMapper extends BaseMapper<TavernAuditResult> {

    /**
     * 获取清算结果最近跟新日期
     * @param query
     * @return
     */
    String getLastUpdate(@Param(value = "query") RoomAuditQuery query);

    /**
     * 获取总览信息
     * @param query
     * @return
     */
    TavernOverviewInfo getOverview(@Param(value = "query") RoomAuditQuery query);

    /**
     * 获取不可分摊费用
     * @param query
     * @return
     */
    String getBkftFee(@Param(value = "query") RoomAuditQuery query);

    /**
     * 获取房屋数量
     * @param query
     * @return
     */
    Integer getRoomCounts(@Param(value = "query") RoomAuditQuery query);

    /**
     * 根据角色ID获取角色名称
     * @param roleId
     * @return
     */
    String getRoleName(@Param(value = "roleId") String roleId);
}
