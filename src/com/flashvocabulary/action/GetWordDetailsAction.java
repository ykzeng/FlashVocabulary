package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.SearchService.searchResult;
import com.flashvocabulary.service.WordService;
import com.opensymphony.xwork2.Action;

public class GetWordDetailsAction implements Action {

	private WordService wordService = new WordService();
	private SearchService ss = new SearchService();
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int uid = user.getId();
		int wid = Integer.parseInt(request.getParameter("wid"));
		
		Word word = wordService.getWordInfo(wid);
		String wordName = word.getWord();
		searchResult sr = ss.findAllWordInfoByWord(wordName);
		
		String phonetics = word.getPhonetics();
		String antonym = word.getAntonym();
		String synonym = word.getSynonym();
		
		return null;
	}

}
