package com.emotte.eserver.core.db.local.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emotte.eserver.core.annotation.Service;
import com.emotte.eserver.core.db.local.mapper.TestMapper;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.service.TestService;
@Service
public class TestServiceImpl implements TestService {
	@Resource
	private TestMapper test;
	
	@Override
	public Map<String, Object> query() {
		return test.query();
	}
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return test.queryList(map);
	}
	@Override
	public List<Account> queryAccountList (Account account) {
		return test.queryAccountList(account);
	}
	@Override
	public Account queryAccount (Map<String, Object> map) {
		return test.queryAccount(map);
	}
	@Override
	public Account queryAccount(Account account) {
		return test.queryAccount(account);
	}
	@Override
	public Account queryAccount (Object[] objects) {
		return test.queryAccount(objects);
	}
	@Override
	public Account queryAccount(Long id) {
		return test.queryAccount(id);
	}
	@Override
	public Boolean insertAccount(Map<String, Object> map) {
		int i = test.insertAccount(map);
		if (i == 0) return false;
		return true;
	}
	
	@Override
	public Boolean insertAccount(Account account) {
		Long i = test.insertAccount(account);
		System.out.println(i);
		if (i > 0) return true;
		return false;
	}
	@Override
	public Boolean insertAccount(Object[] objects) {
		int i = test.insertAccount(objects);
		if (i == 0) return false;
		return true;
	}
	@Override
	public Boolean insertAccount(String name, String bank, Long bankNum, Integer valid) {
		int i = test.insertAccount(name, bank, bankNum, valid);
		if (i == 0) return false;
		return true;
	}
	@Override
	public Boolean updateAccount(Long id) {
		int i = test.updateAccount(id);
		if (i == 0) return false;
		return true;
	}
	
	@Override
	public Boolean updateAccount(Long id, String name) {
		int i = test.updateAccount(id, name);
		if (i == 0) return false;
		return true;
	}
	
	@Override
	public Boolean deleteAccount(Long id) {
		int i = test.deleteAccount(id);
		if (i == 0) return false;
		return true;
	}
	@Override
	public Boolean deleteAccount(Long id, Long[] ids) {
		int i = test.deleteAccount(id, ids);
		return i > 0;
	}
}
