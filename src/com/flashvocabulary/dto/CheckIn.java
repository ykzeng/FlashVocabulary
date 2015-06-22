package com.flashvocabulary.dto;

import java.util.Date;

public class CheckIn implements BaseEntry{
	
	private int id;
	private String post;
	private int uid;
	private Date date;
	
	public CheckIn() {}
	
	public CheckIn(String post, int uid, Date date)
	{
		this.post = post;
		this.uid = uid;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPrimaryKey() {	
		return "id";
	}
	@Override
	public Boolean isPK_Increment() {
		return true;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_checkin";
	}
	
}
