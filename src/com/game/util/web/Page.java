package com.game.util.web;

import java.util.List;

/**
 * @name 分页类
 * 
 */
public class Page<T> {
	private int currentPage;
	private int maxPage;
	private List<Integer> pageNum;
	private List<T> resultlist;
	public Page() {
	}
	/**
	 * @param currentPage 当前页
	 * @param maxPage 最大页
	 * @param pageNum 页码数组
	 * @param resultlist 数据集
	 */
	public Page(int currentPage, int maxPage, List<Integer> pageNum, List<T> resultlist) {
		this.currentPage = currentPage;
		this.maxPage = maxPage;
		this.pageNum = pageNum;
		this.resultlist = resultlist;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	public void setPageNum(List<Integer> pageNum) {
		this.pageNum = pageNum;
	}

	public List<Integer> getPageNum() {
		return pageNum;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
