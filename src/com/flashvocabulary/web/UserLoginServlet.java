package com.flashvocabulary.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.TodayWordService;
import com.flashvocabulary.service.UserCollectLibService;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.utils.WebUtils;

/**
 * @last modify time 2015/6/20
 * @代号：ljt 
 */

public class UserLoginServlet extends HttpServlet {
	
	private UserInfoService userInfoService = new UserInfoService();
	private TodayWordService todayWordService = new TodayWordService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=WebUtils.write2Bean(request, User.class);
		User userFromDB = null;
		String currentLibname = " - -";
		int todayCount=0,todayNoFinished=0, currentLibCount=0,currentLibFinished=0,dayToFinish=0;
		try {
			if((userFromDB = userInfoService.userLogin(user)) != null){

				currentLibname = todayWordService.getUserCurrentLibName(25);
				int [] values = todayWordService.getUserTodayWordInfo(25);
				todayCount = values[0];
				todayNoFinished = values[1];
				currentLibCount = values[2];
				currentLibFinished = values[3];
				dayToFinish = (currentLibCount-currentLibFinished)/todayCount+1;
				request.setAttribute("todayCount", todayCount);
				request.setAttribute("todayNoFinished", todayNoFinished);
				request.setAttribute("currentLibCount", currentLibCount);
				request.setAttribute("currentLibFinished", currentLibFinished);
				request.setAttribute("dayToFinish", dayToFinish);
				request.setAttribute("currentLibname", currentLibname);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				request.getSession().setAttribute("user", userFromDB);
			}
			else 
			{
				request.setAttribute("message", "你的用户名或密码有误！！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("message", "登陆失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}

}
