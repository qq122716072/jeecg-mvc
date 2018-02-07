package com.emotte.eserver.core.reanno.init.impl;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.emotte.eserver.core.annotation.Api;
import com.emotte.eserver.core.annotation.Api.OPERATIONTYPE;
import com.emotte.eserver.core.annotation.Api.REQUESTYPE;
import com.emotte.eserver.core.db.ConnectionManager;
import com.emotte.eserver.core.db.ESessionFactory;
import com.emotte.eserver.core.db.ESessionManager;
import com.emotte.eserver.core.helper.CollectionHelper;
import com.emotte.eserver.core.helper.LogHelper;

/**
 * API注解解释器
 * @author yj
 *
 */
public class ApiAnalysizer {

	public static void operateDB(Class<?> clazz,String version,String name) throws SQLException{
		ESessionFactory factory = null;
		Connection con = null;
		try{
			factory = ESessionManager.getFactory(ApiAnalysizer.class.getName());
			ConnectionManager.createConnection(factory);
			con = ConnectionManager.getThreadLocalConnection();
			Method[] methods = clazz.getMethods();//当前类路径下类对应的方法
			Map<OPERATIONTYPE, List<Map<String, Object>>> mapOper = new HashMap<>(); //key:操作标识 value:对应的操作数据
			List<Map<String, Object>> insertList = new ArrayList<>();// 存放要插入的数据库不存在的注解信息
			List<Map<String, Object>> updateList = new ArrayList<>();//存放数据库已存在且需要修改的注解信息
			for (int i = 0; i < methods.length; i++) {
				Method childMethod = methods[i];
				Api interfDocAnno = childMethod.getAnnotation(Api.class);
				if(interfDocAnno != null){
					Map<String, Object> interMap = new HashMap<>();
					OPERATIONTYPE opType = interfDocAnno.operation();
					String module = interfDocAnno.module();
					String paramsExamp = interfDocAnno.paramsExamp();
					String paramNeeds = interfDocAnno.paramNeeds();
					String ips = interfDocAnno.ip();
					String terminals = interfDocAnno.terminal();
					boolean isTest = interfDocAnno.isTest();
					boolean isEnabled = interfDocAnno.isEnabled();
					String returnSample = interfDocAnno.returnSample();
					String returnExplian = interfDocAnno.returnExplain();
					String empolder = interfDocAnno.empolder();
					REQUESTYPE requestType = interfDocAnno.requestType();
					String method = childMethod.getName();
					interMap.put("module", module);
					interMap.put("params_example", paramsExamp);
					interMap.put("params_need", paramNeeds);
					interMap.put("ip", ips);
					interMap.put("terminal", terminals);
					interMap.put("test", isTest);
					interMap.put("enabled", isEnabled);
					interMap.put("return_sample", returnSample);
					interMap.put("return_explain", returnExplian);
					interMap.put("request_mode", requestType);
					interMap.put("service", clazz.getPackage().getName()+".e-"+name);
					interMap.put("method", method);
					interMap.put("version", version);
					interMap.put("empolder", empolder);
					if(opType == OPERATIONTYPE.INSERT){
						insertList.add(interMap);
					}else if(opType == OPERATIONTYPE.UPDATE){
						updateList.add(interMap);
					}
				}
			}
			if(updateList!=null && updateList.size()>0){
				mapOper.put(OPERATIONTYPE.UPDATE, updateList);
			}
			if(insertList!=null && insertList.size()>0){
				mapOper.put(OPERATIONTYPE.INSERT, insertList);
			}
			dbOper(mapOper, con);
		}catch(NullPointerException e){
			LogHelper.info(ApiAnalysizer.class, "未找到【"+ApiAnalysizer.class+"】对应的数据源");
		}catch (Exception e) {
			LogHelper.error(ApiAnalysizer.class, e.toString());	
		}
	}
	
	/**
	 * 更新和插入操作
	 * yj
	 * 2017年12月4日	
	 * @param mapOper
	 * @param con
	 * @throws SQLException
	 * void
	 */
	public static void dbOper(Map<OPERATIONTYPE, List<Map<String, Object>>> mapOper,Connection con) throws SQLException{
		List<Map<String, Object>> dbList = new ArrayList<>(); //存放数据库查询到的已存在注解的信息
		if(mapOper!=null && mapOper.size()>0){
			List<Map<String, Object>> listIn = mapOper.get(OPERATIONTYPE.INSERT);
			List<Map<String, Object>> listUp= mapOper.get(OPERATIONTYPE.UPDATE);
			if(listIn!=null && listIn.size()>0){
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i <listIn.size(); i++) {
					Map<String, Object>  queryMap = listIn.get(i);
					String service= (String) queryMap.get("service");
					String method = (String) queryMap.get("method");
					String versionName = (String) queryMap.get("version");
					if(StringUtils.isNotBlank(versionName)&&StringUtils.isNotBlank(service)&&StringUtils.isNotBlank(method)){
						if(i==0 && 1==listIn.size()){
							sb.append("SELECT * FROM t_api_config s WHERE (s.SERVICE,s.METHOD,s.VERSION) in (('"+service+"','"+method+"','"+versionName+"'))");
						}else if(i==0 && i<listIn.size()){
							sb.append("SELECT * FROM t_api_config s WHERE (s.SERVICE,s.METHOD,s.VERSION) in (('"+service+"','"+method+"','"+versionName+"')");
						}else if(i==listIn.size()-1 && listIn.size()>1){
							sb.append(",('"+service+"','"+method+"','"+versionName+"'))");
						}else{
							sb.append(",('"+service+"','"+method+"','"+versionName+"')");
						}
					}
				}
				if(!StringUtils.isBlank(sb.toString())){
					PreparedStatement pre;
					pre = con.prepareStatement(sb.toString());
					LogHelper.info(ApiAnalysizer.class, "执行查询数据库是否存在此数据sql: "+sb.toString());
					ResultSet rst = pre.executeQuery();
					while(rst.next()){
						Map<String, Object> dbMap = new HashMap<>();
						String versionName = rst.getString("VERSION");
						String method = rst.getString("METHOD");
						String service = rst.getString("SERVICE");
						dbMap.put("service", service);
						dbMap.put("method", method);
						dbMap.put("version", versionName);
						if(dbMap!=null){
							dbList.add(dbMap);
						}
					}
				}
			}
			if(dbList!=null && dbList.size()>0 && listIn!=null && listIn.size()>0){
				listIn = CollectionHelper.removeDuplicate(listIn,dbList);
			}
			if(listIn!=null && listIn.size()>0){
				StringBuffer sbIn = new StringBuffer("INSERT INTO t_api_config (ID,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,VALID,VERSION,MODULE,SERVICE,METHOD,PARAMS_EXAMPLE,REMARK,PARAMS_NEED,IP,TERMINAL,TEST,ENABLED,RETURN_SAMPLE,RETURN_EXPLAIN,REQUEST_MODE,EMPOLDER)");
				for (int i = 0; i < listIn.size(); i++) {
					Map<String, Object> map = listIn.get(i);
					if(i==0){
						sbIn.append(" SELECT GETSEQ(),1,sysdate,null,null,1,'"+map.get("version")+"','"+map.get("module")+"','"+map.get("service")+"','"+map.get("method")+"','"+map.get("params_example")+"',null,'"+map.get("params_need")+"','"+map.get("ip")+"','"+map.get("terminal")+"',decode('"+map.get("test")+"','true',1,'false',2,''),decode('"+map.get("enabled")+"','true',1,'false',2,''),'"+map.get("return_sample")+"','"+map.get("return_explain")+"','"+map.get("request_mode")+"','"+map.get("empolder")+"' FROM DUAL ");
					}else {
						sbIn.append(" UNION ALL SELECT GETSEQ(),1,sysdate,null,null,1,'"+map.get("version")+"','"+map.get("module")+"','"+map.get("service")+"','"+map.get("method")+"','"+map.get("params_example")+"',null,'"+map.get("params_need")+"','"+map.get("ip")+"','"+map.get("terminal")+"',decode('"+map.get("test")+"','true',1,'false',2,''),decode('"+map.get("enabled")+"','true',1,'false',2,''),'"+map.get("return_sample")+"','"+map.get("return_explain")+"','"+map.get("request_mode")+"','"+map.get("empolder")+"' FROM DUAL ");
					}
				}
				PreparedStatement pre;
				pre = con.prepareStatement(sbIn.toString());
				LogHelper.info(ApiAnalysizer.class, "执行插入操作sql: "+sbIn.toString());
			    pre.executeUpdate();
			}
			if(listUp!=null && listUp.size()>0){
				for (int i = 0; i < listUp.size(); i++) {
					StringBuffer sbUp = new StringBuffer("UPDATE t_api_config  SET UPDATE_TIME=sysdate,UPDATE_BY=1");
					Map<String, Object> mapUp = listUp.get(i);
					for (Map.Entry<String, Object> entry: mapUp.entrySet()) {
						if((entry.getValue()) instanceof String && !entry.getValue().equals("")&& !entry.getKey().equals("service")
								&& !entry.getKey().equals("method") && !entry.getKey().equals("version")){
							sbUp.append( ","+entry.getKey()+"='"+entry.getValue()+"'");
						}else if((entry.getValue()) instanceof Boolean){
							sbUp.append(","+entry.getKey()+"= decode('"+entry.getValue()+"','true',1,'false',2,'') ");
						}else if(entry.getValue() instanceof REQUESTYPE){
							sbUp.append(","+entry.getKey()+"='"+entry.getValue()+"'");
						}
					}
					sbUp.append(" WHERE  service ='"+mapUp.get("service")+"' AND method='"+mapUp.get("method")+"' AND version='"+mapUp.get("version")+"'");
					PreparedStatement pre;
					pre = con.prepareStatement(sbUp.toString());
					LogHelper.info(ApiAnalysizer.class, "执行更新操作sql: "+sbUp.toString());
					pre.executeUpdate();
				}
			}
			
		}
	}
}
