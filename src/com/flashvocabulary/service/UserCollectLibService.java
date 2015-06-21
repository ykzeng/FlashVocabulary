package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.List;

import com.flashvocabulary.dao.impl.UserCollectLibImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dto.UserCollectLib;
import com.flashvocabulary.dto.Word;

public class UserCollectLibService {
	
	private UserCollectLibImpl userCollectLibDao = new UserCollectLibImpl();
	private WordDaoImpl wordDao = new WordDaoImpl();
	
	/**
	 * 添加单词到用户单词本（词库）
	 * @param 单词ID, 用户ID.
	 */
	public void AddwordToUserCollectLib(int wid,int uid)  //@代号：ljt 
	{
		userCollectLibDao.AddwordToUserCollectLib(wid, uid);
	}
	
	/**
	 * 获取单词用户单词本（词库）
	 * @param int(用户ID).
	 * @return 词库中所有单词
	 */
	public List<Word> getUserCollectLibByUid(String uid)  //@代号：ljt 
	{
		List<UserCollectLib> ucl= new ArrayList<UserCollectLib>();
		List<Word> wordList = new ArrayList<Word>();
		ucl = userCollectLibDao.getUserCollectLibByUid(uid);
		for(int i=0;i<ucl.size();i++)
		{
			try {
				wordList.add(wordDao.getEntry(ucl.get(i).getWid()));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wordList;
	}
	
	/**
	 * 判断单词是否已经存在于用户单词本（词库）
	 * @param int(单词ID).
	 * @return boolean
	 */
	public boolean isWordInUserCollectLib(int wid)  //@代号：ljt 
	{
		return userCollectLibDao.isWordInUserCollectLib(wid);
	}
	
	
}
