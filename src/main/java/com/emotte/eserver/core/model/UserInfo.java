package com.emotte.eserver.core.model;

import java.util.Map;

public class UserInfo {
	
	/**
	 * 用户ID
	 * 2017年11月22日
	 */
	private Long id;
	/**
	 * 用户请求IP（服务器）
	 * 2017年11月22日
	 */
	private String ip;
	/**
	 * 类型（1:支付宝，2：微信，3：QQ）
	 * 2017年11月22日
	 */
	private Integer type;
	/**
	 * 结合类型对应的账号
	 * 2017年11月22日
	 */
	private String account;
	/**
	 * 设备唯一标识
	 * 2017年11月22日
	 */
	private String uuid;
	/**
	 * 设备号
	 * 2017年11月22日
	 */
	private String imei;
	
	/**
	 * 请求方式
	 * 2017年11月29日
	 */
	private String method;
	
	/**
	 * 用户ip
	 * 2017年12月6日
	 */
	private String userIp;
	
	private Map<String, String> cookies;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Map<String, String> getCookies() {
		return cookies;
	}
	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", ip=" + ip + ", type=" + type
				+ ", account=" + account + ", uuid=" + uuid + ", imei=" + imei
				+ ", cookies=" + cookies + ", method=" + method + ",userIp=" + userIp + "]";
	}
}
