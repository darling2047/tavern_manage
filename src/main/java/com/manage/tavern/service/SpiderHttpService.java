package com.manage.tavern.service;

import com.manage.tavern.model.form.ByDataRoomForm;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/9 8:55
 * @version:
 * @modified By:
 */
public interface SpiderHttpService {

    /**
     * 爬取宝寓房间数据
     * @param form
     */
    void spiderByDataRoom(ByDataRoomForm form);

    /**
     * 爬取宝寓保洁数据
     * @param form
     */
    void byBjDataSync(ByDataRoomForm form);

    /**
     * 每月第一天凌晨3店获取当月钉钉财务报销流程下的所有实例
     * @param formId 钉钉对应流程唯一标识
     *               财务：PROC-DBE92E71-EFF7-4D44-B389-C289907EA27D
     */
    void doDingProIdsTask(String formId);

    /**
     * 每月第一天凌晨6点获取当月钉钉财务报销流程下的所有实例详情
     */
    void dingProDetailsTask();
}
