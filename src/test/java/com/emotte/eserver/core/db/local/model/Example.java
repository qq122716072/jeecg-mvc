package com.emotte.eserver.core.db.local.model;

import java.io.Serializable;

/**
 * : T_EXAMPLE_EXAMPLE
 * 2017年10月22日
 */
public class Example implements Serializable {
 	/**
	 * TODO
	 * 2017年10月22日
	 */
	private static final long serialVersionUID = 1L;

 	/**
     *  : ID
	 * 2017年10月22日
	 */ 
	private Long id;
 	/**
     *  : CREATE_BY
	 * 2017年10月22日
	 */ 
	private Long createBy;
 	/**
     *  : CREATE_TIME
	 * 2017年10月22日
	 */ 
	private String createTime;
 	/**
     *  : UPDATE_BY
	 * 2017年10月22日
	 */ 
	private Long updateBy;
 	/**
     *  : UPDATE_TIME
	 * 2017年10月22日
	 */ 
	private String updateTime;
 	/**
     *  : VALID
	 * 2017年10月22日
	 */ 
	private Integer valid;
 	/**
     *  : VERSION
	 * 2017年10月22日
	 */ 
	private Integer version;
	/** 
	 * id数组
	 * 2017年10月22日
	 */
	private String[] ids;
	
	/**
	 *  : ID
	 * @return
	 * 2017年10月22日
	 */
	public Long getId() {
		return id;
	}
	/**
	 *  : ID
	 * @return
	 * 2017年10月22日
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 *  : CREATE_BY
	 * @return
	 * 2017年10月22日
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 *  : CREATE_BY
	 * @return
	 * 2017年10月22日
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 *  : CREATE_TIME
	 * @return
	 * 2017年10月22日
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 *  : CREATE_TIME
	 * @return
	 * 2017年10月22日
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 *  : UPDATE_BY
	 * @return
	 * 2017年10月22日
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 *  : UPDATE_BY
	 * @return
	 * 2017年10月22日
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 *  : UPDATE_TIME
	 * @return
	 * 2017年10月22日
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 *  : UPDATE_TIME
	 * @return
	 * 2017年10月22日
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 *  : VALID
	 * @return
	 * 2017年10月22日
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 *  : VALID
	 * @return
	 * 2017年10月22日
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 *  : VERSION
	 * @return
	 * 2017年10月22日
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 *  : VERSION
	 * @return
	 * 2017年10月22日
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	/**
	 * id数组
	 * @return 
	 * 2017年10月22日
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * id数组
	 * @param ids
	 * 2017年10月22日
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	@Override
	public String toString() {
		return "Example["
		+ "id=" + id
		+ ", createBy=" + createBy
		+ ", createTime=" + createTime
		+ ", updateBy=" + updateBy
		+ ", updateTime=" + updateTime
		+ ", valid=" + valid
		+ ", version=" + version
		+ "]";
	}
	
}