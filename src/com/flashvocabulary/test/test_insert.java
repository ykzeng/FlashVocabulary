package com.flashvocabulary.test;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.flashvocabulary.dao.impl.UserDaoImpl;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.SearchService.searchResult;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.TodayWordService;
import com.flashvocabulary.utils.QueryHelper;
import com.flashvocabulary.utils.WebUtils;

public class test_insert {
	
	@Test
	public void test() throws Exception
	{
//		UserDaoImpl dao=new UserDaoImpl();
//		//User u=dao.getEntry(2);
//		User u = new User();
//		u.setPrivilege_id(0);
//		u.setPwd("asda");
//		u.setUname("1");
//		
//		dao.saveEntry(u); 
//		User u=dao.getEntry();
//		QueryHelper qh=new QueryHelper(Book.class, "b")
//					.addCondition("id=?", 1).addCondition("uname=?", "haha").addOrderProperty("id", true);
//		System.out.println(qh.getCountQuerySql());
//		
//		System.out.println(qh.getListQuerySql());
//		System.out.println(WebUtils.getId());
//		dao.updateEntry(u);
		
//		searchResult srs = null;
//		srs.setWord("nihao");
//		srs.setPhonetics("dsds");
//		System.out.println(srs.getPhonetics());
//		int i=1;
//		while(i<10)
//		{
//			System.out.println(new Random().nextInt(4));
//			i++;
//		}

		
		TodayWordService todayWordservice = new TodayWordService();
			int uid = 18;
			int lastID = 0;
			List<TodayWord> twList = todayWordservice.getUserTodayWordById(uid, lastID);
			JSONArray jsonArray = todayWordservice.getWordGroupInfoToJason(twList);
			System.out.println(jsonArray.toString());
	}
	

}






