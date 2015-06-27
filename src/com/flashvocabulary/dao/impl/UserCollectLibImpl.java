package com.flashvocabulary.dao.impl;

import java.util.List;

import com.flashvocabulary.dto.UserCollectLib;

public class UserCollectLibImpl extends BaseDaoImpl<UserCollectLib> {
	
	/**
	 * 添加单词到用户单词本（词库）
	 * @param 单词ID, 用户ID.
	 */
	public void AddwordToUserCollectLib(int wid,int uid)  //@代号：ljt 
	{
		try {
			UserCollectLib userCollectLib = new UserCollectLib();
			userCollectLib.setWid(wid);
			userCollectLib.setUid(uid);
			saveEntry(userCollectLib);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取单词用户单词本（词库）
	 * @param int(用户ID).
	 * @return 词库中所有单词
	 */
	public List<UserCollectLib> getUserCollectLibByUid(int uid)  //@代号：ljt 
	{
		try {
			return getEntrys("select * from tb_usercollectlib where uid = ?", uid);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 判断单词是否已经存在于用户单词本（词库）
	 * @param int(单词ID).
	 * @return boolean
	 */
	public boolean isWordInUserCollectLib(int wid,int uid)  //@代号：ljt 
	{
		try {
			UserCollectLib ucl =null;
			ucl = getEntry("select * from tb_usercollectlib where wid = ? and uid=?", wid,uid);
			if(ucl == null || ucl.getWid() != wid)
			{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
