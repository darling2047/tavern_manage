package com.manage.tavern.po.sys;

import lombok.Data;

/**
 * @Author dll
 * @create 2020/5/29 22:28
 * @describe 角色与菜单关系表 DM_SYS_ROLE_MENU
 */
@Data
public class DmSysRoleMenu {

    private Integer menuId;

    private String roleId;

}