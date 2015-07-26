package com.cx.oa.yjsy.base;

import java.util.List;

public class Page<T> {
	private List<T> data;
	private int currentPage;
	private int pageNumShown=5;
	private int numPerPage;
	private int totalCount;
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageNumShown() {
		return pageNumShown;
	}
	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public Page() {
	}
	public Page(List<T> data, int currentPage,int numPerPage, int totalCount) {
		this.data = data;
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "Page [data=" + data + ", currentPage=" + currentPage
				+ ", pageNumShown=" + pageNumShown + ", numPerPage="
				+ numPerPage + ", totalCount=" + totalCount + "]";
	}
	
	
}
