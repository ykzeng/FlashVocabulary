package com.flashvocabulary.dao.impl;

import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.UserLib;
import java.util.ArrayList;

public class UserlibDaoImpl extends BaseDaoImpl<UserLib> implements BaseDao<UserLib> {
	public ArrayList<UserLib> getEntryByUserId(Integer userId)
	{
		ArrayList<UserLib> userLibList = new ArrayList<UserLib>();
		try {
			userLibList = (ArrayList<UserLib>)getEntrys("select * from tb_userlib where uid = ?", userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLibList;
	}
}
