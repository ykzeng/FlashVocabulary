package com.flashvocabulary.service;

import java.util.ArrayList;

import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordlibDaoImpl;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.UserLib;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.Wordlib;

public class ChooseWordLib {
	
	private WordlibDaoImpl wordlibDao;
	private UserlibDaoImpl userlibDao;
	private WordDaoImpl wordDao;
	
	public ChooseWordLib()
	{
		wordlibDao = new WordlibDaoImpl();
		userlibDao = new UserlibDaoImpl();
		wordDao = new WordDaoImpl();
	}
	
	public ArrayList<Wordlib> getAllWordLib()
	{
		ArrayList<Wordlib> wordlibList = null;
		try {
			wordlibList = (ArrayList<Wordlib>)wordlibDao.getAllEntrys();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wordlibList;
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
				// TODO: handle exception
			}
		}
		
		ArrayList<Word> wordsList = wordDao.getWordsByLibId(libId);
		for (Word word : wordsList)
		{
			UserLib userLib = new UserLib(uid,word.getId(),0);
			try {
				userlibDao.saveEntry(userLib);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
	}
	 
}
