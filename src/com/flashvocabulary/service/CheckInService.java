package com.flashvocabulary.service;

import java.util.Date;

import com.flashvocabulary.dao.impl.CheckInDaoImpl;
import com.flashvocabulary.dto.CheckIn;

public class CheckInService {
	
	private CheckInDaoImpl checkInDao;
	
	public CheckInService()
	{
		checkInDao = new CheckInDaoImpl();
	}
	
	
	public boolean isFirstCheckIn(int uid)
	{
		int counts = checkInDao.getEntryCounts(uid, new Date());
		if (0 == counts)
		{
			return true;
		}
		else
			return false;
	}
	
	public void checkIn(int uid,String post)
	{
		CheckIn checkIn = new CheckIn(post,uid,new Date());
		try {
			checkInDao.saveEntry(checkIn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
