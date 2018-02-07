package com.emotte.eserver.core.db;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.emotte.eserver.core.db.local.model.Account;

public class SqlBuildHandlerTest {

	@Test
	public void test() {
		String sql = "insert into t_fin_bank_account (ID, NAME, BANK, BANKNUM)"+
" values (getseq(), #{name}, #{bank}, #{bankNum})";
		Object[] args = new Object[3];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "cj");
		map.put("bank", "杭州银行");
		map.put("bankNum", 123456);
		Account account = new Account();
		account.setName("cj");
		account.setBank("杭州");
		args[0] = account;
//		System.out.println(args[1] instanceof Number);
		System.out.println(SqlBuildHandler.rebuild(sql, args));
	}

}
