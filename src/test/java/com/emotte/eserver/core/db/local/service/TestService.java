package com.emotte.eserver.core.db.local.service;

import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.local.model.Account;


public interface TestService {
	public Map<String, Object> query();
	
	public List<Map<String, Object>> queryList(Map<String, Object> map);
	
	public List<Account> queryAccountList (Account account);
	
	public Account queryAccount (Map<String, Object> map);
	
	public Account queryAccount (Account account);

	public Account queryAccount (Object[] objects);
	
	public Account queryAccount (Long id);
	
	public Boolean insertAccount(Map<String, Object> map);
	
	public Boolean insertAccount(Account account);
	
	public Boolean insertAccount(Object[] objects);
	
	public Boolean insertAccount(String name, String bank, Long bankNum, Integer valid);
	
	public Boolean updateAccount(Long id);
	
	public Boolean updateAccount(Long id, String name);
	
	public Boolean deleteAccount(Long id);
	
	public Boolean deleteAccount(Long id, Long[] ids);
	
}
