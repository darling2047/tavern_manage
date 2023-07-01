package com.manage.tavern.service;

import com.manage.tavern.constant.ExportEnum;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.excel.TavernAuditResultExcelModel;
import com.manage.tavern.model.form.TavernAuditResultForm;
import com.manage.tavern.model.form.TavernExportLogForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernAuditResultVo;
import com.manage.tavern.model.vo.TavernOverviewInfo;
import com.manage.tavern.po.TarvenResultDetailsLog;

import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/29 21:48
 * @version:
 * @modified By:
 */
public interface TavernAuditResultService {

    /**
     * 清算结果查询
     * @param query
     * @return
     */
    PaginationModel<TavernAuditResultVo> getList(RoomAuditQuery query);

    /**
     * 跑指定月份的清算结果
     * @param month
     * @param source
     */
    void insertResult(String month,String source);

    /**
     * 修改清算结果
     * @param params
     */
    void update(TavernAuditResultForm params);

    /**
     * 记录导入日志
     * @param gridExportLogForm
     * @return
     */
    default Long addInit(TavernExportLogForm gridExportLogForm){
        gridExportLogForm.setStatus(ExportEnum.INIT.getCode());
        return addExcelLog(gridExportLogForm);
    }

    /**
     * 记录导入日志
     * @param gridExportLogForm
     * @return
     */
    Long addExcelLog(TavernExportLogForm gridExportLogForm);


    /**
     * 修改结算清单
     * @param item
     */
    void updateExportResult(TavernAuditResultExcelModel item);

    /**
     * 保存导入详情日志
     * @param exportIdLocal
     * @param logList
     */
    void saveExcelDetailLogs(ThreadLocal<Long> exportIdLocal, List<TarvenResultDetailsLog> logList);

    /**
     * 保存导入日志
     * @param id
     * @param gridExportLogForm
     */
    void addEsuc(Long id, TavernExportLogForm gridExportLogForm);

    /**
     * 获取清算结果最近跟新日期
     * @param query
     * @return
     */
    String getLastUpdate(RoomAuditQuery query);

    /**
     * 总览查询
     * @param query
     * @return
     */
    TavernOverviewInfo getOverview(RoomAuditQuery query);
}
