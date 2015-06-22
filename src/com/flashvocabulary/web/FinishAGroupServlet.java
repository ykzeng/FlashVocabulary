package com.flashvocabulary.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.service.FinishAGroupService;
import com.flashvocabulary.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FinishAGroupServlet
 */
//untested
@WebServlet("/FinishAGroupServlet")
public class FinishAGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FinishAGroupService finishAGroupDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishAGroupServlet() {
        super();
        finishAGroupDao = new FinishAGroupService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer uid = null;
		try {
			HttpSession session=request.getSession();
			uid = Integer.parseInt(session.getAttribute("id").toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("session error" + e.getMessage());
		}
		
		String json = WebUtils.readJsonString(request); //待测试
		JSONArray jsonArray = null;
		ArrayList<TodayWord> todayWordsList = new ArrayList<TodayWord>();
	
		try {
			jsonArray = JSONArray.fromObject(json);
			for (int i=0;i<jsonArray.size();i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Integer id = Integer.parseInt(jsonObject.get("tid").toString()); 
				Integer wid = Integer.parseInt(jsonObject.get("wid").toString());
				Integer isCheck = Integer.parseInt(jsonObject.get("isCheck").toString());
				todayWordsList.add(new TodayWord(id,uid,wid,isCheck));
			}
			
		} catch (JSONException e) {
			// TODO: handle exception
			System.out.println("JSON error"+e.getMessage());
		}
		
		finishAGroupDao.writeToTodayWords(todayWordsList);
		finishAGroupDao.writeToUserLib(todayWordsList);
		
		//跳转
	}

}
