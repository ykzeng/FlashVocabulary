package com.flashvocabulary.dao.impl;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.TodayWord;
import com.flashvocabulary.dto.UserLib;

public class TodayWordDaoImpl extends BaseDaoImpl<TodayWord> implements BaseDao<TodayWord> {

	
	public Integer getLibCount(Integer userId) {
		String sql = "select count(*) from tb_todayword where uid = ?";
		Integer ret = null;
		try {
			ret = (Integer)super.excSql_retValue(sql, new ScalarHandler(), userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	public TodayWord getEntryByUidAndWid(int uid,int wid)
	{
		String sql = "select * from tb_todayword where uid = ? and wid = ?";
		TodayWord todayWord = null;
		try {
			todayWord = super.getEntry(sql, uid, wid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return todayWord;
	}
}
