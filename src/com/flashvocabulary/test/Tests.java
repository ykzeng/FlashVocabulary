package com.flashvocabulary.test;

import org.junit.Test;
import com.flashvocabulary.service.*;;

public class Tests {
	
	@Test
	public void test() throws Exception
	{
		ArrangeWordService arrangeWordService = new ArrangeWordService();
		arrangeWordService.ArrangeWord(25);
	}
	
}
