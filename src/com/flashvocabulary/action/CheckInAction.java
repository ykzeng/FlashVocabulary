package com.flashvocabulary.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.CheckInService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class CheckInAction implements Action{
	
	private CheckInService checkInDao = new CheckInService();
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
		    return IConstants.SESSION_EXPIRED;
		}
		int uid = user.getId();
		String post = ""; 
		if(request.getParameter("checkin")!=null)
		{
			if(checkInDao.isFirstCheckIn(uid))
			//if(!checkInDao.isTodayCheckedIn(uid,new Timestamp(System.currentTimeMillis())) )
			{
				post = request.getParameter("mytext");
				if(post!=null && !post.trim().equals(""))
				{
					checkInDao.checkIn(uid, post);
					
					return "checkinSuccess";
				}
				else
				{
					request.setAttribute("message", "输入不能为空！");
					return IConstants.FAILURE;
				}
			}
			else
			{
				request.setAttribute("message", "你今天已经打过卡了！");
				return IConstants.FAILURE;
			}
		}
		request.setAttribute("message", "打卡失败!");
		return IConstants.FAILURE;
				
	}
}
