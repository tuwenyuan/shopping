package com.twy.web.bean;

import java.util.List;

public class Page {
	private List records;//每页显示的记录
	private int pagenum;//当前页码
	private int pagesize=3;//每页显示的条数
	private int startindex;//每页开始记录的索引
	private int totalpage;//总页数
	private int totalrecords;//总记录数
	
	private String url;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page(int pagenum,int totalrecords){
		this.pagenum = pagenum;
		this.totalrecords = totalrecords;
		//算开始记录的索引
		startindex = (pagenum-1)*pagesize;
		//计算总页数
		totalpage = (totalrecords%pagesize==0)?totalrecords/pagesize:(totalrecords/pagesize+1);
	}
	
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStartindex() {
		return startindex;
	}
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalrecords() {
		return totalrecords;
	}
	public void setTotalrecords(int totalrecords) {
		this.totalrecords = totalrecords;
	}
	
	
}
