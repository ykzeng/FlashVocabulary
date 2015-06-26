package com.flashvocabulary.service;

import com.flashvocabulary.dao.impl.UserCollectLibImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dto.Word;

public class WordService {
	private WordDaoImpl wordDao = new WordDaoImpl();
	
	public Word getWordInfo(int wid)
	{
		try {
			return wordDao.getEntry(wid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
