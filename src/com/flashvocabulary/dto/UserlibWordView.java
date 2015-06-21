package com.flashvocabulary.dto;

public class UserlibWordView implements BaseEntry
{
	private int wid;
	private int lib_id;
	private int status;
	private int uid;
	
	public UserlibWordView(){}
	
	public UserlibWordView(int wid,int lib_id,int status)
	{
		this.wid = wid;
		this.lib_id = lib_id;
		this.status = status;
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

	public int getLib_id() {
		return lib_id;
	}

	public void setLib_id(int lib_id) {
		this.lib_id = lib_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPK_Increment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "tb_UserlibWordView";
	}
	
}