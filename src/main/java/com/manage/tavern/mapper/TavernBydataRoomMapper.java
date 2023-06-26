package com.manage.tavern.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernByDataRoomVo;
import com.manage.tavern.po.TavernByDataRoom;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/8 20:41
 * @version:
 * @modified By:
 */
public interface TavernBydataRoomMapper extends BaseMapper<TavernByDataRoom> {

    /**
     * 宝寓财务清单查询
     * @param iPage
     * @param query
     * @return
     */
    List<TavernByDataRoomVo> getBycwDetails(IPage<TavernByDataRoomVo> iPage, RoomAuditQuery query);
}
