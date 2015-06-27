package com.flashvocabulary.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.TodayWordService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class RandomFlashCardAction implements Action {
    private TodayWordService todayWordservice = new TodayWordService();
    private JSONArray rFlashCard = null;
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	User user = (User)request.getSession().getAttribute("user");
	if (user == null) {
	    return IConstants.SESSION_EXPIRED;
	}
	
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
