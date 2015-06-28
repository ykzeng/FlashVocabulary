package com.flashvocabulary.dao.impl;

import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.UserLib;
import java.util.ArrayList;

public class UserlibDaoImpl extends BaseDaoImpl<UserLib> implements BaseDao<UserLib> {
	public ArrayList<UserLib> getEntrysByUserId(Integer userId)
	{
		ArrayList<UserLib> userLibList = new ArrayList<UserLib>();
		try {
			userLibList = (ArrayList<UserLib>)getEntrys("select * from tb_userlib where uid = ?", userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLibList;
	}
	
	public UserLib getEntryByUidAndWid(Integer uid,Integer wid)
	{
		UserLib userLib = null;
		try {
			userLib = super.getEntry("select * from tb_userlib where uid = ? and wid = ?", uid, wid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userLib;
	}
}
