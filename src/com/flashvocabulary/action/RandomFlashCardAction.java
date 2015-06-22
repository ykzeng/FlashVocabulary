package com.flashvocabulary.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.flashvocabulary.service.TodayWordService;
import com.opensymphony.xwork2.Action;

public class RandomFlashCardAction implements Action {
    private TodayWordService todayWordservice = new TodayWordService();
    private String rFlashCard = null;
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	int uid = 25;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	rFlashCard  = todayWordservice.getFlashCard(uid).toString();
	System.out.println("I'm being accessed!");
	return SUCCESS;
    }
    
    public String getRFlashCard() {
	return rFlashCard;
    }
    public void setRFlashCard(String rFlashCard) {
	this.rFlashCard = rFlashCard;
    }
    
}
