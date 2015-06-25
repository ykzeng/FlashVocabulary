package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Wordlib;
import com.flashvocabulary.service.WordLibService;
import com.opensymphony.xwork2.Action;

public class ChooseWordLibAction implements Action {
	private WordLibService wordLibService = new WordLibService();
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		int currentLibId = user.getCurrentLib();
		List<Wordlib> wordlib = new ArrayList<Wordlib>();
		wordlib = wordLibService.getAllWordLib();
		int libId = 0;
		for(int i=0;i<wordlib.size();i++)
		{
			if(request.getParameter("lib"+wordlib.get(i).getId())!=null)
			{
				libId = wordlib.get(i).getId();
				user.setCurrentLib(libId);
				//System.out.println(libId);
				wordLibService.setUserLib(uid, libId);
				
				//request.setAttribute("libName", wordLibService.getLibNameByLibid(libId));
			}
		}
		
		//new UserHomeAction();
		return "chooseSuccess";
	}

}
