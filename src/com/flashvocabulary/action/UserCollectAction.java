package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.service.UserCollectLibService;
import com.flashvocabulary.service.WordLibService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class UserCollectAction implements Action {

    private UserCollectLibService userCollectLibService = new UserCollectLibService();
    
    public String execute() {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	int uid = user.getId();
	
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
	try {
		if(!userCollectLibService.isWordInUserCollectLib(Integer.parseInt(wid),uid))
		{
			userCollectLibService.AddwordToUserCollectLib(Integer.parseInt(wid), uid);
			request.setAttribute("message", uid+"添加"+wid+"成功!");
			return IConstants.ADD_SUCCESS;
		}
		else
		{
			request.setAttribute("message", wid+"已存在于您的单词库");
		
			return IConstants.ALREADY_EXIST;
		}
			
	} catch (Exception e) {
		request.setAttribute("message", "登陆失败");
		return IConstants.SESSION_EXPIRED;
	}
    }
}
