package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.flashvocabulary.dao.impl.TodayWordDaoImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordSentenceViewImpl;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.WordSentenceView;

public class TodayWordService {
	
	private TodayWordDaoImpl todayWordDao = new TodayWordDaoImpl();
	private WordDaoImpl wordDao = new WordDaoImpl();
	private WordSentenceViewImpl wordSentenceViewDao = new WordSentenceViewImpl();
	
	/**
	 * 封装一组TodayWord为Json数据。
	 * @param TodayWord类-List
	 */
	public JSONArray getWordGroupInfoToJason(List<TodayWord> twList)
	{
		List<Word> wordList = getWordGroupInfo1(twList);
		List<WordSentenceView> wsvList = getWordGroupInfo2(twList);
		Word word = null;
		WordSentenceView wsv = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		int position = -1;
		int wordID = -1;
		int idOfTodayWordLib = -1;
		String wordTrans = "";
		String phonetics = "";
		String antonym = "";
		String synonym = "";
		String wordSentence = "";
		String sentenceTran = "";
		for(int i=0; i<wordList.size() ; i++)
		{
			idOfTodayWordLib = twList.get(i).getId();
			
			word = wordList.get(i);
			wordID = word.getId();
			position = getRandomID();
			wordTrans = word.getTranslation();
			phonetics = word.getPhonetics();
			antonym = word.getAntonym();
			synonym = word.getSynonym();
			
			wsv = wsvList.get(i);
			wordSentence = wsv.getSentence();
			sentenceTran = wsv.getTranslation();
			
			jsonObject.put("idOfTodayWordLib", idOfTodayWordLib);
			jsonObject.put("position", position);
			jsonObject.put("id", wordID);
			jsonObject.put("phonetics",phonetics );
			jsonObject.put("c_trans",wordTrans );
			jsonObject.put("antonyms",antonym );
			jsonObject.put("synonyms",synonym );
			jsonObject.put("trans","a|b|c|d" );
			jsonObject.put("usages",wordSentence );
			jsonObject.put("sen_trans",sentenceTran );
			jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
	
	/**
	 * 获得下一组（7个）TodayWord-List
	 * @param 用户ID，上一个单词在TodayWord表的ID。
	 * @return TodayWord类-List
	 */
	public List<TodayWord> getUserTodayWordById(int uid,int lastID)
	{
		List<TodayWord> tw = null;
		try {
			
			tw = todayWordDao.getEntrys("select top 7 * from tb_todayword where uid = ? and " +
					"id > ? and ischeck = 0 ", uid,lastID);
			if(tw!=null && tw.size()>0)
			{
				return tw;
			}
			else if(tw.size() == 0)
			{
				tw = todayWordDao.getEntrys("select top 7 * from tb_todayword where uid = ? and " +
						"id > 0 and ischeck = 0 ", uid);
				return tw;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得单词各种信息
	 * @param TodayWord类-List
	 * @return Word类-List
	 */
	public List<Word> getWordGroupInfo1(List<TodayWord> twList)
	{
		List<Word> wordList = new ArrayList<Word>();
		Word word = null;
		try {
			for(int i=0;i<twList.size();i++)
			{
				word = wordDao.getEntry(twList.get(i).getWid());
				wordList.add(word);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wordList;
	}
	
	/**
	 * 获得例句和例句翻译
	 * @param TodayWord类-List
	 * @return WordSentenceView类-List
	 */
	public List<WordSentenceView> getWordGroupInfo2(List<TodayWord> twList)
	{
		List<WordSentenceView> wsvList = new ArrayList<WordSentenceView>();
		WordSentenceView wsv = null;
		try {
			for(int i=0;i<twList.size();i++)
			{
				wsv = wordSentenceViewDao.getEntry(twList.get(i).getWid());
				wsvList.add(wsv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsvList;
	}
	
	
	
	
	public int getRandomID()
	{
		return new Random().nextInt(4);
	}
	
}
