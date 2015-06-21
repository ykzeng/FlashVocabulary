package com.flashvocabulary.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.service.TodayWordService;

public class GetTodayWordServlet extends HttpServlet {
	private TodayWordService todayWordservice = new TodayWordService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid = 18;
		int lastID = 0;
		//lastID = request.getParameter("");
		List<TodayWord> twList = todayWordservice.getUserTodayWordById(uid, lastID);
		JSONArray jsonArray  = todayWordservice.getWordGroupInfoToJason(twList);
		
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		out.write(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}

}
