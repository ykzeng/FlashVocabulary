package com.flashvocabulary.dto;

public class UserCollectLib implements BaseEntry {
	
	private long id;
	private int uid;
	private int wid;
	
	public UserCollectLib(){}
	
	public UserCollectLib(int uid,int wid)
	{
		this.uid = uid;
		this.wid = wid;
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
		return "tb_usercollectlib";
	}

}
