package com.flashvocabulary.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.FinishAGroupService;
import com.flashvocabulary.utils.IConstants;
import com.flashvocabulary.utils.WebUtils;
import com.opensymphony.xwork2.Action;

public class SetCompleteAction implements Action {
    private static final long serialVersionUID = 1L;
    private FinishAGroupService finishAGroupDao;
    
    public SetCompleteAction() {
	super();
        finishAGroupDao = new FinishAGroupService();
    }

    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	Integer uid = null;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = null;
	try {
		session=request.getSession();
		User user = (User)session.getAttribute("User");
		if (user == null) {
		    return IConstants.SESSION_EXPIRED;
		}
		uid = user.getId();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("session error" + e.getMessage());
	}
	
	JSONObject json = JSONObject.fromObject(request.getParameter("data")); //待测试
	JSONArray jsonArray = null;
	ArrayList<TodayWord> todayWordsList = new ArrayList<TodayWord>();

	try {
	    	
		jsonArray = json.getJSONArray("setResult");
		int length = json.getInt("setCount");
		for (int i=0;i<length;i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Integer id = Integer.parseInt(jsonObject.get("tid").toString()); 
			Integer wid = Integer.parseInt(jsonObject.get("wid").toString());
			Integer isCheck = Integer.parseInt(jsonObject.get("isCheck").toString());
			todayWordsList.add(new TodayWord(id,uid,wid,isCheck));
			if (i == length - 1) {
			    session.setAttribute("lastTid", id);
			}
		}
		
	} catch (JSONException e) {
		// TODO: handle exception
		System.out.println("JSON error"+e.getMessage());
	}
	
	finishAGroupDao.writeToTodayWords(todayWordsList);
	finishAGroupDao.writeToUserLib(todayWordsList);
	return null;
	//跳转
    }
    
}
