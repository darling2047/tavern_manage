package com.manage.tavern.service;

import com.manage.tavern.model.base.MenuInfoModel;
import com.manage.tavern.po.sys.DmSysUser;
import com.manage.tavern.po.sys.DmSysUserRole;

import java.util.List;

/**
 * @Author dll
 * @create 2020/5/31 20:28
 * @describe 系统常用功能 SERVICE
 */
public interface ISystemService {

    /**
     * 根据登录名获取用户信息
     * @param loginName
     * @return
     */
    DmSysUser getUserByLoginName(String loginName);

    /**
     * 根据用户id获取该用户能看的菜单
     * @param userId
     * @return
     */
    List<MenuInfoModel> getMenus(Integer userId);

    /**
     * 根据用户id获取用户的角色
     *      目前仅支持用户和角色一对一的关系 所以在配置角色的时候
     *  只需配置该人员权限最大的角色即可
     * @param userId
     * @return
     */
    List<DmSysUserRole> getRolesByUserId(Integer userId);


    /**
     * 权限校验 判断当前登录人是否有操作此功能的权限
     * @param url
     */
    void checkAuthority(String url);
}
