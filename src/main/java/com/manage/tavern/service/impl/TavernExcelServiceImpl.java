package com.manage.tavern.service.impl;

import com.manage.tavern.constant.ExportEnum;
import com.manage.tavern.mapper.sys.TavernExportLogMapper;
import com.manage.tavern.model.form.TavernExportLogForm;
import com.manage.tavern.po.sys.TavernExportLog;
import com.manage.tavern.service.TavernExcelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/6/17 11:15
 * @version:
 * @modified By:
 */
@Service
public class TavernExcelServiceImpl implements TavernExcelService {


    @Resource
    private TavernExportLogMapper tavernExportLogMapper;

    @Override
    public Long addExcelLog(TavernExportLogForm gridExportLogForm) {
        TavernExportLog gridExportLog = new TavernExportLog();
        BeanUtils.copyProperties(gridExportLogForm, gridExportLog);
        Long id = System.currentTimeMillis();
        gridExportLog.setId(id);
        gridExportLog.setCreateTime(new Date());
        tavernExportLogMapper.insert(gridExportLog);
        return id;
    }

    @Override
    public void addEsuc(Long id, TavernExportLogForm tavernExportLogForm) {
        tavernExportLogForm.setStatus(ExportEnum.ESUC.getCode());
        TavernExportLog tavernExportLog = new TavernExportLog();
        BeanUtils.copyProperties(tavernExportLogForm, tavernExportLog);
        tavernExportLog.setId(id);
        tavernExportLog.setUpdateTime(new Date());
        tavernExportLogMapper.updateById(tavernExportLog);
    }
}
