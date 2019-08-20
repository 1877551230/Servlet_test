package cn.tedu.vo;

import java.util.Arrays;
import java.util.List;

/**
 * VO类,是程序中临时用的数据的载体
 * @author tedu
 *
 */
public class Page<T> {
	
	private int currentPage;//当前页
	private int pageSize;//每页多少条数据
	private int previousPage;//前一页
	private int nextPage;//后一页
	private int totalCount;//总记录数
	private int totalPage;//总页数
	
	private List<T> data;//当前页的那些数据
	
	private String[] keywords;//模糊的关键字

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", previousPage=" + previousPage
				+ ", nextPage=" + nextPage + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", data="
				+ data + ", keywords=" + Arrays.toString(keywords) + "]";
	}

	
}
