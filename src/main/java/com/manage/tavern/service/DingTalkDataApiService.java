package com.manage.tavern.service;

import com.manage.tavern.po.TavernDingProData;
import com.manage.tavern.po.TavernDingSkTableData;

import java.util.Date;
import java.util.List;

/**
 * @description: 钉钉api对接
 * @author: dll
 * @date: Created in 2023/5/4 15:42
 * @version:
 * @modified By:
 */
public interface DingTalkDataApiService {


    /**
     * 获取给定时间范围内指定表单下的所有流程实例
     * @param startDate
     * @param endDate
     * @param formId
     * @param type  审批类型 1:收款审批 2:财务报销
     * @return
     */
    void getProInstanceIds(Date startDate, Date endDate, String formId,Integer type,String month);

    /**
     * 获取指定实例的详情
     * @param processInstanceId
     */
    List<TavernDingSkTableData> getProcessByInstanceId(String processInstanceId,Integer approvalType,String month);


    /**
     * 根据请求日志重新解析钉钉报文 更新数据
     */
    void updateDingForm();
}
