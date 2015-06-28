package com.flashvocabulary.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.flashvocabulary.dto.User;
import com.flashvocabulary.dto.Wordlib;
import com.flashvocabulary.service.WordLibService;
import com.flashvocabulary.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class ToChooseWordLibAction implements Action {
	
	private WordLibService wordLibService = new WordLibService();
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
		    return IConstants.SESSION_EXPIRED;
		}
		int currentlibID = user.getCurrentLib();
		
		List<Wordlib> wordlib = new ArrayList<Wordlib>();
		wordlib = wordLibService.getAllWordLib();
		
		Wordlib Lib = null;
		String libname = "";
		int libId = 0;
		String description="";
		String imgSrc = "";
		String inputcolor = "";
		String inputvalue = "";
		String disable = "";
		int inputname = -1;
		int wordCount = 0;
		String longSTR = "";
		for(int i=0;i<wordlib.size();i++)
		{
			Lib = wordlib.get(i);
			libId = Lib.getId();
			libname = wordLibService.getLibNameByLibid(libId);
			description = Lib.getDescription();
			wordCount = wordLibService.getLibCountById(libId);
			imgSrc = Lib.getImg();
			
			if(libId==currentlibID)
			{
				inputcolor = "style=\"background:#e77e23;\"";
				inputvalue = "Using";
				disable = " disabled=\"true\"";
			}
			else
			{
				inputcolor = "";
				inputvalue =  "Switch";
				disable = "";
			}
			inputname = libId;
			longSTR += "<div><div><img src=\""+ imgSrc + "\"></div><div><h3>"
					+libname+"</h3><p>"+description+"</p>Total: <span>"
					+wordCount+"</span><br></br><input onclick=\"toWait()\" "+inputcolor
					+"type=\"submit\" value=\""+inputvalue+"\""+disable+"\" name=\"lib"
					+inputname+"\"/></div></div>";
		}
		request.setAttribute("longSTR", longSTR);
		return "ToChooseWordLib";
			
		
	}

}
