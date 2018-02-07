package com.emotte.eserver.core.db.finance.mapper;

import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.annotation.Mapper;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.finance.model.Comfirm;
import com.emotte.eserver.core.db.local.model.Account;

@Mapper
public interface TestMapper2 {
	@SQL(sql="SELECT * FROM t_fin_income_comfirm s WHERE 1=1 AND s.id = 733949338785812", 
	type=SQLTYPE.SELECT)
	public Map<String, String> query ();
	
	@SQL(sql="SELECT * FROM t_fin_income_comfirm s WHERE ROWNUM < 10", 
			type=SQLTYPE.SELECT)
	public List<Map<String, String>> queryList ();
	
	@SQL(sql="SELECT * FROM t_fin_income_comfirm s WHERE ROWNUM < 10", 
	type=SQLTYPE.SELECT, returnParam=Comfirm.class)
	public List<Account> queryAccountList ();
	
	@SQL(sql="SELECT s.id, s.city, s.deal_money dealMoney FROM t_fin_income_comfirm s WHERE s.id = 733949338785812", 
	type=SQLTYPE.SELECT, returnParam=Comfirm.class)
	public Comfirm queryAccount ();
}
