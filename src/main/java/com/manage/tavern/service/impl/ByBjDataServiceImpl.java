package com.manage.tavern.service.impl;

import com.manage.tavern.mapper.ByBjDataMapper;
import com.manage.tavern.po.TavernByBjData;
import com.manage.tavern.service.ByBjDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/8 16:27
 * @version:
 * @modified By:
 */
@Service
public class ByBjDataServiceImpl implements ByBjDataService {

    @Resource
    private ByBjDataMapper byBjDataMapper;

    @Override
    public void save(TavernByBjData bjData) {
        byBjDataMapper.insert(bjData);
    }
}
