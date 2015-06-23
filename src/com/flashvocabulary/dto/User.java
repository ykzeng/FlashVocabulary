package com.flashvocabulary.dto;

public class User implements BaseEntry{

	
	private int id= 0;
	private String uname;
	private String pwd;
	private int privilege_id = 0;

	private int dailyCount = 0;
	private int currentLib = 0;
	private int checkin = 0;
	
	public int getCheckin() {
		return checkin;
	}

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	public int getCurrentLib() {
		return currentLib;
	}

	public void setCurrentLib(int currentLib) {
		this.currentLib = currentLib;
	}

	public int getDailyCount() {
		return dailyCount;
	}

	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}

	public User(){}
	
	public User(String username,String password)
	{
		this.uname = username;
		this.pwd = password;
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(int privilege_id) {
		this.privilege_id = privilege_id;
	}
	
	@Override
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
		return "tb_user";
	}
	
	
	
}
