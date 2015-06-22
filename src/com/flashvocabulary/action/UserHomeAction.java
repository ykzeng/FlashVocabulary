package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.UserCollectLibService;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.service.WordLibService;
import com.opensymphony.xwork2.Action;

public class UserHomeAction implements Action {
	
	private UserCollectLibService userCollectLibService = new UserCollectLibService();
	private UserInfoService userInfoService = new UserInfoService();
	private WordLibService wordLibService = new WordLibService();
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		
		int userCollectCount = userCollectLibService.getUserCollectLibByUid(uid).size();
		String libName = wordLibService.getLibNameByLibid(
				userInfoService.getCurrentUserInfo(uid).getCurrentLib());
		if(libName==null) libName = "Empty Now!";
		request.setAttribute("userCollectCount", userCollectCount);
		request.setAttribute("libName", libName);
		
		return "toUserHome";
	}
	
}
