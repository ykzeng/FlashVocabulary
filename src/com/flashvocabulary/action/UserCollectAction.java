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
    private WordLibService wordLibService = new WordLibService();
    private UserCollectLibService userCollectLibService = new UserCollectLibService();
    
    public String execute() {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	int userid = user.getId();
	List<Word> wordList = new ArrayList<Word>();
	if(request.getParameter("getCollect")!=null)
	{
		wordList = userCollectLibService.getUserCollectLibByUid(userid);
		String showstr1 = "四级: ";
		String showstr2 = "六级: ";
		String showstr3 = "要你命3000: ";
		String showstr4 = "TOEFL: ";
		Word word = null;
		//String lib_name = "";
		//Set<String> lib = new HashSet<String>();
		for(int i=0;i<wordList.size();i++)
		{
			word = wordList.get(i);
			//lib_name = wordLibService.getLibNameByLibid(word.getLib_id());
			//lib.add(lib_name);
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
	else
	{
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
		String uid = request.getParameter("uid").toString();
		try {
			if(!userCollectLibService.isWordInUserCollectLib(Integer.parseInt(wid)))
			{
				userCollectLibService.AddwordToUserCollectLib(Integer.parseInt(wid), Integer.parseInt(uid));
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
}
