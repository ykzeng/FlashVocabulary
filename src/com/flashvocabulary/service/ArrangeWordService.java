package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;

import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dao.impl.UserDaoImpl;
import com.flashvocabulary.dao.impl.TodayWordDaoImpl;

import com.flashvocabulary.dto.*;

public class ArrangeWordService {
	
	private UserlibDaoImpl userLibDao;
	private UserDaoImpl userDao;
	private TodayWordDaoImpl todayWordDao;
	
	public ArrangeWordService()
	{
		userLibDao = new UserlibDaoImpl();
		userDao = new UserDaoImpl();
		todayWordDao = new TodayWordDaoImpl();
	}
	
	public void ArrangeWord(Integer UserId) {
		ArrayList<UserLib> userLibList = userLibDao.getEntryByUserId(UserId);
		ArrayList<Integer> wordsList = new ArrayList<Integer>();
		int dailyCount = userDao.getDailyCount(UserId);
		int i=0;
		for (UserLib userLib : userLibList)
		{
			Date nextDate = userLib.getNextDate();
			if (nextDate.compareTo(new java.sql.Date(new java.util.Date().getTime())) == 0)
			{
				wordsList.add(userLib.getWid());
			}
			else
			{
				if (userLib.getStatus()==0 && i<dailyCount)
				{
					wordsList.add(userLib.getWid());
					i++;
				}
			}
		}
			
		Collections.shuffle(wordsList);
		
		for (Integer wordId : wordsList)
		{
			TodayWord todayWord = new TodayWord(UserId,wordId,0);
			try {
				todayWordDao.saveEntry(todayWord);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public Boolean isFirstLogin(Integer userId)
	{
		int counts = todayWordDao.getLibCount(userId);
		if (counts == 0)
			return true;
		else 
			return false;
	}
}
