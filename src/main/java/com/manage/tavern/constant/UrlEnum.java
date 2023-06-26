package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Author   dll
 * @create   2023/4/9 09:09
 */
@AllArgsConstructor
@NoArgsConstructor
public enum UrlEnum {


    BY_CW_ROOM_URL("财务_按房间", "console/report/get"),
    BY_BY_DATA_URL("宝寓保洁数据", "console/room_task/");


    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
