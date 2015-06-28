package com.flashvocabulary.service;

import com.flashvocabulary.dao.impl.UserDaoImpl;
import com.flashvocabulary.dto.User;

public class UserInfoService {
	private UserDaoImpl userDao=new UserDaoImpl();
	public User userRegister(User user)  //@代号：ljt 
	{
		try {
			userDao.saveEntry(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLogin(user);
	}
	
	public User userLogin(User user)  //@代号：ljt 
	{
		String username = "";
		String password = "";
		username = user.getUname();
		password = user.getPwd();
		User userFromDB = userDao.getEntryByUname(username);
		if(username.equals(userFromDB.getUname()) && password.equals(userFromDB.getPwd()))
		{
			return userFromDB;
		}
		return null;
	}
	
	public User getCurrentUserInfo(int uid)
	{
		try {
			return userDao.getEntry(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isAcountExisted(String username)
	{
		return userDao.isAcountExisted(username);
	}
	
	public int modifyUserPassword(int uid,String originalPWD,String newPWD)
	{
		try {
			User u = userDao.getEntry(uid);
			if(u.getPwd().equals(originalPWD))
			{
				u.setPwd(newPWD);
				userDao.updateEntry(u);
				return 1;
			}
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
		
	public void setDailyNewWordCount(int uid,int dailyCount)
	{
		try {
			User u = userDao.getEntry(uid);
			u.setDailyCount(dailyCount);
			userDao.updateEntry(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getDailyNewWordCount(int uid)
	{
		try {
			User u = userDao.getEntry(uid);
			return u.getDailyCount();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setUserCurrentLib(int uid,int newLib)
	{
		try {
			User u = userDao.getEntry(uid);
			u.setCurrentLib(newLib);
			userDao.updateEntry(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getContinueCheckinDays(int uid)
	{
		try {
			int days = userDao.getEntry(uid).getCheckin();
			return days;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
