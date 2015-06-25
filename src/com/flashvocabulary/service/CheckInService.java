package com.flashvocabulary.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

import com.flashvocabulary.dao.impl.CheckInDaoImpl;
import com.flashvocabulary.dao.impl.UserDaoImpl;
import com.flashvocabulary.dto.CheckIn;
import com.flashvocabulary.dto.User;

public class CheckInService {
	
	private CheckInDaoImpl checkInDao;
	private UserDaoImpl userDao;
	
	public CheckInService()
	{
		checkInDao = new CheckInDaoImpl();
		userDao = new UserDaoImpl();
	}
	
	
	public boolean isFirstCheckIn(int uid)
	{
		int counts = checkInDao.getEntryCounts(uid);
		if (0 == counts)
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean isTodayCheckedIn(int uid,Timestamp time)
	{
		try {
			int count = 0;
			count = checkInDao.getEntrys("select * from tb_checkin where uid = ? " +
					"and CONVERT(varchar(100), time, 23) = ? ", uid,getTime(time)).size();
			if(count==1)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void checkIn(int uid,String post)
	{
		CheckIn checkIn = new CheckIn(post,uid,new Timestamp(System.currentTimeMillis()));
		try {
			checkInDao.saveEntry(checkIn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			User user = userDao.getEntry(uid);
			user.setCheckin(user.getCheckin()+1);
			userDao.updateEntry(user);
		} catch (Exception e) {
				// TODO: handle exception
		}
	}
	
	public int getCheckinDays(int uid)
	{
		try {
			int days = checkInDao.getCheckinDays(uid);
			return days;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<CheckIn> getAllCheckIn(int uid)
	{
		return checkInDao.getAllEntryByUserId(uid);
	}
	
	public String getTime(Timestamp time)
	{
		Date currentTime = time;
		    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(currentTime);
		return dateString;
	}
}
