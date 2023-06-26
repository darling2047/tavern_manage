package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author   dll
 * @create   2023/5/4 17:59
 */
@AllArgsConstructor
@NoArgsConstructor
public enum DingApiUrlEnum {


    DING_INSTANCEIDS_URL("获取审批实例ID列表", "/v1.0/workflow/processes/instanceIds/query"),
    DING_INSTANCE_DETAILS_URL("获取单个审批实例详情", "/v1.0/workflow/processInstances");


    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
