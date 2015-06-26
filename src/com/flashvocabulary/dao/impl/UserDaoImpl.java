package com.flashvocabulary.dao.impl;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements BaseDao<User>{
	
	/**
	 * 通过账户名查找用户
	 * @param 用户name.
	 * @return 用户类
	 */
	public User getEntryByUname(String username)   //@代号：ljt 
	{
		User userFromDB = new User();
		try {
			userFromDB = (User)getEntry("select * from tb_user where uname = ?", username);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userFromDB;
	}
	
	public boolean isAcountExisted(String username)   //@代号：ljt 
	{
		try {
			int a = getEntrys("select * from tb_user where uname = ?", username.trim()).size();
			return a>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getDailyCount(Integer userId)
	{
		String sql = "select dailyCount from tb_user where id = ?";
		Object ret = null;
		try
		{
			ret =  super.excSql_retValue(sql, new ScalarHandler("dailyCount"), userId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return Integer.parseInt(ret.toString());
	}
}

