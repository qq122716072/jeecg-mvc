package com.emotte.eserver.core.scan;

import com.emotte.eserver.core.reanno.init.impl.InitializeByService;

public class InitialScanner extends Scanner {
	private InitializeByService initialByService = new InitializeByService();
//	private InitializeByMapper initialByMapper = new InitializeByMapper();

	public InitialScanner(String rootPath, ClassLoader classLoader) {
		super(rootPath, classLoader);
	}
	@Override
	public void process(String classname) throws Exception {
		initialByService.init(classname, getClassLoader());
//		initialByMapper.init(classname, getClassLoader());
	}

}
