package com.manage.tavern.service;

import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.SelectData;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/7 19:45
 * @version:
 * @modified By:
 */
public interface RoomAuditService {

    /**
     * 分页获取结算清单
     * @param query
     * @return
     */
    PaginationModel<RoomAuditInfo> getList(RoomAuditQuery query);

    /**
     * 获取下拉列表数据
     * @param name
     * @param type
     * @return
     */
    List<SelectData> getSelectData(String name, Integer type);
}
