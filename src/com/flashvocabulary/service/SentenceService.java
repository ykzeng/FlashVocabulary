package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.List;

import com.flashvocabulary.dao.impl.SentenceDaoImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordSentenceViewImpl;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.WordSentenceView;

public class SentenceService {
	private SentenceDaoImpl sentenceDao = new SentenceDaoImpl();
	private WordSentenceViewImpl wordSentenceDao  = new WordSentenceViewImpl();
	private WordDaoImpl wordDao = new WordDaoImpl();
	
	/**
	 * 通过单词名获得所有例句
	 * @param word名.
	 * @return 例句-List
	 */
	public List<WordSentenceView> getSentencesByWord(String word)  //@代号：ljt 
	{
		List<WordSentenceView> wsvList = new ArrayList<WordSentenceView>();
		try {
			Word w = null;
			w = wordDao.getEntry("select top 1 * from tb_word where word = ?", word);
			if(w!=null)
			{
				int wid = w.getId();
				wsvList = wordSentenceDao.getEntrys("select * from tb_wordsentenceview where wid = ?", wid);
				if(wsvList.size()>0) 
					return wsvList;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
