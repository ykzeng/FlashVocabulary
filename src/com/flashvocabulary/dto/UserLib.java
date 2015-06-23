package com.flashvocabulary.dto;

import java.sql.Date;

import com.flashvocabulary.utils.IConstants;

public class UserLib implements BaseEntry{

	private long id;
	private int uid;
	private int wid;
	private int status;
	private Date firstDate;
	private Date nextDate;

	public UserLib() {
		
	}
	
	public UserLib(int uid, int wid, int status) {
		this.uid = uid;
		this.wid = wid;
		this.status = status;
		this.firstDate = IConstants.defaultDate;
		this.nextDate = IConstants.defaultDate;
	}
	
	public Date getNextDate() {
		return nextDate;
	}
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
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
		return "tb_userlib";
	}
}
