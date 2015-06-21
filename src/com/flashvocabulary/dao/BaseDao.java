package com.flashvocabulary.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 * 有问题请马化腾275083584  可能存在bug
 * @author LuckyBear
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	public void saveEntry(T t) throws Exception;
	public void updateEntry(T t) throws Exception;
	public void deleteEntry(Serializable id) throws Exception;
	public T getEntry(Serializable id)  throws Exception;
	public List<T> getAllEntrys()  throws Exception;
	public List<T> getEntrys(String sql,Object... objs)throws Exception;
	public T getEntry(String sql,Object... objs)  throws Exception;
	public void excSql(String sql,Object... objs)throws Exception;
	public Object excSql_retValue(String sql,ResultSetHandler rh,Object... objs) throws Exception;

}
