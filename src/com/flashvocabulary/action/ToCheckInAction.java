package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.CheckIn;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.CheckInService;
import com.flashvocabulary.service.TodayWordService;
import com.opensymphony.xwork2.Action;

public class ToCheckInAction implements Action {
	
	private CheckInService checkInDao = new CheckInService();
	private TodayWordService todayWordService = new TodayWordService();
	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		CheckIn checkIn = null;
		String post = ""; 
		String time="";
		List<CheckIn> checkinList = checkInDao.getAllCheckIn(uid);
		int len = checkinList.size();
		String longSTR = "<ul>";
		for (int i=len; i>0;i--) 
		{
			checkIn = checkinList.get(i-1);
			post = checkIn.getPost();
			time = checkInDao.getTime(checkIn.getTime());
			longSTR += "<li><p>第"+(i)+"天打卡日记:</p>"+post+"<p style=\"float:right\">"+time+"</p></li>"; //6月23日，2015
		}
		longSTR += "</ul>";
		
		String canCheckin = "disabled";
		int []arr = todayWordService.getUserTodayWordInfo(uid);
		if(arr[0] >0 && arr[1]==0 && checkInDao.isFirstCheckIn(uid))
		{
			canCheckin = "";
		}
		request.setAttribute("canCheckin", canCheckin);
		request.setAttribute("checkinCount", len+1);
		request.setAttribute("longSTR", longSTR);
		return "toCheckinSuccess";
	}

}
