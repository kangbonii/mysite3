package com.javaex.vo;

import java.sql.Date;

public class RboardVo {
	private int no;
	private int user_no;
	private String title;
	private String content;
	private int hit;
	private Date reg_date;
	private int group_no;
	private int order_no;
	private int depth;
	private String state;
	private String name;

	
	
	
	public RboardVo() {
		super();
	}
	
	
	
	public RboardVo(int no, int user_no, String title, String content, int hit, Date reg_date, int group_no,
			int order_no, int depth, String state) {
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.state = state;
	}

	public RboardVo(int no, int user_no, String title, String content, int hit, Date reg_date, int group_no,
			int order_no, int depth, String state, String name) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.state = state;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", user_no=" + user_no + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no=" + order_no + ", depth="
				+ depth + ", state=" + state + ", name=" + name + "]";
	}
	
	
	
	
}
