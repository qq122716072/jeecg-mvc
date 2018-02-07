package com.emotte.eserver.core.helper;

import net.sf.json.JSONObject;

import com.emotte.eserver.core.exception.BaseException;
import com.emotte.eserver.core.exception.ParamsException;

public class JSONObjectHelper {
	/**
	 * 获取jsonObject中关键字对应的value
	 * @param jsonObj
	 * @param key
	 * @param canNull 是否允许为空
	 * @return
	 * @throws BaseException, Exception
	 * 2016年9月14日
	 */
	public static String getJsonObjectValue (JSONObject jsonObj, String key, boolean canNull) throws ParamsException {
		Object obj = jsonObj.get(key);
		if (!canNull && (null == obj || "null|\"null\"".contains(obj.toString().toLowerCase()))) {
			throw new ParamsException(key + " 不能为空");
		}
		return obj == null ? null : obj.toString();
	}
	
	public static String getJsonObjectValue (JSONObject jsonObj, String key, boolean canNull, boolean isNum) throws ParamsException {
		String s = getJsonObjectValue(jsonObj, key, canNull);
		if (s != null && isNum && !s.matches("\\d+")) {
			throw new ParamsException(key + " 不是数字");
		}
		return s;
	}
	
	public static String getJsonObjectValue (JSONObject jsonObj, String key, boolean canNull, boolean isNum, boolean canContainComma) throws ParamsException {
		String s = getJsonObjectValue(jsonObj, key, canNull);
		if (s != null && isNum) {
			boolean isN = false; // 是否为数字
			if (canContainComma) {
				isN = s.replace(",", "").matches("\\d+");
			} else {
				isN = s.matches("\\d+");
			}
			if (!isN) {
				throw new ParamsException(key + " 不是数字");
			}
		}
		return s;
	}
	
	public static void main(String[] args) throws ParamsException {
		JSONObject json = new JSONObject();
		json.accumulate("mobiles", "1,2"); // 必填
		json.accumulate("userIds", null); // 选填
		json.accumulate("userNames", "旺财"); // 选填
//		System.err.println(PartnerException(json, "mobiles", true, true, false));
	}
	
	/**
	 * 获取json中关键字对应的value
	 * @param json
	 * @param key
	 * @param canNull
	 * @return
	 * @throws SMSException
	 * @throws Exception
	 * 2016年9月14日
	 */
	public static String getJsonObjectValue (String json, String key, boolean canNull) throws ParamsException {
		JSONObject jsonObj = JSONObject.fromObject(json);
		Object obj = jsonObj.get(key);
		if (!canNull && null == obj) {
			throw new ParamsException(key + " 不能为空");
		}
		return obj.toString();
	}
	
	public static String getJsonObjectValue (String json, String key, boolean canNull, boolean isNum) throws ParamsException {
		String s = getJsonObjectValue(json, key, canNull);
		if (s != null && isNum && !s.matches("\\d+")) {
			throw new ParamsException(key + " 不是数字");
		}
		return s;
	}
}
