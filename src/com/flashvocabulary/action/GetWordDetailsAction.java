package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Word;
import com.flashvocabulary.dto.WordSentenceView;
import com.flashvocabulary.service.SearchService;
import com.flashvocabulary.service.SearchService.searchResult;
import com.flashvocabulary.service.SearchService.tranList;
import com.flashvocabulary.service.SentenceService;
import com.flashvocabulary.service.WordService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class GetWordDetailsAction implements Action {

	private WordService wordService = new WordService();
	private SearchService ss = new SearchService();
	private SentenceService sentenceService = new SentenceService(); 
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
		    return IConstants.SESSION_EXPIRED;
		}
		int uid = user.getId();
		int wid = Integer.parseInt(request.getParameter("wid"));
		int libId = Integer.parseInt(request.getParameter("libid"));
		Word word = wordService.getWordInfo(wid);
		String wordName = word.getWord();
		searchResult sr = ss.findAllWordInfoByWord(wordName);
		
		String phonetics = word.getPhonetics();
		String antonym = word.getAntonym();
		String synonym = word.getSynonym();
		//List<tranList> trans = sr.getEveryTranList();
		//List<String> libnames = sr.getWordlib_name();
		
		String [] trans = word.getTranslation().split("\\|");	
		String tranSTR = getOLLI(trans);
		
		String [] antonyms = word.getAntonym().split("\\|");
		String antonymSTR = getOLLI(antonyms);
		
		String [] synonyms = word.getSynonym().split("\\|");
		String synonymSTR = getOLLI(synonyms);
		
		List<WordSentenceView> wsvlist = sentenceService.getSentencesByWord(wordName);
		WordSentenceView wsv = null;
		String sentenceSTR = "<ol class=\"sentence_list\">";
		for (int i = 0; i < wsvlist.size(); i++) {
			wsv = wsvlist.get(i);
			sentenceSTR += "<li><span>"+wsv.getSentence()
					+"</span><br><p>"+wsv.getTranslation()
					+"</p></li>";
		}
		sentenceSTR += "</ol>";
		
		request.setAttribute("word", wordName);
		request.setAttribute("phonetics", phonetics);
		request.setAttribute("antonymSTR", antonym);
		request.setAttribute("synonymSTR", synonym);
		request.setAttribute("longTranSTR", tranSTR);
		request.setAttribute("sentenceSTR", sentenceSTR);

		return "success";
	}
	
	
	public String getOLLI(String [] STR)
	{
		String longSTR = "<ol>";
		for (String str : STR) {
			longSTR+="<li><span>"+str+"</span></li>";
		}
		longSTR+="</ol>";
		return longSTR;
	}
	
}
