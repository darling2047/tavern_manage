package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.mapper.sys.SysMenuMapper;
import com.manage.tavern.mapper.sys.SysUserMapper;
import com.manage.tavern.mapper.sys.SysUserRoleMapper;
import com.manage.tavern.model.base.MenuInfoModel;
import com.manage.tavern.po.sys.DmSysMenu;
import com.manage.tavern.po.sys.DmSysUser;
import com.manage.tavern.po.sys.DmSysUserRole;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.ISystemService;
import com.manage.tavern.utils.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author dll
 * @create 2020/5/31 20:28
 * @describe
 */
@Service
public class SystemServiceImpl implements ISystemService {

    @Resource
    SysUserMapper userMapper;

    @Resource
    SysMenuMapper menuMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public DmSysUser getUserByLoginName(String loginName) {
        QueryWrapper<DmSysUser> qw = new QueryWrapper<>();
        qw.lambda().eq(DmSysUser::getLoginName, loginName);
        List<DmSysUser> users = userMapper.selectList(qw);
        if (users.isEmpty()) {
            return null;
        }
        return users.stream().findFirst().get();
    }

    @Override
    public List<MenuInfoModel> getMenus(Integer userId) {
        List<MenuInfoModel> res = new ArrayList<>(16);
        // 先查出当前登录人能看到的根菜单
        List<DmSysMenu> rootMenus = menuMapper.getMenus(userId, "0");
        rootMenus.stream().forEach(root -> {
            MenuInfoModel model = getModel(root,userId);
            res.add(model);
        });
        return res;
    }

    /**
     * 获取菜单对象
     * @param menu
     * @param userId
     * @return
     */
    private MenuInfoModel getModel(DmSysMenu menu,Integer userId) {
        List<MenuInfoModel> subs = new ArrayList<>(16);
        MenuInfoModel model = new MenuInfoModel();
        model.setIcon(menu.getIcon());
        model.setTitle(menu.getName());
        model.setIndex(menu.getUrl());
        if (Objects.equals(menu.getLeaf(), CommonConstant.MENU_LEAF)) {
            // 如果菜单本身就是叶子节点则加入菜单列表后返回即可
            return model;
        }
        // 根据根菜单获取其下的二级菜单
        List<DmSysMenu> menus = menuMapper.getMenus(userId, String.valueOf(menu.getMenuId()));
        if (menus.isEmpty()) {
            return model;
        }
        for (DmSysMenu sysMenu : menus) {
            MenuInfoModel childMenu = getModel(sysMenu,userId);
            subs.add(childMenu);
        }
        model.setSubs(subs);
        return model;
    }

    @Override
    public List<DmSysUserRole> getRolesByUserId(Integer userId) {
        List<DmSysUserRole> roles = sysUserRoleMapper.selectList(new QueryWrapper<DmSysUserRole>().lambda()
                .eq(DmSysUserRole::getUserId, userId));

        return roles;
    }

    @Override
    public void checkAuthority(String url) {
        url = url.substring(url.lastIndexOf("/"));
        UserInfoModel user = TokenUtil.getUser();
        String roleId = user.getRoleIds().stream().findFirst().orElse(null);
//        if (Objects.equals(CommonConstant.ROLE_TYPE.SUB_ADMINISTRATOR, roleId)) {
//            String finalUrl = url;
//            CommonConstant.UPDATELIST.stream().forEach(item -> {
//                if (finalUrl.contains(item)) {
//                    throw new BusinessException("子管理员无权进行当前操作!");
//                }
//            });
//        }
//        if (Objects.equals(CommonConstant.ROLE_TYPE.DEPT_DEVICE_ADMINISTRATOR, roleId)) {
//            String finalUrl = url;
//            CommonConstant.DEPTURLLIST.stream().forEach(item -> {
//                if (finalUrl.contains(item)) {
//                    throw new BusinessException("部门设备管理员无权进行当前操作!");
//                }
//            });
//        }
    }

}
