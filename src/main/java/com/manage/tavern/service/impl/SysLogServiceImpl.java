package com.manage.tavern.service.impl;

import com.manage.tavern.mapper.sys.TavernSysLogMapper;
import com.manage.tavern.po.sys.TavernSysLog;
import com.manage.tavern.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/20 20:34
 * @version:
 * @modified By:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private TavernSysLogMapper tavernSysLogMapper;

    @Override
    public void save(TavernSysLog sysLog) {
        tavernSysLogMapper.insert(sysLog);
    }
}
