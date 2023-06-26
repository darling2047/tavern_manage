package com.manage.tavern.model.base;

import lombok.Data;

import java.util.List;

/**
 * @Author dll
 * @create 2021/3/6 15:55
 * @describe 菜单信息model
 */
@Data
public class MenuInfoModel {

    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 菜单路由
     */
    private String index;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 子菜单列表
     */
    private List<MenuInfoModel> subs;


}
