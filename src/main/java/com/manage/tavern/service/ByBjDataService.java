package com.manage.tavern.service;

import com.manage.tavern.po.TavernByBjData;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/8 16:27
 * @version:
 * @modified By:
 */
public interface ByBjDataService {

    /**
     * 保存报结数据
     * @param bjData
     */
    void save(TavernByBjData bjData);
}
