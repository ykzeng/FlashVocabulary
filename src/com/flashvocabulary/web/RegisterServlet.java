package com.flashvocabulary.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.utils.WebUtils;

/**
 * @last modify time 2015/6/20
 * @代号：ljt 
 */

public class RegisterServlet extends HttpServlet {

	private UserInfoService userInfoService = new UserInfoService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=WebUtils.write2Bean(request, User.class);
		try {
			userInfoService.userRegister(user);
			request.setAttribute("message", "注册成功！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("message", "注册失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);

		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}

}
