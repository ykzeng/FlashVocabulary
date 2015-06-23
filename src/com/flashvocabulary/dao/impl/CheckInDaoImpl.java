package com.flashvocabulary.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.CheckIn;

public class CheckInDaoImpl extends BaseDaoImpl<CheckIn> implements BaseDao<CheckIn>{
	
	public Integer getEntryCounts(int uid)
	{
		String sql = "select count(*) from tb_checkin where uid = ? and DateDiff(DAY, time, Getdate())=0";
		Integer ret = null;
		try {
			ret = (Integer)super.excSql_retValue(sql, new ScalarHandler(), uid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	public Integer getCheckinDays(int uid)
	{
		String sql = "select count(*) from tb_checkin where uid = ?";
		Integer ret = null;
		try {
			ret = (Integer)super.excSql_retValue(sql, new ScalarHandler(), uid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<CheckIn> getAllEntryByUserId(int uid)
	{
		String sql = "select * from tb_checkin where uid = ?";
		ArrayList<CheckIn> ret = null;
		try {
			ret = (ArrayList<CheckIn>)super.getEntrys(sql, uid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
}
