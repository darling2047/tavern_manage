package com.manage.tavern.po.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author dll
 * @create 2020/5/29 22:28
 * @describe 部门信息表 DM_SYS_DEPT
 */
@Data
public class DmSysDept {

    @TableId
    private String deptId;

    private String deptName;

    private String parentId;

    private String creator;

    private Date createTime;

    private String isLeaf;
}