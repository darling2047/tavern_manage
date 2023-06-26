package com.manage.tavern.po.sys;

import lombok.Data;

import java.util.Date;

/**
 * @Author dll
 * @create 2020/5/29 22:28
 * @describe 菜单信息表 DM_SYS_MENU
 */
@Data
public class DmSysMenu {

    private Integer menuId;

    private String name;

    private String parentId;

    private String url;

    private Integer outsideType;

    private Integer orderId;

    private Integer status;

    private String creator;

    private Date createTime;

    private String lastUpdator;

    private Date lastUpdateTime;

    /**
     * 是否叶子节点(1:是;0:否)
     */
    private Integer leaf;

    /**
     * 菜单icon
     */
    private String icon;
}