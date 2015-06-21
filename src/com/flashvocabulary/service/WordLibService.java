package com.flashvocabulary.service;

import com.flashvocabulary.dao.impl.WordlibDaoImpl;

public class WordLibService {
	private WordlibDaoImpl wlDao = new WordlibDaoImpl();
	
	/**
	 * 获取词库名
	 * @param libID.
	 * @return lib名
	 */
	public String getLibNameByLibid(int libId)   //@代号：ljt 
	 
	{
		return wlDao.getLibNameByLibid(libId);
	}
}
