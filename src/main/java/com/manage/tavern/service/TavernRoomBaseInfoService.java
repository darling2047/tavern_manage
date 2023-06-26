package com.manage.tavern.service;

import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.form.RoomBaseInfoForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomBaseInfoVo;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/21 19:52
 * @version:
 * @modified By:
 */
public interface TavernRoomBaseInfoService {

    /**
     * 房屋信息查询
     * @param query
     * @return
     */
    PaginationModel<RoomBaseInfoVo> getList(RoomAuditQuery query);

    /**
     * 房屋信息编辑
     * @param form
     */
    void edit(RoomBaseInfoForm form);

    /**
     * 房屋信息删除
     * @param id
     */
    void delRoomById(Integer id);
}
