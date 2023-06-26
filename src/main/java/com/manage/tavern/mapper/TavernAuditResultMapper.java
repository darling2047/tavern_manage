package com.manage.tavern.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.po.TavernAuditResult;
import org.apache.ibatis.annotations.Param;

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
}
