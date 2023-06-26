package com.manage.tavern.service;

import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernByBjDataVo;
import com.manage.tavern.model.vo.TavernByDataRoomVo;
import com.manage.tavern.model.vo.TavernDingBxDataVo;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/16 17:46
 * @version:
 * @modified By:
 */
public interface RoomAuditDeatilService {

    /**
     * 获取保洁清单数据
     * @param query
     * @return
     */
    PaginationModel<TavernByBjDataVo> getBjDetails(RoomAuditQuery query);

    /**
     * 获取钉钉报销品类清单
     * @param query
     * @return
     */
    PaginationModel<TavernDingBxDataVo> getDingBxDetails(RoomAuditQuery query);

    /**
     * 宝寓财务清单查询
     * @param query
     * @return
     */
    PaginationModel<TavernByDataRoomVo> getBycwDetails(RoomAuditQuery query);
}
