package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class UserSettingAction implements Action {
	
	private UserInfoService userInfoService = new UserInfoService();
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
		    return IConstants.SESSION_EXPIRED;
		}
		int uid = user.getId();
		if(request.getParameter("saveChange")!=null)
		{
			String originalPassword = request.getParameter("originalPassword");
			String newPassword = request.getParameter("newPassword");
			String newWordNum = request.getParameter("newWordNum").trim();
			String againPwd = request.getParameter("confirmNewPassword");
			if(originalPassword!=null && newPassword!=null && !originalPassword.equals("") && !newPassword.equals(""))
			{
				if (!againPwd.equals(newPassword)) {
				    request.setAttribute("message", "两次新密码输入不一致！");
				    return IConstants.WARNING;
				}
				int flag = userInfoService.modifyUserPassword(uid, originalPassword, newPassword);
				if(flag==1)
				{
					user.setPwd(newPassword);
					request.setAttribute("message", "修改成功！");
				}
				else if(flag == 0)
				{
					request.setAttribute("message", "原始密码输入有误！");
					return IConstants.WARNING;
				}
				else
				{
					request.setAttribute("message", "修改失败！");
					return IConstants.WARNING;
				}
			}
			else if(!newWordNum.equals(""))
			{
				userInfoService.setDailyNewWordCount(uid, Integer.parseInt(newWordNum));
				user.setDailyCount(Integer.parseInt(newWordNum));
				request.setAttribute("message", "新词设置成功："+"  "+userInfoService.getDailyNewWordCount(uid));
			}
			else {
			    request.setAttribute("message", "密码和单词量至少一项不能为空！");
			    return IConstants.WARNING;
			}
			return IConstants.SAVE_SUCCESS;
			
		}
		request.setAttribute("message", "连接错误！");
		return IConstants.WARNING;
	}

}
