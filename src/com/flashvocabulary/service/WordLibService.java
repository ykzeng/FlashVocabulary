package com.flashvocabulary.service;

import java.util.ArrayList;
import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordlibDaoImpl;
import com.flashvocabulary.dto.UserLib;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.Wordlib;

public class WordLibService {
	private WordlibDaoImpl wordlibDao = new WordlibDaoImpl();
	private UserlibDaoImpl userlibDao = new UserlibDaoImpl();
	private WordDaoImpl wordDao = new WordDaoImpl();
	private UserInfoService userInfoService = new UserInfoService();
	
	/**
	 * 获取词库名
	 * @param libID.
	 * @return lib名
	 */
	public String getLibNameByLibid(int libId)   //@代号：ljt 
	 
	{
		return wordlibDao.getLibNameByLibid(libId);
	}
	
	public ArrayList<Wordlib> getAllWordLib()
	{
		ArrayList<Wordlib> wordlibList = null;
		try {
			wordlibList = (ArrayList<Wordlib>)wordlibDao.getEntrys("select * from tb_wordlib where id != 12");	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wordlibList;
	}
	 
	public int getLibCountById(int libId)
	{
		return wordDao.getLibCountById(libId);
	}
	
	
	public void setUserLib(int uid,int libId)
	{
		ArrayList<UserLib> userLibsList = new ArrayList<UserLib>();
		userLibsList = userlibDao.getEntrysByUserId(uid);
		
		for (UserLib userLib : userLibsList)
		{
			try {
				userlibDao.deleteEntry(userLib.getId());
			} catch (Exception e) {
				
			}
		}
		
		ArrayList<Word> wordsList = wordDao.getWordsByLibId(libId);
		for (Word word : wordsList)
		{
			UserLib userLib = new UserLib(uid,word.getId(),0);
			try {
				userlibDao.saveEntry(userLib);	
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		userInfoService.setUserCurrentLib(uid, libId);
		
		TwelveClockService twelveClockService = new TwelveClockService();
		twelveClockService.resetOneUserTodayWord(uid);
		ArrangeWordService arrangeWordService  =new ArrangeWordService();
		arrangeWordService.ArrangeWord(uid);
	}
}
