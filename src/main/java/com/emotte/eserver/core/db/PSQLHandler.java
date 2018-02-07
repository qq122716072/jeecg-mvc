package com.emotte.eserver.core.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ognl.Ognl;
import ognl.OgnlException;

public class PSQLHandler {
	
	/**
	 * 最终的sql 可能有几个 
	 */
	List<String> sql = new ArrayList<String>();
	
	/**
	 * 最终的参数 与sql对应  可能有几个 
	 */
	List<Object[]> argslist = new ArrayList<Object[]>();;
	
	public List<String> getSql() {
		return sql;
	}

	public List<Object[]> getArgslist() {
		return argslist;
	}


	public PSQLHandler(String sql, Object[] args , Map<String,Object> map) {
		if(sql == null || sql.isEmpty() || args == null || args.length == 0){
			this.sql.add(sql);
		}
		String lowersql = sql.toLowerCase();
		if (lowersql.contains("@foreach")){
			List<SQLPart> forlist = foreachSQL( sql, args , map);
			for(SQLPart part : forlist){
				this.argslist.add(analysisSql(part).toArray());
				this.sql.add(part.getSql().replaceAll("#\\{(.*?)\\}", "?"));
			}
		}else if(lowersql.contains("@if")){
			SQLPart part = ifSQL( sql, map);
			this.argslist.add(analysisSql(part).toArray());
			this.sql.add(part.getSql().replaceAll("#\\{(.*?)\\}", "?"));
		}else{
			SQLPart part = new SQLPart(sql, map);
			this.argslist.add(analysisSql(part).toArray());
			this.sql.add(part.getSql().replaceAll("#\\{(.*?)\\}", "?"));
		}
	}

	
	/**
	 * 分析sql 获得带顺序的参数值
	 * @param part
	 * @return
	 */
	List<Object> analysisSql(SQLPart part){
		List<Object> retlist = new ArrayList<Object>();
		//UPDATE t_exam_demand SET ID = #{item.id} ,mm=#{sdaf}  ,name = #{item.id}
		List<List<String>> list = getRegexGroups(part.getSql(), "#\\{(.*?)\\}");
		for(List<String> li : list){
			try {
				retlist.add(Ognl.getValue(li.get(1).trim(), part.getContext()));
			} catch (OgnlException e) {
				e.printStackTrace();
			}
		}
		return retlist;
	}
	
	
	/**
	 * 处理带@foreach的长sql 变为sql片段列表
	 * @param sql
	 * @param args
	 * @param map
	 * @return
	 */
	public List<SQLPart> foreachSQL(String sql,  Object[] args , Map<String,Object> map) {
		List<SQLPart> parts = new ArrayList<SQLPart>();
		sql = sql.replaceAll("\\{\\s*", "{").replaceAll("\\s*\\}", "}").replaceAll("\\s*\\.\\s*", ".");
		List<String> grouplist = getRegexGroup(sql,"@foreach.*?\\((.*?)\\)\\[(.*)\\]",0);
		if (grouplist != null && grouplist.size() >= 3) {
			String forstr = grouplist.get(1);
			forstr = forstr.replaceAll(" ", "");
			List<String> forlist = getRegexGroup(sql, ".*?=#\\{(\\d)\\},.*?=(.*?),.*?='(.*?)'", 0);
			Object arg = args[Integer.parseInt(forlist.get(1))];
			String item = forlist.get(2);
			String separate = forlist.get(3);
			Object[] beans = arg.getClass().isArray() ? (Object[]) arg : arg instanceof List ? ((List<?>) arg).toArray() : null;
			for (Object bean : beans) {
				Map<String,Object> argsmap = new HashMap<String,Object>();
				argsmap.put(item , bean);
				if(map != null && !map.isEmpty()){
					argsmap.putAll(map);
				}
				SQLPart sqlpart = ifSQL(grouplist.get(2), argsmap);
				sqlpart.setSeparate(separate);
				parts.add(sqlpart);
			}
		}
		return parts;
	}


	/**
	 * 处理if语句 因为此sql参数 所在参数环境一致 所以返回单独sqlpart
	 * @param sql
	 * @param args
	 * @return
	 */
	private   SQLPart ifSQL(String sql, Object args) {
		List<List<String>> grouplist = getRegexGroups(sql,"@if\\((.*?)\\)\\[(.*?)\\]");
		for (List<String> list : grouplist) {
			String ifsql =  list.get(1).replaceAll("#\\{(.*?)\\}", "$1");
			sql = sql.replace(list.get(0), checkExpress(ifsql , args) ? list.get(2) : "");
		}
		return new SQLPart(sql,args);
	}
	
	
	/**
	 * ognl表达式计算 
	 * @param expression
	 * @param context
	 * @return
	 */
	public static Boolean  checkExpress(String expression, Object context) {
		try {
			return (Boolean) Ognl.getValue(expression, context);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 获得字符串中符合表达式的所有捕获组
	 * 
	 * @param sql
	 * @param regex
	 * @return
	 */
	private static List<List<String>> getRegexGroups(String sql, String regex) {
		List<List<String>> list = new ArrayList<List<String>>();
		Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			int o = m.groupCount();
			List<String> children = new ArrayList<String>();
			for (int i = 0; i <= o; i++) {
				children.add(m.group(i));
			}
			list.add(children);
		}
		return list;
	}

	/**
	 * 获得字符串中符合表达式的指定捕获组
	 * 
	 * @param sql
	 * @param regex
	 * @param groupIndex
	 * @return
	 */
	private static List<String> getRegexGroup(String sql, String regex, int groupIndex) {
		List<List<String>> list = getRegexGroups(sql, regex);
		if (list.size() > groupIndex) {
			return list.get(groupIndex);
		}
		return null;
	}

	/**
	 * 获得字符串中符合表达式的指定捕获组的指定列
	 * 
	 * @param sql
	 * @param regex
	 * @param groupIndex
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getRegexGroupVal(String sql, String regex, int groupIndex, int colIndex) {
		List<String> list = getRegexGroup(sql, regex, groupIndex);
		if (list != null && list.size() > colIndex) {
			return list.get(colIndex);
		}
		return null;
	}

	
	/**
	 * sql段
	 * @author clc
	 */
	static class SQLPart{
		String sql;
		Object context;
		String separate = "";
		public SQLPart(String sql, Object context) {
			super();
			this.sql = sql;
			this.context = context;
		}
		public SQLPart(String sql, Object context, String separate) {
			super();
			this.sql = sql;
			this.context = context;
			this.separate = separate;
		}
		public String getSql() {
			return sql;
		}
		public void setSql(String sql) {
			this.sql = sql;
		}
		public Object getContext() {
			return context;
		}
		public void setContext(Object context) {
			this.context = context;
		}
		public String getSeparate() {
			return separate;
		}
		public void setSeparate(String separate) {
			this.separate = separate;
		}
	}
	

}
