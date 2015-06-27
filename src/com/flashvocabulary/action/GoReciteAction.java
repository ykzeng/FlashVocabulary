package com.flashvocabulary.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class GoReciteAction implements Action {

    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	HttpSession session = ServletActionContext.getRequest().getSession();
	User user = (User)session.getAttribute("user");
	if (user == null) {
	    return IConstants.SESSION_EXPIRED;
	}
	return SUCCESS;
    }
    
}
