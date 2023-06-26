/**
 * 
 */
package com.manage.tavern.model.base;


import lombok.Data;

/**
 * @Author dll
 * @create 2020/3/24 15:54
 * @describe 分页入参基类
 */
@Data
public class PageBaseModel {

	/**
     * 第几页
     */
    private Integer currentPageNo = 1;

    /**
     * 每页显示几行
     */
    private Integer pageSize = 15;

}

