package com.emotte.eserver.core.db.local.mapper;

import java.util.List;

import com.emotte.eserver.core.db.annotation.Mapper;
import com.emotte.eserver.core.db.annotation.Params;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.annotation.SelectKey;
import com.emotte.eserver.core.db.local.model.Example;

@Mapper
public interface WrExampleMapper {

	public static final String COLUMNS = "ID, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, VALID, VERSION";
	/**
	 * 插入
	 * @param example
	 * 2017年10月22日
	 */
	@SQL(sql="INSERT INTO T_EXAMPLE_EXAMPLE (" + COLUMNS 
		+ ") VALUES("
			+ "#{id} "
			+ ",#{createBy} "
			+ ",to_date(#{createTime}, 'yyyy-mm-dd hh24:mi:ss') "
			+ ",#{updateBy} "
			+ ",to_date(#{updateTime}, 'yyyy-mm-dd hh24:mi:ss') "
			+ ",#{valid} "
			+ ",#{version} "
		+ ")", type=SQLTYPE.INSERT, selectKey=@SelectKey(sql="SELECT getseq() FROM DUAL", prop="id", returnType=Long.class))
	public Long insert(Example example);
	/**
	 * 更新
	 * @param example
	 * @return
	 * 2017年10月22日
	 */
	@SQL(sql="UPDATE T_EXAMPLE_EXAMPLE SET "
			+ "@IF(#{id} != null && #{id} != '')[ID = #{id}]"
			+ "@IF(#{createBy} != null && #{createBy} != '')[,CREATE_BY = #{createBy}]"
			+ "@IF(#{createTime} != null && #{createTime} != '')[,CREATE_TIME = to_date(#{createTime}, 'yyyy-mm-dd hh24:mi:ss')]"
			+ "@IF(#{updateBy} != null && #{updateBy} != '')[,UPDATE_BY = #{updateBy}]"
			+ "@IF(#{updateTime} != null && #{updateTime} != '')[,UPDATE_TIME = to_date(#{updateTime}, 'yyyy-mm-dd hh24:mi:ss')]"
			+ "@IF(#{valid} != null && #{valid} != '')[,VALID = #{valid}]"
			+ "@IF(#{version} != null && #{version} != '')[,VERSION = #{version}]"
		+ " WHERE "
			+ " ID = #{id}"
		+ "", type=SQLTYPE.UPDATE)
	public int update(Example example);
	/**
	 * 更新
	 * @param example
	 * @return
	 * 2017年10月22日
	 */
	@SQL(sql="@FOREACH(collection=#{0}, item=item, separate=';')[UPDATE T_EXAMPLE_EXAMPLE SET "
			+ "@IF(#{id} != null && #{item.id} != '')[ID = #{item.id}]"
			+ "@IF(#{createBy} != null && #{item.createBy} != '')[,CREATE_BY = #{item.createBy}]"
			+ "@IF(#{createTime} != null && #{item.createTime} != '')[,CREATE_TIME = to_date(#{item.createTime}, 'yyyy-mm-dd hh24:mi:ss')]"
			+ "@IF(#{updateBy} != null && #{item.updateBy} != '')[,UPDATE_BY = #{item.updateBy}]"
			+ "@IF(#{updateTime} != null && #{item.updateTime} != '')[,UPDATE_TIME = to_date(#{item.updateTime}, 'yyyy-mm-dd hh24:mi:ss')]"
			+ "@IF(#{valid} != null && #{item.valid} != '')[,VALID = #{item.valid}]"
			+ "@IF(#{version} != null && #{item.version} != '')[,VERSION = #{item.version}]"
			+ " WHERE "
				+ " ID = #{id}"
			+ "]", type=SQLTYPE.UPDATE)
	public int multiUpdate(List<Example> list);
	/**
	 * 批量插入
	 * @param data
	 * @return
	 * 2017年10月22日
	 */
	 @SQL(sql="INSERT INTO T_EXAMPLE_EXAMPLE (" + COLUMNS + ") "
		+ "@FOREACH(collection=#{0}, item=item, separate='UNION ALL')["
		+ "SELECT "
			+ "getseq() "
			+ ",#{item.createBy} "
			+ ",to_date(#{item.createTime}, 'yyyy-mm-dd hh24:mi:ss') "
			+ ",#{item.updateBy} "
			+ ",to_date(#{item.updateTime}, 'yyyy-mm-dd hh24:mi:ss') "
			+ ",#{item.valid} "
			+ ",#{item.version} "
		+ " FROM DUAL"
		+ "]", type=SQLTYPE.UPDATE)
	public int multiInsert(List<Example> data);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * 2017年10月22日
	 */
	 @SQL(sql="DELETE T_EXAMPLE_EXAMPLE"
		+ " WHERE"
		+ " id IN (@FOREACH(collection=#{ids}, item=id, separate=',')[#{id}])"
		+ "", type=SQLTYPE.DELETE)
	public int multiDelete(@Params("ids")Long[] ids);
}