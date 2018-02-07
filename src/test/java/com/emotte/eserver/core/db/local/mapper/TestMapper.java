package com.emotte.eserver.core.db.local.mapper;

import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.annotation.Mapper;
import com.emotte.eserver.core.db.annotation.Params;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.annotation.SelectKey;
import com.emotte.eserver.core.db.local.model.Account;

@Mapper
public interface TestMapper {
	
	static final String INSERT_SQL = "insert into t_fin_bank_account (ID, NAME, BANK, BANKNUM, VALID)"+
" values (#{id}, #{name}, #{bank}, #{bankNum}, #{valid})";
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE a.id = 509444919032868", type=SQLTYPE.SELECT)
	public Map<String, Object> query ();
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 @IF(#{name} != null)(AND a.name=#{name}) @IF(#{id}!=null)(AND a.id=#{id})", type=SQLTYPE.SELECT)
	public List<Map<String, Object>> queryList (Map<String, Object> map);
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 @IF(#{name} != null)(AND a.name=#{name}) @IF(#{id}!=null)(AND a.id=#{id})", type=SQLTYPE.SELECT, returnParam=Account.class)
	public List<Account> queryAccountList (Account account);
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 and a.id = #{id}", type=SQLTYPE.SELECT, returnParam=Account.class)
	public Account queryAccount (Map<String, Object> map);
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 and a.id = #{id}", type=SQLTYPE.SELECT, returnParam=Account.class)
	public Account queryAccount (Account account);
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 and a.id = #{id}", type=SQLTYPE.SELECT, returnParam=Account.class)
	public Account queryAccount (Object[] objects);
	
	@SQL(sql="SELECT * FROM t_fin_bank_account a WHERE 1=1 and a.id = #{id}", type=SQLTYPE.SELECT, returnParam=Account.class)
	public Account queryAccount (Long id);
	
	@SQL(sql=INSERT_SQL, type=SQLTYPE.INSERT)
	public int insertAccount(Map<String, Object> map);
	
	@SQL(sql=INSERT_SQL, type=SQLTYPE.INSERT, selectKey=@SelectKey(sql="select getseq() from dual", prop="id", returnType=Long.class))
	public Long insertAccount(Account account);
	
	@SQL(sql=INSERT_SQL, type=SQLTYPE.INSERT)
	public int insertAccount(Object[] objects);
	
	@SQL(sql=INSERT_SQL, type=SQLTYPE.INSERT)
	public int insertAccount(String name, String bank, Long bankNum, Integer valid);
	@SQL(sql="INSERT INTO t_fin_bank_account (ID, NAME, BANK, BANKNUM, VALID) "
			+ "@FOREACH(collection=#{0}, item=account, separate=';')["
			+ "SELECT getseq(), #{account.name}, #{account.bank}, #{account.bankNum}, 1 FROM DUAL"
			+ "]", type=SQLTYPE.INSERT)
	public int insertAccount(List<Account> list);
	@SQL(sql="UPDATE t_fin_bank_account a SET a.name = '张三' WHERE a.id = #{id}", type=SQLTYPE.UPDATE)
	public int updateAccount(Long id);
	@SQL(sql="@FOREACH(collection=#{0}, item=detail, separate=';')[UPDATE t_fin_bank_account d "
			+ "SET d.name = #{detail.name}"
			+ ", d.bank = #{detail.bank}"
			+ ", d.update_time = SYSDATE"
			+ " WHERE d.id = #{detail.id}]", type=SQLTYPE.UPDATE)
	public int updateAccountList(List<Account> list);
	@SQL(sql="UPDATE t_fin_bank_account a SET a.name = #{name} WHERE a.id = #{id}", type=SQLTYPE.UPDATE)
	public int updateAccount(@Params("id")Long id, @Params("name")String name);
	
	@SQL(sql="DELETE t_fin_bank_account a WHERE a.id = #{id}", type=SQLTYPE.DELETE)
	public int deleteAccount(Long id);
	
	@SQL(sql="DELETE t_fin_bank_account a WHERE 1=1 @IF(#{id} != null)[AND id=#{id}] AND a.id in (@FOREACH(collection=#{ids}, item=id, separate=',')[#{id}])", type=SQLTYPE.DELETE)
	public int deleteAccount(@Params("id")Long id, @Params("ids")Long[] ids);
}
