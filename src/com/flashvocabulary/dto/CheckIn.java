package com.flashvocabulary.dto;

import java.sql.Timestamp;

public class CheckIn implements BaseEntry{
	
	private int id;
	private String post;
	private int uid;
	private Timestamp time;
	
	public CheckIn() {}
	
	public CheckIn(String post, int uid, Timestamp time)
	{
		this.post = post;
		this.uid = uid;
		this.time = time;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
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
