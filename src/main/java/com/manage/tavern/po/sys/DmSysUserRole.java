package com.manage.tavern.po.sys;

import lombok.Data;

/**
 * @Author dll
 * @create 2020/5/29 22:28
 * @describe 用户与角色关系表 DM_SYS_USER_ROLE
 */
@Data
public class DmSysUserRole {

    private Integer userId;

    private String roleId;

}