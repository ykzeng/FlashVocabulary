package com.flashvocabulary.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;

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
	
	public List<Word> getEntrysByWordLike(String word)  //@代号：ljt 
	{
		List<Word> wordList = new ArrayList<Word>();
		try {
			
			wordList = getEntrys("select * from tb_word where word = ?", word);
			if(wordList.size()==0)
			{
				if(word.startsWith("dis"))
				{
					word = word.substring(3, word.length());
				}
				if(word.endsWith("tion") || word.endsWith("ion"))
				{
					word = word.substring(0, word.length()-4);
				}
				if(word.endsWith("ing") || word.endsWith("ion") || word.endsWith("ism"))
				{
					word = word.substring(0, word.length()-3);
				}
				if(word.endsWith("ed") || word.endsWith("es") || word.endsWith("or") || word.endsWith("er"))
				{
					word = word.substring(0, word.length()-2);
				}
				if(word.endsWith("s"))
				{
					word = word.substring(0, word.length()-1);
				}
				wordList = getEntrys("select * from tb_word where word like ?", word+"%");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}
	
	public ArrayList<Word> getWordsByLibId(int libId)
	{
		String sql = "select * from tb_word where lib_id = ?";
		ArrayList<Word> wordsList = null;
		try {
			wordsList = (ArrayList<Word>)super.getEntrys(sql, libId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wordsList;
	}
	
	public int getLibCountById(int libId)
	{
		try {
			return (Integer)excSql_retValue("select count(*) from tb_word where lib_id = ?",
					new ScalarHandler() , libId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
