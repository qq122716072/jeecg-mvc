package com.emotte.eserver.interf.impl;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.emotte.eserver.core.annotation.EService;
import com.emotte.eserver.core.db.local.model.Account;
import com.emotte.eserver.core.db.local.service.TestService;
import com.emotte.eserver.core.helper.JSONObjectHelper;
import com.emotte.eserver.interf.EAccountService;
@EService
public class EAccountServiceImpl implements EAccountService {
	@Resource
	private TestService service;
	@Override
	public String query(String data) {
		JSONObject obj = JSONObject.fromObject(data);
		String id = JSONObjectHelper.getJsonObjectValue(obj, "id", false);
		Account account = service.queryAccount(Long.parseLong(id));
		JSONObject result = JSONObject.fromObject(account);
		return result.toString();
	}

}
