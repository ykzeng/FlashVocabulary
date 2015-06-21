package com.flashvocabulary.utils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


/**
 * 有问题请马化腾275083584  可能存在bug
 * @author LuckyBear
 *
 * @param <T>
 */
public class WebUtils {
	
	static
	{
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class arg0, Object arg1) {
				// TODO Auto-generated method stub
				if(arg1==null) return null;
				String time=(String) arg1;
				if(time.trim().equals(""))
				{
					return null;
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new  RuntimeException(e);
				}
			}
		}, Date.class);
	}
	
	public static <T>T  write2Bean(HttpServletRequest request ,Class<T> clazz)
	{
		
		try {
			T bean=clazz.newInstance();
			
			Enumeration<String> it= request.getParameterNames();
			while (it.hasMoreElements()) {
				String name = (String) it.nextElement();
				String value=request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String md5(String str)
	{
		try {
			StringBuffer sb=new StringBuffer();
			char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			MessageDigest ms=MessageDigest.getInstance("md5");
			byte[] re=ms.digest(str.getBytes());
			for(byte b: re)
			{
				sb.append(chars[(b >> 4) & 0x0F]);
				sb.append(chars[b  & 0x0F]);
			}
			return sb.toString();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getId()
	{
		return UUID.randomUUID().toString();
	}

}
