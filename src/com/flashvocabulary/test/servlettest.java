package com.flashvocabulary.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flashvocabulary.dao.impl.WordDaoImpl;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.service.SearchService;

public class servlettest extends HttpServlet {

	private SearchService ss = new SearchService();
	private WordDaoImpl wordDao  = new WordDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			List<Word> wordList = new ArrayList<Word>();
			wordList = wordDao.getEntrysByWord("abandon");
			System.out.println(wordList.get(0).getPhonetics());
			request.setAttribute("message", wordList.get(0).getPhonetics());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("message", "失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}

}
