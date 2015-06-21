package com.flashvocabulary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.flashvocabulary.dao.impl.BaseDaoImpl;
import com.flashvocabulary.dao.impl.TodayWordDaoImpl;
import com.flashvocabulary.dao.impl.UserlibWordViewImpl;
import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dao.impl.WordSentenceViewImpl;
import com.flashvocabulary.dao.impl.WordlibDaoImpl;
import com.flashvocabulary.dto.BaseEntry;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.WordSentenceView;

public class TodayWordService {
	
	private TodayWordDaoImpl todayWordDao = new TodayWordDaoImpl();
	private WordDaoImpl wordDao = new WordDaoImpl();
	private WordSentenceViewImpl wordSentenceViewDao = new WordSentenceViewImpl();
	private WordlibDaoImpl wordLibDao = new WordlibDaoImpl();
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
		String transElection = "";
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
			
			transElection = get3RandomTranEletion(wordID,position);
			
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
			jsonObject.put("trans", transElection);
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
	
	/**
	 * 获得一组单词翻译选项（4个）
	 * @param 单词ID，正确位置。
	 * @return 选项字符串
	 */
	public String get3RandomTranEletion(int wid,int position)
	{
		int [] indexs = new int[4];
		if(wid<3000)
		{
			if(wid<1500)
			{
				indexs[0] = wid/10*1+wid+1;
				indexs[1] = wid/10*3+wid+50;
				indexs[2] = wid/10*5+wid+100;
			}
			else
			{
				indexs[0] = wid-wid/10*1;
				indexs[1] = wid-wid/10*3;
				indexs[2] = wid-wid/10*5;
			}
		}
		else if(wid>=3000 && wid<6000)
		{
			if(wid<4500)
			{
				indexs[0] = wid/5*1+wid;
				indexs[1] = wid/10*3+wid;
				indexs[2] = wid/10*5+wid;
			}
			else
			{
				indexs[0] = wid-wid/5*1;
				indexs[1] = wid-wid/10*3;
				indexs[2] = wid-wid/10*5;
			}
		}
		else if(wid>=6000 && wid<9000)
		{
			if(wid<7500)
			{
				indexs[0] = wid/10*1+wid;
				indexs[1] = wid/50*3+wid;
				indexs[2] = wid/100*5+wid;
			}
			else
			{
				indexs[0] = wid-wid/10*1;
				indexs[1] = wid-wid/50*3;
				indexs[2] = wid-wid/100*5;
			}
		}
		else if(wid>=9000 && wid<12000)
		{
			if(wid<10500)
			{
				indexs[0] = wid/10*1+wid;
				indexs[1] = wid/100*3+wid;
				indexs[2] = wid/300*5+wid;
			}
			else
			{
				indexs[0] = wid-wid/10*1;
				indexs[1] = wid-wid/100*3;
				indexs[2] = wid-wid/300*5;
			}
		}
		else if(wid>=12000)
		{
			indexs[0] = wid-wid/10*1;
			indexs[1] = wid-wid/100*3;
			indexs[2] = wid-wid/300*5;
		}
		
		if(position!=3)
		{
			indexs[3] = indexs[position];
		}
		indexs[position] = wid;

		String transElection="";
		try {
			transElection = wordDao.getEntry(indexs[0]).getTranslation().split("\\|")[0]
							+"|"+ wordDao.getEntry(indexs[1]).getTranslation().split("\\|")[0]
							+"|"+ wordDao.getEntry(indexs[2]).getTranslation().split("\\|")[0]
							+"|"+ wordDao.getEntry(indexs[3]).getTranslation().split("\\|")[0];
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transElection;
	}
	
	public int[] getUserTodayWordInfo(int uid)
	{
		// 0 : todayCount,1 : todayNoFinished, 2: currentLibCount, 3: currentLibFinished
		int [] values = new int[4];
		try {
			values[0] = todayWordDao.getEntrys("select * from tb_todayword where uid = ?", uid).size();
			
			values[1] = todayWordDao.getEntrys("select * from tb_todayword where uid = ? " +
					"and ischeck = 0", uid).size();
			//values[1] = (Integer)(todayWordDao.excSql_retValue("select count(*) from tb_todayword " +
			//		"where uid = ? and ischeck = 0",new ArrayHandler(), uid));
			
			TodayWord tw = todayWordDao.getEntry("select top 1 * from tb_todayword where uid = ?", uid);
			int libID = wordDao.getEntry(tw.getWid()).getLib_id();
			values[2] = wordDao.getEntrys("select * from tb_word where lib_id = ?", libID).size();
			UserlibWordViewImpl userlibWordViewDao = new UserlibWordViewImpl();
			
			values[3] = userlibWordViewDao.getEntrys("select * from tb_UserlibWordView " +
					"where lib_id = ? and uid = ? and status = 5", libID,uid).size();
			
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUserCurrentLibName(int uid)
	{
		try {
			TodayWord tw = todayWordDao.getEntry("select top 1 * from tb_todayword where uid = ?", uid);
			int libID = wordDao.getEntry(tw.getWid()).getLib_id();
			return wordLibDao.getLibNameByLibid(libID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRandomID()
	{
		return new Random().nextInt(4);
	}
	
}
