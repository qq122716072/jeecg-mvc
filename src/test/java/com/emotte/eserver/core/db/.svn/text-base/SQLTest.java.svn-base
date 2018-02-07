package com.emotte.eserver.core.db;

import org.junit.Test;

import com.emotte.eserver.core.db.local.mapper.CommentMapper;
import com.emotte.eserver.core.db.local.mapper.TestMapper;
import com.emotte.eserver.core.db.local.model.Appraise;
import com.emotte.eserver.core.db.proxy.SQLSessionProxy;

public class SQLTest {
	private ESessionFactory sessionFactory;
	@Test
	public void test() throws Exception {
//		initDataSource();
//		ConnectionManager.createConnection(sessionFactory);
		CommentMapper mapper = (CommentMapper) SQLSessionProxy.getInstance(CommentMapper.class);
		Appraise para = new Appraise();
		para.setIsFisrt(1);
		System.out.println(mapper.queryAppraiseForStarListPage(para, 1, 2));
	}

	private void initDataSource() {
		EDataSource dataSource = new EDataSource();
		// 2、为数据源实例指定必须的属性  
		dataSource.setUsername("account");  
		dataSource.setPassword("123123");  
		dataSource.setUrl("jdbc:oracle:thin:@192.168.220.9:1521:orcl");  
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		// 3、指定数据源的一些可选属性  
		// 3.1、指定数据库连接池中出使化连接数的个数  
		dataSource.setInitialSize(5);  
		// 3.2、指定最大连接数：同意时刻可以同时向   数据库申请的连接数  
		dataSource.setMaxTotal(20);  
		// 3.3、指定最小连接数:在数据库连接池中保存的最少的空闲连接的数量  
		dataSource.setMinIdle(2); 
		dataSource.setMaxIdle(10);
		// 3.4、等待数据库连接池分配连接的最长时间。单位为毫秒。超出时间将抛出异常  
		dataSource.setMaxWaitMillis(1000 * 5);
		sessionFactory = new ESessionFactory();
		sessionFactory.setDataSource(dataSource);
	}
}
