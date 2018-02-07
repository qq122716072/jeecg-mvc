package com.emotte;

import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.helper.SystemHelper;
import com.emotte.eserver.core.scan.InitialScanner;

public class BaseTestService {
//	@Before
	
	public static void init() throws Exception {
		Context.initialize("classpath:context.xml");
		InitialScanner scanner = new InitialScanner(getRootPath(), ClassLoader.getSystemClassLoader());
		scanner.scanClassPath("target/test-classes", "com.emotte");
	}
	
	private static String getRootPath () {
		String s = SystemHelper.getCurrentPath(0);
		System.out.println(s);
		return s;
	}

}
