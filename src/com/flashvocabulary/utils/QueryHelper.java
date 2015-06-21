package com.flashvocabulary.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * 用于辅助拼接SQL语句
 * 
 * @author LuckyBear（熊嘉男） 
 * 
 */
public class QueryHelper {

	private String selectClause="";
	private String fromClause; // FROM子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private String pageClause="";

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz, String alias) {
		selectClause="SELECT * ";
		fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 * @param params
	 */
	public QueryHelper addCondition(String condition, Object... params) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		// 参数
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}
		}

		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 */
	public QueryHelper addCondition(boolean append, String condition, Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}
	
	public QueryHelper addPageLimit(int startPage,int pageSize)
	{
		this.pageClause=" LIMIT "+startPage+","+pageSize;
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的SQL语句
	 * 
	 * @return
	 */
	public String getListQuerySql() {
		
		return selectClause+fromClause + whereClause + orderByClause+pageClause;
	}

	/**
	 * 获取生成的用于查询总记录数的SQL语句
	 * 
	 * @return
	 */
	public String getCountQuerySql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取SQL中的参数值列表
	 * 
	 * @return
	 */
	public Object[] getParameters() {
		return parameters.toArray();
	}

	

}
