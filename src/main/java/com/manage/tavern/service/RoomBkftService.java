package com.manage.tavern.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.excel.RoomBkftExcelModel;
import com.manage.tavern.model.query.BkftRoomQuery;
import com.manage.tavern.model.vo.RoomBkftVo;
import com.manage.tavern.po.TavernBkftDetailsLog;
import com.manage.tavern.po.TavernRoomBkft;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DLL
 * @since 2023-06-16
 */
public interface RoomBkftService extends IService<TavernRoomBkft> {

    /**
     * 不可分摊费用列表查询
     * @param query
     * @return
     */
    PaginationModel<RoomBkftVo> getList(BkftRoomQuery query);

    /**
     * 保存不可分摊费用
     * @param item
     */
    void saveBkftFee(RoomBkftExcelModel item);

    /**
     * 保存用户导入的清单数据
     * @param exportIdLocal
     * @param logList
     */
    void saveExcelDetailLogs(ThreadLocal<Long> exportIdLocal, List<TavernBkftDetailsLog> logList);
}
