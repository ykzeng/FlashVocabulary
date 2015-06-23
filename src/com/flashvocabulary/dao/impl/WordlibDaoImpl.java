package com.flashvocabulary.dao.impl;

import com.flashvocabulary.dto.Wordlib;

public class WordlibDaoImpl extends BaseDaoImpl<Wordlib>{

	/**
	 * 通过词库ID获得词库名
	 * @param libID.
	 * @return lib名
	 */
	public String getLibNameByLibid(int libId)  //@代号：ljt 
	{
		try {
			String libName = getEntry(libId).getName();
			return libName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
