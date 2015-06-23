package com.flashvocabulary.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.TodayWordService;
import com.opensymphony.xwork2.Action;

public class RandomFlashCardAction implements Action {
    private TodayWordService todayWordservice = new TodayWordService();
    private JSONArray rFlashCard = null;
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	User user = (User)request.getSession().getAttribute("user");
	
	rFlashCard  = todayWordservice.getFlashCard(user.getId());
	System.out.println("I'm being accessed!");
	return SUCCESS;
    }
    
    public JSONArray getRFlashCard() {
	return rFlashCard;
    }
    public void setRFlashCard(JSONArray rFlashCard) {
	this.rFlashCard = rFlashCard;
    }
    
}
