/**
 * 
 */
package com.manage.tavern.model.base;

import java.io.Serializable;
import java.util.List;


/**
 * @Author dll
 * @create 2020/3/24 15:54
 * @describe 通用分页返回对象
 */
public class PaginationModel<T> implements Serializable {

	private static final long serialVersionUID = -1207759792579934735L;

	/**第几页*/
	private Integer pageIndex;
	
	/**每页记录数*/
	private Integer pageSize;
	
	/**总记录数*/
	private Long count;
	
	/**数据集合*/
	private List<T> data;

	private String code;

	public PaginationModel() {
		super();
	}

	public PaginationModel(Integer pageIndex, Integer pageSize, Long total, List<T> list, String code) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.count = total;
		this.data = list;
		this.code = code;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
