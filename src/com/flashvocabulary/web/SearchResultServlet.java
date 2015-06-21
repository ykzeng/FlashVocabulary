package com.flashvocabulary.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flashvocabulary.dto.WordSentenceView;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.SentenceService;
import com.flashvocabulary.service.SearchService.searchResult;

/**
 * @last modify time 2015/6/20
 * @代号：ljt 
 */

public class SearchResultServlet extends HttpServlet{
	private SearchService searchservice = new SearchService();
	private SentenceService sentenceService = new SentenceService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String word = request.getParameter("word");
		searchResult result = null;
		List<WordSentenceView> wsvList = new ArrayList<WordSentenceView>();
		//RequestDispatcher dispatcher = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			result = searchservice.findAllWordInfoByWord(word);
			wsvList = sentenceService.getSentencesByWord(word);
//			request.setAttribute("message", "OK!");
//			dispatcher = request.getRequestDispatcher("/message.jsp");
//			dispatcher.forward(request, response);
			out.println("<html><title>show</title>" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
					   +"<form action=\"UserCollectServlet\" method=\"POST\">"
					   +"<input type=\"hidden\" name=\"uid\" value=\"18\"/>"
					   + "<H1 ALIGN=\"CENTER\">" + result.getWord() + "</H1>"
					   + "<H2 ALIGN=\"CENTER\">" + result.getPhonetics() + "</H2>"   
					);
			for(int i=0;i<result.getWordlib_name().size();i++)
			{
				out.println("<H3 ALIGN=\"CENTER\">" + result.getWordlib_name().get(i) + "</H3>");
				String [] tranList = result.getEveryTranList().get(i).getEveryTran();
				for(int j=0;j<tranList.length;j++)
				{
					out.println("<H3 ALIGN=\"CENTER\">" + tranList[j] + "</H3>");
				}
				out.println("<input type=\"hidden\" name=\""+("wid"+i)+"\" value=\""+result.getIdList().get(i)+"\"/>");
				out.println("<input type=\"submit\" name=\""+i+"\" value=\"添加到我的单词本\"/>");
			}
			for(int i=0;i<wsvList.size();i++)
			{
				out.println("<H5 ALIGN=\"CENTER\">" + wsvList.get(i).getSentence()+"&nbsp;&nbsp;" +
						wsvList.get(i).getTranslation()+ "</H5>");
			}
			out.println("</form></BODY></html>");
			
		} catch (Exception e) {
//			request.setAttribute("message", "登陆失败");
//			request.getRequestDispatcher("/message.jsp").forward(request, response);
			out.println("<html><title>show</title>" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
				       + "<H1 ALIGN=\"CENTER\">" + "失败" + "</H1>");
			out.println("</BODY></html>");
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	}
}
