package com.flashvocabulary.dto;

public class TodayWord implements BaseEntry {
	
	private int id;
	private int uid;
	private int wid;
	private int isCheck;
	
	public TodayWord(int id,int uid,int wid,int isCheck)
	{
		this.id = id;
		this.uid = uid;
		this.wid = wid;
		this.isCheck = isCheck;
	}
	public TodayWord(){}
	
	public TodayWord(int uid,int wid,int isCheck)
	{
		this.uid = uid;
		this.wid = wid;
		this.isCheck = isCheck;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}

	@Override
	public Boolean isPK_Increment() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_todayword";
	}

}
