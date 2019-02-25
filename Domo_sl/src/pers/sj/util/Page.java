package pers.sj.util;

import java.util.ArrayList;
import java.util.List;

public class Page {
	//保存的数据
	private List<Object> items=new ArrayList<Object>();
	//总页数
	private Integer pageCount;
	//当前页
	private Integer page;
	
	private Integer pageSize;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<Object> getItems() {
		return items;
	}
	public void setItems(List<Object> items) {
		this.items = items;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
