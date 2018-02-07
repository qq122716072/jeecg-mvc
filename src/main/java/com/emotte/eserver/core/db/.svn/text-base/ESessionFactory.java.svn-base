package com.emotte.eserver.core.db;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;

import com.emotte.eserver.core.local.CurrentUserManager;
import com.emotte.eserver.core.model.UserInfo;

/**
 * session工厂
 * @author chj clc
 */
public class ESessionFactory {
	
	//特殊客户标志
	private static final String MANAGER_TYPE = "2";
	private DataSource dataSource;
	private String scanPackage;
	private DataSource otherDataSource;
	
	public DataSource getDataSource() {
		//判断用户信息 来切换数据源 取线程中的userinfo
		UserInfo userinfo = CurrentUserManager.getUser();
		if (userinfo != null) {
			Map<String, String> map = userinfo.getCookies();
			String managertype = MapUtils.getString(map, "managerType");
			if (map != null && MANAGER_TYPE.equals(managertype) && otherDataSource != null) {
				return otherDataSource;
			}
		}
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public DataSource getOtherDataSource() {
		return otherDataSource;
	}

	public void setOtherDataSource(DataSource otherDataSource) {
		this.otherDataSource = otherDataSource;
	}

	
	
	@Override
	public String toString() {
		return "ESessionFactory [dataSource=" + dataSource + ", scanPackage="
				+ scanPackage + "]";
	}
	
}
