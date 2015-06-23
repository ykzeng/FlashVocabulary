package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.List;
import java.sql.Date;

import com.flashvocabulary.dao.impl.CheckInDaoImpl;
import com.flashvocabulary.dao.impl.TodayWordDaoImpl;
import com.flashvocabulary.dao.impl.UserDaoImpl;
import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.UserLib;
import com.flashvocabulary.utils.IConstants;

public class TwelveClockService {
	
	private UserDaoImpl userDao;
	private CheckInDaoImpl checkInDao;
	private UserlibDaoImpl userlibDao;
	private TodayWordDaoImpl todayWordDao;
	
	public TwelveClockService()
	{
		userDao = new UserDaoImpl();
		checkInDao = new CheckInDaoImpl();
		userlibDao = new UserlibDaoImpl();
		todayWordDao = new TodayWordDaoImpl();
	}
	
	public void resetCheckIn()
	{
		ArrayList<User> userList = null;
		try {
			userList = (ArrayList<User>)userDao.getAllEntrys();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for (User user : userList)
		{
			int id = user.getId();
			int counts = checkInDao.getEntryCounts(id);
			if (counts == 0)
			{
				try {
					user.setCheckin(0);
					userDao.updateEntry(user);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void resetWordsNextDate()
	{
		ArrayList<UserLib> userLibList = null;
		try {
			userLibList = (ArrayList<UserLib>)userlibDao.getAllEntrys();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		for (UserLib userLib : userLibList)
		{
			int uid = userLib.getUid();
			int wid = userLib.getWid();
			TodayWord todayWord = todayWordDao.getEntryByUidAndWid(uid, wid);
			if (todayWord.getIsCheck() == IConstants.isCheckisFalse)
			{
				Date date = userLib.getNextDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, 1);
				userLib.setNextDate(new Date(calendar.getTime().getTime()));
				try {
					userlibDao.updateEntry(userLib);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	public void resetTodayWord()
	{
		ArrayList<TodayWord> todayWordList = null;
		try {
			todayWordList = (ArrayList<TodayWord>)todayWordDao.getAllEntrys();
			for (TodayWord todayWord : todayWordList)
			{
				todayWordDao.deleteEntry(todayWord.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
