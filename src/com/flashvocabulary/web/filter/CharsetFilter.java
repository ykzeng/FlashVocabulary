package com.flashvocabulary.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharsetFilter implements Filter {

	private FilterConfig filterConfig;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
			
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		
		String charset=filterConfig.getInitParameter("charset");
		if(charset==null||charset.trim().equals(""))
		{
			charset="UTF-8";
		}
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		
		
		chain.doFilter(new myservletrequest(request), response);
		

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
	}
	
	@Override
	public void destroy() {
		

	}

}


class myservletrequest extends HttpServletRequestWrapper
{

	
	private HttpServletRequest request;
	public myservletrequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request=request;
	}
	@Override
	public String getParameter(String name) {
		
		try {
			
			String value=request.getParameter(name);
			//System.out.println(value);
			if(value==null)
			{
				return null;
			}
			
			if(!request.getMethod().equalsIgnoreCase("get"))
			{
				return value;
			}
			
			value=new String(value.getBytes("iso8859-1"),request.getCharacterEncoding());
			return value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
}
