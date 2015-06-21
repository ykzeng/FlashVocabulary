package com.flashvocabulary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.flashvocabulary.dto.Word;

public class WordDaoImpl extends BaseDaoImpl<Word> {
	
	/**
	 * 通过word查找所在所有词库中的信息
	 * @param word（String）
	 * @return Word类的List
	 */
	public List<Word> getEntrysByWord(String word)  //@代号：ljt 
	{
		List<Word> wordList = new ArrayList<Word>();
		try {
			wordList = getEntrys("select * from tb_word where word = ?", word);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}

}
