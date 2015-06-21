package com.flashvocabulary.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flashvocabulary.dao.impl.UserCollectLibImpl;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.UserCollectLib;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.UserCollectLibService;
import com.flashvocabulary.service.WordLibService;
import com.flashvocabulary.utils.WebUtils;

/**
 * @last modify time 2015/6/20
 * @代号：ljt 
 */

public class UserCollectServlet extends HttpServlet {
	private WordLibService wordLibService = new WordLibService();
	private UserCollectLibService userCollectLibService = new UserCollectLibService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Word> wordList = new ArrayList<Word>();
		if(request.getParameter("getCollect")!=null)
		{
			//String uid = request.getParameter("uid").toString();
			wordList = userCollectLibService.getUserCollectLibByUid("18");
			String showstr1 = "四级: ";
			String showstr2 = "六级: ";
			String showstr3 = "要你命3000: ";
			String showstr4 = "TOEFL: ";
			Word word = null;
			//String lib_name = "";
			//Set<String> lib = new HashSet<String>();
			for(int i=0;i<wordList.size();i++)
			{
				word = wordList.get(i);
				//lib_name = wordLibService.getLibNameByLibid(word.getLib_id());
				//lib.add(lib_name);
				if(word.getLib_id()==1){
					showstr1+=word.getWord()+"   ";
				}
				else if(word.getLib_id()==3){
					showstr2+=word.getWord()+"   ";
				}
				else if(word.getLib_id()==6){
					showstr3+=word.getWord()+"   ";
				}
				else if(word.getLib_id()==10){
					showstr4+=word.getWord()+"   ";
				}
			}
			request.setAttribute("message", showstr1);
			request.setAttribute("message2", showstr2);
			request.setAttribute("message3", showstr3);
			request.setAttribute("message4", showstr4);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			;
		}
		else
		{
			String submitName = "";
			if(request.getParameter("0")==null)
			{
				if(request.getParameter("1")==null)	
				{	
					if(request.getParameter("2")==null)	
					{	
						if(request.getParameter("3")!=null)	
						{	
							submitName = "3";
						}
					}
					else submitName = "2";
				}
				else submitName = "1";
			}
			else submitName = "0";
			String wid = request.getParameter("wid"+submitName).toString();
			String uid = request.getParameter("uid").toString();
			try {
				if(!userCollectLibService.isWordInUserCollectLib(Integer.parseInt(wid)))
				{
					userCollectLibService.AddwordToUserCollectLib(Integer.parseInt(wid), Integer.parseInt(uid));
					request.setAttribute("message", uid+"添加"+wid+"成功!");
					request.getRequestDispatcher("/message.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("message", wid+"已存在于您的单词库");
				
					request.getRequestDispatcher("/message.jsp").forward(request, response);
				}
					
			} catch (Exception e) {
				request.setAttribute("message", "登陆失败");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}
}
