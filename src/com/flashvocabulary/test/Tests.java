package com.flashvocabulary.test;

import java.sql.Date;
//import java.util.Date;

import java.sql.Timestamp;

import org.junit.Test;

import com.flashvocabulary.dao.impl.CheckInDaoImpl;
import com.flashvocabulary.dao.impl.UserlibDaoImpl;
import com.flashvocabulary.dto.CheckIn;
import com.flashvocabulary.dto.UserLib;
import com.flashvocabulary.utils.IConstants;

public class Tests {
	
	@Test
	public void test() throws Exception
	{
		CheckIn checkIn = new CheckIn("test post", 25, new Timestamp(System.currentTimeMillis()));
		CheckInDaoImpl da = new CheckInDaoImpl();
		da.saveEntry(checkIn);
	}
	
}
