package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.UserInfoService;
import com.opensymphony.xwork2.Action;

public class UserSettingAction implements Action {
	
	private UserInfoService userInfoService = new UserInfoService();
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		if(request.getParameter("saveChange")!=null)
		{
			String originalPassword = request.getParameter("originalPassword");
			String newPassword = request.getParameter("newPassword");
			String newWordNum = request.getParameter("newWordNum").trim();
			if(originalPassword!=null && newPassword!=null && !originalPassword.equals("") && !newPassword.equals(""))
			{
				int flag = userInfoService.modifyUserPassword(uid, originalPassword, newPassword);
				if(flag==1)
				{
					user.setPwd(newPassword);
					request.setAttribute("message", "修改成功！");	
				}
				else if(flag == 0)
				{
					request.setAttribute("message", "原始密码输入有误！");
				}
				else
				{
					request.setAttribute("message", "修改失败！");
				}
			}
			else if(newWordNum != null)
			{
				userInfoService.setDailyNewWordCount(uid, Integer.parseInt(newWordNum));
				user.setDailyCount(Integer.parseInt(newWordNum));
				request.setAttribute("message", "新词设置成功："+"  "+userInfoService.getDailyNewWordCount(uid));
			}
			return "saveChangeSuccess";
			
		}
		return null;
	}

}
