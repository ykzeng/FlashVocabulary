package com.flashvocabulary.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.TodayWordService;
import com.opensymphony.xwork2.Action;

public class GetWordSetAction implements Action {
    TodayWordService todayWordservice = new TodayWordService();
    private JSONArray wordSet = null;
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	int lastWid = 0;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	if (session.getAttribute("lastWid") == null) {
	    session.setAttribute("lastWid", 0);
	}
	else {
	    lastWid = (Integer)session.getAttribute("lastWid");
	}
	User user = (User)session.getAttribute("user");
	int uid = user.getId();
	//lastID = request.getParameter("");
	List<TodayWord> twList = todayWordservice.getUserTodayWordById(uid, lastWid);
	wordSet  = todayWordservice.getWordGroupInfoToJason(twList);
	
	return SUCCESS;
    }
    
    public JSONArray getWordSet() {
	return wordSet;
    }
    
    public void setWordSet(JSONArray wordSet) {
	this.wordSet = wordSet;
    }
    
}
