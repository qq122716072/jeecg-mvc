package com.emotte.eserver.core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.emotte.eserver.core.helper.LogHelper;

public class ConnectionManager {
	//使用ThreadLocal保存Connection变量  
	private static ThreadLocal<List<Connection>> connectionHolder = new ThreadLocal<List<Connection>>();
    
    public static void createConnection (ESessionFactory factory) {
    	// ThreadLocal取得当前线程的connection
    	List<Connection> list = connectionHolder.get();
    	if (list == null) {
    		list = new ArrayList<Connection>();
    		connectionHolder.set(list);
    	}
        //如果ThreadLocal没有绑定相应的Connection，创建一个新的Connection
        //并将其保存到本地线程变量中。  
//        if(conn == null) {  
    	try {
    		Connection conn = factory.getDataSource().getConnection();
			// 将当前线程的Connection设置到ThreadLocal  
    		list.add(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//        }
    }
    /** 
     * 连接Connection 
     * @return 
     */  
    public static Connection getThreadLocalConnection() {
    	Connection conn = getTopConnection();
    	LogHelper.debug(ConnectionManager.class, "getThreadLocalConnection()[CONNECTION]:" + conn.toString());
        return conn;
    }
    
    public static void beginTransaction() throws SQLException {
    	//ThreadLocal取得当前线程的connection
        Connection conn = getTopConnection();
        if (null != conn) {
			conn.setAutoCommit(Boolean.FALSE);
        }
    }
    
    public static boolean commit () throws SQLException {
    	//ThreadLocal取得当前线程的connection  
        Connection conn = getTopConnection();
        if (null != conn) {
        	conn.commit();
        	return true;
        }
        return false;
    }
    
    public static boolean rollBack (Savepoint savepoint) throws SQLException {
    	//ThreadLocal取得当前线程的connection  
        Connection conn = getTopConnection();
        if (null != conn) {
        	conn.rollback(savepoint);
        	return true;
        }
        return false;
    }
    
    public static boolean rollBack () throws SQLException {
    	//ThreadLocal取得当前线程的connection  
        Connection conn = getTopConnection();
        if (null != conn) {
        	conn.rollback();
        	return true;
        }
        return false;
    }
    
    /** 
     * 关闭Connection，清除集合中的Connection 
     * @throws SQLException 
     */  
    public static void closeConnection() throws SQLException {  
        //ThreadLocal取得当前线程的connection  
        Connection conn = getTopConnection();  
        //当前线程的connection不为空时，关闭connection.  
        if(conn != null){  
            conn.close();
            connectionHolder.get().remove(conn);
            //connection关闭之后，要从ThreadLocal的集合中清除Connection
            if (connectionHolder.get().size() == 0) {
            	connectionHolder.remove();  
            }
        }  
    }
    
    public static Connection getTopConnection () {
    	List<Connection> list = connectionHolder.get();
    	return list.get(list.size() - 1); // 获取最新的conn
    }
}
