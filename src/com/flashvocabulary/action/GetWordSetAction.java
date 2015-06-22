package com.flashvocabulary.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.service.TodayWordService;
import com.opensymphony.xwork2.Action;

public class GetWordSetAction implements Action {
    TodayWordService todayWordservice = new TodayWordService();
    private JSONArray wordSet = null;
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	int uid = 18;
	int lastID = 0;
	HttpServletResponse response = ServletActionContext.getResponse();
	//lastID = request.getParameter("");
	List<TodayWord> twList = todayWordservice.getUserTodayWordById(uid, lastID);
	wordSet  = todayWordservice.getWordGroupInfoToJason(twList);
	
	return "SUCCESS";
    }
    
    public String getWordSet() {
	return wordSet.toString();
    }
    
}
