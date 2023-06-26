package com.manage.tavern.service;

import com.manage.tavern.po.sys.TavernSysLog;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/20 20:34
 * @version:
 * @modified By:
 */
public interface SysLogService {

    /**
     * 保存操作日志
     * @param sysLog
     */
    void save(TavernSysLog sysLog);
}
