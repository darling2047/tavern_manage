package com.manage.tavern.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.tavern.po.sys.DmSysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author dll
 * @create 2020/6/2 21:58
 * @describe
 */
public interface SysMenuMapper extends BaseMapper<DmSysMenu> {

    /**
     * 根据菜单父id和用户id获取菜单
     * @param userId
     * @param parentId
     * @return
     */
    List<DmSysMenu> getMenus(@Param(value = "userId") Integer userId, @Param(value = "parentId") String parentId);

    /**
     * 获取最后一次登录时间
     * @param loginName
     * @return
     */
    String getLastLoginTime(@Param(value = "loginName") String loginName);
}
