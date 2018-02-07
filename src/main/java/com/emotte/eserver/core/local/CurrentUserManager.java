package com.emotte.eserver.core.local;

import com.emotte.eserver.core.model.UserInfo;

/**
 * 用户信息本地线程管理器
 * @author ChengJing
 * 2017年12月11日
 */
public class CurrentUserManager {
	private static ThreadLocal<UserInfo> userHolder = new ThreadLocal<UserInfo>();

	public static UserInfo getUser() {
		return userHolder.get();
	}
	
	public static void setUser(UserInfo user) {
		userHolder.set(user);
	}
}
