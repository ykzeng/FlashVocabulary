package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.WordSentenceView;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.SentenceService;
import com.flashvocabulary.service.SearchService.searchResult;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class SearchAction implements Action{
    private SearchService searchservice = new SearchService();
    private SentenceService sentenceService = new SentenceService();
    
    @Override
    public String execute() throws Exception {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String word = request.getParameter("word");
	searchResult result = null;
	List<WordSentenceView> wsvList = new ArrayList<WordSentenceView>();
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	if (user == null) {
	    return IConstants.SESSION_EXPIRED;
	}
	try {
		result = searchservice.findAllWordInfoByWord(word);
		wsvList = sentenceService.getSentencesByWord(word);
		if(result!=null){
			request.setAttribute("result", result);
			if(wsvList!=null && wsvList.size()>0)
			request.setAttribute("wsvList", wsvList);
			return SUCCESS;
		}
		else
		{
			String maybewords = searchservice.maybeWords(word);
			if(maybewords==null || maybewords.equals(""))
			{
				request.setAttribute("message", "获取详细信息失败");
			}
			else
			{
				request.setAttribute("message", "You can search these similar words : <br><strong>"+maybewords+"</strong>");
			}
			return IConstants.WARNING;
		}
		
	} catch (Exception e) {
		request.setAttribute("message", "连接错误！");
		return IConstants.WARNING;
	}
    }
    
}
