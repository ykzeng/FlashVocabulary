package com.flashvocabulary.dto;

import java.io.Serializable;

/**
 * 有问题请马化腾275083584  可能存在bug
 * @author LuckyBear
 *
 * @param <T>
 */
public interface BaseEntry extends Serializable{
	/**
	 * 返回主键的主键名
	 * @return
	 */
	public  String getPrimaryKey();
	/**
	 * 主键是否是自增长的
	 * @return
	 */
	public Boolean isPK_Increment();
	
	public String getTableName();
}
