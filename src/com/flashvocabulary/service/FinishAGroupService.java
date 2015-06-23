package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import com.flashvocabulary.dao.impl.TodayWordDaoImpl;
import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.UserLib;
import com.flashvocabulary.utils.*;

public class FinishAGroupService {
	
	private TodayWordDaoImpl todayWordDao;
	private UserlibDaoImpl userlibDao;
	
	public FinishAGroupService()
	{
		todayWordDao = new TodayWordDaoImpl();
		userlibDao = new UserlibDaoImpl();
	}
	
	public void writeToTodayWords(ArrayList<TodayWord> wordsList)
	{
		try {
			for (TodayWord tw : wordsList)
			{
				todayWordDao.updateEntry(tw);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void writeToUserLib(ArrayList<TodayWord> wordsList)
	{
		for (TodayWord tw : wordsList)
		{
			int uid = tw.getUid();
			int wid = tw.getWid();
			UserLib userLib = userlibDao.getEntryByUidAndWid(uid, wid);
			
			if (tw.getIsCheck() == IConstants.isCheckisTrue)
			{
				if (userLib.getStatus() == 0)
					userLib.setFirstDate(new java.sql.Date(new java.util.Date().getTime()));
				
				int status = userLib.getStatus();
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.sql.Date(new java.util.Date().getTime()));
				cal.add(Calendar.DATE, IConstants.reciteIntervals[status]);
				userLib.setNextDate(new Date(cal.getTime().getTime()));
				userLib.setStatus(status+1);
				try {
					userlibDao.updateEntry(userLib);	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
