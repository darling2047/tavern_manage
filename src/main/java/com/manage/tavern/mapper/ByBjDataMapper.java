package com.manage.tavern.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;
import com.manage.tavern.model.vo.TavernByBjDataVo;
import com.manage.tavern.model.vo.TavernDingBxDataVo;
import com.manage.tavern.po.TavernByBjData;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/8 16:26
 * @version:
 * @modified By:
 */
public interface ByBjDataMapper extends BaseMapper<TavernByBjData> {

    /**
     * 获取保洁清单数据
     * @param iPage
     * @param query
     * @return
     */
    List<TavernByBjDataVo> getBjDetails(IPage<TavernByBjDataVo> iPage, RoomAuditQuery query);

    /**
     * 获取钉钉报销品类清单
     * @param iPage
     * @param query
     * @return
     */
    List<TavernDingBxDataVo> getDingBxDetails(IPage<TavernDingBxDataVo> iPage, RoomAuditQuery query);

    /**
     * 获取房间指定月份保洁次数
     * @param auditInfo
     * @return
     */
    Integer getBjCounts(RoomAuditInfo auditInfo,String month);
}
