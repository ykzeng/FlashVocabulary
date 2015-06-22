package com.flashvocabulary.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.CheckInService;
import com.opensymphony.xwork2.Action;

public class CheckInAction implements Action{
	
	private CheckInService checkInDao;

	public String execute() throws Exception
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		int uid = ((User)session.getAttribute("user")).getId();
		String post = "This is test post."; //to be modified
		
		if (checkInDao.isFirstCheckIn(uid))
		{
			checkInDao.checkIn(uid, post);
			return "Success";
			//打卡成功
		}
		else {
			return "";
			//已打卡
		}
		
				
	}
}
