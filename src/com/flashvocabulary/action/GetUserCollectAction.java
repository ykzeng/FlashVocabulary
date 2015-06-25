package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.service.UserCollectLibService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class GetUserCollectAction implements Action{
	
	private UserCollectLibService userCollectLibService = new UserCollectLibService();
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Word> wordList = new ArrayList<Word>();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		
		wordList = userCollectLibService.getUserCollectLibByUid(18);
		String showstr1 = "四级: ";
		String showstr2 = "六级: ";
		String showstr3 = "要你命3000: ";
		String showstr4 = "TOEFL: ";
		Word word = null;
		for(int i=0;i<wordList.size();i++)
		{
			word = wordList.get(i);
			if(word.getLib_id()==1){
				showstr1+=word.getWord()+"   ";
			}
			else if(word.getLib_id()==3){
				showstr2+=word.getWord()+"   ";
			}
			else if(word.getLib_id()==6){
				showstr3+=word.getWord()+"   ";
			}
			else if(word.getLib_id()==10){
				showstr4+=word.getWord()+"   ";
			}
		}
		request.setAttribute("message", showstr1);
		request.setAttribute("message2", showstr2);
		request.setAttribute("message3", showstr3);
		request.setAttribute("message4", showstr4);
		return IConstants.GET_COLLECTION;
			
	}

}
