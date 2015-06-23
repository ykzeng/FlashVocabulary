package com.flashvocabulary.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import com.flashvocabulary.dao.BaseDao;
import com.flashvocabulary.dto.BaseEntry;
import com.flashvocabulary.utils.JDBCUtils;


/**
 * 有问题请马化腾275083584  可能存在bug
 * @author LuckyBear
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	private Class<T> clazz;
	private String primaryKey;
	private Boolean increment;
	
	String tableName="";
	static String tablePredix = "tb_";
	public BaseDaoImpl()
	{
		try {
			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			clazz=(Class<T>) type.getActualTypeArguments()[0];
			T t=clazz.newInstance();
			Method m=clazz.getDeclaredMethod("getPrimaryKey");
			primaryKey=(String) m.invoke(t);
			increment=(Boolean) clazz.getDeclaredMethod("isPK_Increment").invoke(t);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void saveEntry(T t) throws Exception {
		String simpleName=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		//insert into User (id,username,password,phone,cellphone,address,email)  values (?,?,?,?,?,?,?)
		sb.append("insert into ");
		tableName = getTableName(t, simpleName, tableName);
		sb.append(tableName);
		sb.append(" (");
		String attr="";
		Field[] fields=clazz.getDeclaredFields();
		List<Object> params=new ArrayList<Object>();
		String lastsql=" values (";
		for (Field field : fields) {
			String name=field.getName();
			if(name.trim().startsWith("_"))
			{
				continue;
			}
			if(name.trim().equals(primaryKey)&&increment)
			{
				continue;
			}
			String methodname="";
			if (field.getType() == Boolean.TYPE)
			{
				methodname="is"+name.toUpperCase().substring(0, 1)+name.substring(1);
			}
			else
				methodname="get"+name.toUpperCase().substring(0, 1)+name.substring(1);
			attr+=name+",";
			Method m=clazz.getMethod(methodname);
			params.add(m.invoke(t));
			lastsql+="?,";
		}
		attr=attr.substring(0, attr.lastIndexOf(','));
		lastsql=lastsql.substring(0, lastsql.lastIndexOf(','));
		sb.append(attr).append(')').append(lastsql).append(')');
		//System.out.println("saveSql:"+sb.toString());
		excSql(sb.toString(),params.toArray());
	}

	private String getTableName(T t, String simpleName, String tableName) {
		if(t instanceof BaseEntry)
		{
			tableName=((BaseEntry)t).getTableName();
			if(tableName==null||tableName.trim().equals(""))
			{
				tableName=simpleName;
			}
		}
		return tableName;
	}

	@Override
	public void updateEntry(T t) throws Exception{
		
		String simpleName=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		String attr="";
		Field[] fields=clazz.getDeclaredFields();
		
		String keyValue="";
		List<Object> params=new ArrayList<Object>();
		
		sb.append("update ");
		tableName = getTableName(t, simpleName, tableName);
		sb.append(tableName);
		sb.append(" set ");
		//update User set  username=? , password=? , phone=? , cellphone=? , address=? , email=?  where id=?
		for (Field field : fields) {
			
			String name=field.getName();
			if(name.trim().startsWith("_"))
			{
				continue;
			}
			String methodname="";
			if (field.getType() == Boolean.TYPE)
			{
				methodname="is"+name.toUpperCase().substring(0, 1)+name.substring(1);
			}
			else
				methodname="get"+name.toUpperCase().substring(0, 1)+name.substring(1);
			if(name.trim().equals(primaryKey))
			{
				keyValue=clazz.getMethod(methodname).invoke(t).toString();
				continue;
			}
			
			attr+=(" "+name+"=? ,");
			Method m=clazz.getMethod(methodname);
			params.add(m.invoke(t));
		}
		attr=attr.substring(0, attr.lastIndexOf(","));
		attr+=" where "+primaryKey+"=?";
		sb.append(attr);
		params.add(keyValue);
		System.out.println("updateSql:"+sb.toString());
		excSql(sb.toString(),params.toArray());
		
	}

	@Override
	public void deleteEntry(Serializable id) throws Exception {
		String simplename=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ");
		sb.append(tablePredix+simplename);
		sb.append(" where ");
		sb.append(primaryKey+"=?");
		
		System.out.println(sb.toString());
		
		excSql(sb.toString(), id);
		
	}

	@Override
	public T getEntry(Serializable id) throws Exception {
		String simpleName=clazz.getSimpleName();
		String sql="select * from "+(tablePredix+simpleName)+" where "+primaryKey+"=?";
		return getEntry(sql,id);
	}

	@Override
	public List<T> getAllEntrys() throws Exception {
		String simpleName=clazz.getSimpleName();
		String sql="select * from "+(tablePredix+simpleName);
		return getEntrys(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntrys(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return (List<T>) qr.query(JDBCUtils.getConnection(),sql, new BeanListHandler(clazz),
				objs);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntry(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return (T) qr.query(JDBCUtils.getConnection(),sql, new BeanHandler(clazz), objs);
	}

	@Override
	public void excSql(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(),sql,objs);
	}

	@Override
	public Object excSql_retValue(String sql,ResultSetHandler sh, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return qr.query(JDBCUtils.getConnection(),sql,sh,objs);
	}


}
