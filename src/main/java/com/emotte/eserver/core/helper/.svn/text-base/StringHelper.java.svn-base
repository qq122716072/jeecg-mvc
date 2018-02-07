package com.emotte.eserver.core.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串辅助类
 * @author ChengJing
 * 2015年5月29日
 */
public class StringHelper {
	
	/**
	 * 判断是否字符串str1是否包含 regex
	 * @param str1
	 * @param regex
	 * @return 返回判断结果
	 * 2014年8月15日
	 */
	public static boolean isExist(String str1, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str1);
		return m.find();
	}
	
	/**
	 * 将字符串中满足正则表达式特殊字符的进行转义
	 * @param s
	 * @return
	 * 2014年11月25日
	 */
	public static String toRegexString (String s) {
		if (s.contains("|")) {
			return s.replace("|", "\\|");
		}
		return s;
	}
	
	/**
	 * 查找可以匹配的字符串
	 * @param string 待匹配的字符串
	 * @param regex 正则表达式
	 * @return 返回匹配的字符串
	 * 2014年8月28日
	 */
	public static String findMatche(String string, String regex) {
		String result = null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		if (m.find()) {
			result = m.group();
		}
		return result;
	}
	
	/**
	 * 抽取出所有符合条件的字符串
	 * @param s 待抽取字符串
	 * @param regex 正则表达式
	 * @return String[]
	 * 2015年6月18日
	 */
	public static String[] findMatches (String string, String regex) {
		String[] result = null;
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		while (m.find()) {
			list.add(m.group());
		}
		result = new String[list.size()];
		return list.toArray(result);
	}
	
	/**
	 * 将空值转换为字符串 ""
	 * @param str
	 * @return
	 * 2014年11月15日
	 */
	public static String null2String (String str) {
		if (null == str) {
			str = "";
		}
		return str;
	}
	
	/**
	 * 字符串转换为整型
	 * @param str
	 * @return
	 * 2014年12月29日
	 */
	public static int string2Int(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) {
		String sql = "insert into t_fin_bank_account (ID, NAME, BANK, BANKNUM)"+
"values (getseq(), #{name}, #{bank}, #{bankNum})";
//		System.out.println(findMatche(sql, "#\\{.*?\\}"));
		System.out.println(Arrays.toString(findMatches(sql, "#\\{.*?\\}")));
	}
}
