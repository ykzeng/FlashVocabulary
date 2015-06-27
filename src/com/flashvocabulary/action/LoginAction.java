package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.Icon;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.ArrangeWordService;
import com.flashvocabulary.service.TodayWordService;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.service.WordLibService;
import com.flashvocabulary.utils.IConstants;
import com.flashvocabulary.utils.WebUtils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;

public class LoginAction implements Action{
    private UserInfoService userInfoService = new UserInfoService();
    private TodayWordService todayWordService = new TodayWordService();
    private WordLibService wordLibService = new WordLibService();
    @Override
    public String execute() throws Exception {

	HttpServletRequest request = ServletActionContext.getRequest();
	User user = null;
	user = (User)request.getSession().getAttribute("user");
	if (user !=null) 
	{
	    setIndexParam(request, user);
	    return IConstants.LOGIN_SUCCESS;
	}
	user = WebUtils.write2Bean(request, User.class);
	User userFromDB = null;
	try {
		if((userFromDB = userInfoService.userLogin(user)) != null){
		    	int uid = userFromDB.getId();
			ArrangeWordService arrangeWordService = new ArrangeWordService();
			if (arrangeWordService.isFirstLogin(uid)) {
			    arrangeWordService.ArrangeWord(uid);
			}
		    	
			setIndexParam(request, userFromDB);
			
			return IConstants.LOGIN_SUCCESS;
		}
		else 
		{
			request.setAttribute("message", "你的用户名或密码有误！！");
			return IConstants.LOGIN_PWD_FAILURE;
		}
	} catch (Exception e) {
		// TODO: handle exception
	    	request.setAttribute("message", "连接错误！");
		return IConstants.LOGIN_CONN_FAILURE;
	}
    }
    
    public String logout() throws Exception{
	HttpServletRequest request = ServletActionContext.getRequest();
	request.getSession().removeAttribute("user");
	return IConstants.LOGOUT;
    }
    
    public void setIndexParam(HttpServletRequest request, User user) {
	String currentLibname = " - -";
	int todayCount=0,todayNoFinished=0, currentLibCount=0,currentLibFinished=0,dayToFinish=0;
	currentLibname = wordLibService.getLibNameByLibid(user.getCurrentLib());
	int [] values = todayWordService.getUserTodayWordInfo(user.getId());
	todayCount = values[0];
	todayNoFinished = values[1];
	currentLibCount = values[2];
	currentLibFinished = values[3];
	if(todayCount!=0)
	{
		dayToFinish = (currentLibCount-currentLibFinished)/todayCount+1;
	}
	else
	{
		dayToFinish = 99999;
	}
	request.setAttribute("todayCount", todayCount>-1?todayCount:0);
	request.setAttribute("todayNoFinished", todayNoFinished>-1?todayNoFinished:0);
	request.setAttribute("currentLibCount", currentLibCount>-1?currentLibCount:0);
	request.setAttribute("currentLibFinished", currentLibFinished>-1?currentLibFinished:0);
	request.setAttribute("dayToFinish", dayToFinish>-1?dayToFinish:0);
	request.setAttribute("currentLibname", currentLibname==null?"暂无":currentLibname);
	request.getSession().setAttribute("user", user);
    }
    
}
