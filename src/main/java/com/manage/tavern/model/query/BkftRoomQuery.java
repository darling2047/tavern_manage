package com.manage.tavern.model.query;

import com.manage.tavern.model.base.PageBaseModel;
import lombok.Data;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/7 19:43
 * @version:
 * @modified By:
 */
@Data
public class BkftRoomQuery extends PageBaseModel {

    private String roomName;

    private String month;

    private String tgfd;

    private String area;

}
