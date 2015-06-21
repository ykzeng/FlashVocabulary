package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.flashvocabulary.dao.impl.UserCollectLibImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordlibDaoImpl;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.Wordlib;

public class SearchService {
	
	private WordDaoImpl wordDao  = new WordDaoImpl();
	private WordlibDaoImpl wlDao = new WordlibDaoImpl();
	
	/**
	 * 搜索某单词，得到单词所有信息
	 * @param word（String）.
	 * @return searchResult类（单词名，音标，单词id-List，词库名-List，翻译类-List）
	 */
	public searchResult findAllWordInfoByWord(String word)  //@代号：ljt 
	{
		List<Word> wordList = new ArrayList<Word>();
		wordList = wordDao.getEntrysByWord(word);
		int len = wordList.size();
		if(len>0)
		{
			List<tranList> everyTranList = new ArrayList<tranList>();
			List<String> wordlib_name = new ArrayList<String>();
			List<Integer> idList = new ArrayList<Integer>();
			for(int i=0;i<len;i++)
			{
				try {
				Word w = wordList.get(i);
				idList.add(w.getId());
				String lib_name = ((Wordlib)wlDao.getEntry(w.getLib_id())).getName();
				wordlib_name.add(lib_name);
				tranList tl = new tranList(lib_name,w.getTranslation().split("\\|"));
				everyTranList.add(tl);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			String phonetics = wordList.get(0).getPhonetics();
			return new searchResult(word,phonetics,idList,wordlib_name,everyTranList);
		}
		return null;
	}
	
	//搜索结果类
	public class searchResult  //@代号：ljt 
	{
		private String word;
		private String phonetics;
		private List<Integer> idList;
		private List<String> wordlib_name;
		private List<tranList> everyTranList;

		public searchResult(){}
		
		public searchResult(String word,String phonetics,List<Integer> idList,List<String> wordlib_name,List<tranList> everyTranList)
		{
			this.word = word;
			this.idList = idList;
			this.phonetics = phonetics;
			this.wordlib_name = wordlib_name;
			this.everyTranList = everyTranList;
		}
		
		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}
		
		public String getPhonetics() {
			return phonetics;
		}
		public void setPhonetics(String phonetics) {
			this.phonetics = phonetics;
		}

		public List<String> getWordlib_name() {
			return wordlib_name;
		}
		
		public List<Integer> getIdList() {
			return idList;
		}

		public void setIdList(List<Integer> idList) {
			this.idList = idList;
		}
		
		public void setWordlib_name(List<String> wordlib_name) {
			this.wordlib_name = wordlib_name;
		}
		
		public List<tranList> getEveryTranList() {
			return everyTranList;
		}

		public void setEveryTranList(List<tranList> everyTranList) {
			this.everyTranList = everyTranList;
		}
		
	}
	
//	@Test
//	public void test()
//	{
//		searchResult sr = new searchResult();
//		
//		sr = findTranslationByWord("abandon");
//		sr.setPhonetics("madan");
//		System.out.println(sr.getPhonetics()+"  "+sr.getWord());
//		List<Word> wordList = new ArrayList<Word>();
//		wordList = wordDao.getEntrysByWord("abandon");
//		System.out.println(wordList.get(0).getPhonetics());
//	}
	
	public class tranList  //@代号：ljt 
	{
		private String wordlib_name;
		private String [] everyTran;
		public tranList(){}
		public tranList(String wordlib_name,String [] everyTran)
		{
			this.wordlib_name = wordlib_name;
			this.everyTran = everyTran;
		}
		public String getWordlib_name() {
			return wordlib_name;
		}
		public void setWordlib_name(String wordlib_name) {
			this.wordlib_name = wordlib_name;
		}
		public String[] getEveryTran() {
			return everyTran;
		}
		public void setEveryTran(String[] everyTran) {
			this.everyTran = everyTran;
		}
	}
}
