package com.manage.tavern.service;

import com.manage.tavern.constant.ExportEnum;
import com.manage.tavern.model.form.TavernExportLogForm;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/6/17 11:15
 * @version:
 * @modified By:
 */
public interface TavernExcelService {


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
     * 导入成功后更新成功失败总条目数
     * @param id
     * @param gridExportLogForm
     */
    void addEsuc(Long id, TavernExportLogForm gridExportLogForm);

}
