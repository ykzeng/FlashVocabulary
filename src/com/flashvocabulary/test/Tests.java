package com.flashvocabulary.test;

import java.sql.Date;

import org.junit.Test;

import com.flashvocabulary.service.*;;

public class Tests {
	
	@Test
	public void test() throws Exception
	{
		ArrangeWordService arrangeWordService = new ArrangeWordService();
		arrangeWordService.ArrangeWord(25);
	}
	
	@Test
	public void test1() throws Exception
	{
		
		
	}
}
