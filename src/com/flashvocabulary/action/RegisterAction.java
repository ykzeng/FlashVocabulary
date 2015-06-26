package com.flashvocabulary.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.service.ChooseWordLib;
import com.flashvocabulary.service.UserInfoService;
import com.flashvocabulary.utils.IConstants;
import com.flashvocabulary.utils.WebUtils;
import com.opensymphony.xwork2.Action;

public class RegisterAction implements Action{
    private UserInfoService userInfoService = new UserInfoService();
    private ChooseWordLib chooseWordLib = new ChooseWordLib();
    @Override
    public String execute() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	User user=WebUtils.write2Bean(request, User.class);
	try {
		if(!userInfoService.isAcountExisted(user.getUname()))
		{
			user.setCurrentLib(1);
			userInfoService.userRegister(user);
			request.setAttribute("message", "注册成功！");
			return IConstants.REGISTER_SUCCESS;
		}
		else
		{
			request.setAttribute("message", "账号已存在！请直接登陆");
			return IConstants.REGISTER_FAILURE;
		}

	} catch (Exception e) {
		// TODO: handle exception
		request.setAttribute("message", "注册失败");
		return IConstants.REGISTER_FAILURE;

	}
    }
    
}
