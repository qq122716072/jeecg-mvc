package com.emotte.eserver.core.db.local.model;

import java.util.List;

/**
 * : T_EMP_APPRAISE
 */
public class Appraise {

	// 主键 : ID
	private Long id;

	// 是否首评 1：否，2是
	// 如果是评价：默认2
	// 如果是评论：默认1 : IS_FISRT
	private Integer isFisrt;

	// 是否显示 1：否，2：是
	// 如果是评价：默认2
	// 如果是评论：默认1 : IS_SHOW
	private Integer isShow;

	// 是否下户 1：否，2：是
	// 如果是商品类的，置为NULL : IS_SH
	private Integer isSh;

	// 订单ID : ORD_ID
	private Long ordId;

	// 商品类型 : ORD_TYPE
	private Integer ordType;

	// 评价内容 : ORD_CONTENT
	private String ordContent;

	// 服务人员或商品ID : RE_ID
	private Long reId;

	// 物流ID : LOGISTIC_ID
	private Long logisticId;

	// 物流评价 : LOGISTIC_CONTENT
	private String logisticContent;

	// 评价标签 多个用分号分隔，最多允许3个 : TAGS
	private String tags;

	// 分1,2,3,4,5个等级，其中1是差评，2,3中评，4,5好评
	// 如果是评论：则为NULL : LEVEL
	private Integer starLevel;

	// 评论者ID : APPRAISER
	private Long appraiser;

	// 评论者姓名 : APPRAISER_NAME
	private String appraiserName;

	// 用户头像 : APPRAISER_PIC
	private String appraiserPic;

	// 评论日期 : CREATE_TIME
	private String createTime;

	// 晒图1 个逗号分隔 : IMG
	private String img;

	// 回复
	private List<AppraiseReply> reply;

	public List<AppraiseReply> getReply() {
		return reply;
	}

	public void setReply(List<AppraiseReply> reply) {
		this.reply = reply;
	}

	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 是否首评 1：否，2是 如果是评价：默认2 如果是评论：默认1 : IS_FISRT
	 * 
	 * @return
	 */
	public Integer getIsFisrt() {
		return isFisrt;
	}

	/**
	 * 是否首评 1：否，2是 如果是评价：默认2 如果是评论：默认1 : IS_FISRT
	 * 
	 * @return
	 */
	public void setIsFisrt(Integer isFisrt) {
		this.isFisrt = isFisrt;
	}

	/**
	 * 是否显示 1：否，2：是 如果是评价：默认2 如果是评论：默认1 : IS_SHOW
	 * 
	 * @return
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示 1：否，2：是 如果是评价：默认2 如果是评论：默认1 : IS_SHOW
	 * 
	 * @return
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	/**
	 * 是否上户 1：否，2：是 如果是商品类的，置为NULL : IS_SH
	 * 
	 * @return
	 */
	public Integer getIsSh() {
		return isSh;
	}

	/**
	 * 是否上户 1：否，2：是 如果是商品类的，置为NULL : IS_SH
	 * 
	 * @return
	 */
	public void setIsSh(Integer isSh) {
		this.isSh = isSh;
	}

	/**
	 * 订单ID : ORD_ID
	 * 
	 * @return
	 */
	public Long getOrdId() {
		return ordId;
	}

	/**
	 * 订单ID : ORD_ID
	 * 
	 * @return
	 */
	public void setOrdId(Long ordId) {
		this.ordId = ordId;
	}

	/**
	 * 商品类型 : ORD_TYPE
	 * 
	 * @return
	 */
	public Integer getOrdType() {
		return ordType;
	}

	/**
	 * 商品类型 : ORD_TYPE
	 * 
	 * @return
	 */
	public void setOrdType(Integer ordType) {
		this.ordType = ordType;
	}

	/**
	 * 评价内容 : ORD_CONTENT
	 * 
	 * @return
	 */
	public String getOrdContent() {
		return ordContent;
	}

	/**
	 * 评价内容 : ORD_CONTENT
	 * 
	 * @return
	 */
	public void setOrdContent(String ordContent) {
		this.ordContent = ordContent;
	}

	/**
	 * 服务人员或商品ID : RE_ID
	 * 
	 * @return
	 */
	public Long getReId() {
		return reId;
	}

	/**
	 * 服务人员或商品ID : RE_ID
	 * 
	 * @return
	 */
	public void setReId(Long reId) {
		this.reId = reId;
	}

	/**
	 * 物流ID : LOGISTIC_ID
	 * 
	 * @return
	 */
	public Long getLogisticId() {
		return logisticId;
	}

	/**
	 * 物流ID : LOGISTIC_ID
	 * 
	 * @return
	 */
	public void setLogisticId(Long logisticId) {
		this.logisticId = logisticId;
	}

	/**
	 * 物流评价 : LOGISTIC_CONTENT
	 * 
	 * @return
	 */
	public String getLogisticContent() {
		return logisticContent;
	}

	/**
	 * 物流评价 : LOGISTIC_CONTENT
	 * 
	 * @return
	 */
	public void setLogisticContent(String logisticContent) {
		this.logisticContent = logisticContent;
	}

	/**
	 * 评价标签 多个用分号分隔，最多允许3个 : TAGS
	 * 
	 * @return
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * 评价标签 多个用分号分隔，最多允许3个 : TAGS
	 * 
	 * @return
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * 分1,2,3,4,5个等级，其中1是差评，2,3中评，4,5好评 如果是评论：则为NULL : LEVEL
	 * 
	 * @return
	 */

	public Integer getStarLevel() {
		return starLevel;
	}

	/**
	 * 分1,2,3,4,5个等级，其中1是差评，2,3中评，4,5好评 如果是评论：则为NULL : LEVEL
	 * 
	 * @return
	 */
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	/**
	 * 评论者ID : APPRAISER
	 * 
	 * @return
	 */
	public Long getAppraiser() {
		return appraiser;
	}

	/**
	 * 评论者ID : APPRAISER
	 * 
	 * @return
	 */
	public void setAppraiser(Long appraiser) {
		this.appraiser = appraiser;
	}

	/**
	 * 评论者姓名 : APPRAISER_NAME
	 * 
	 * @return
	 */
	public String getAppraiserName() {
		return appraiserName;
	}

	/**
	 * 评论者姓名 : APPRAISER_NAME
	 * 
	 * @return
	 */
	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}

	/**
	 * 用户头像 : APPRAISER_PIC
	 * 
	 * @return
	 */
	public String getAppraiserPic() {
		return appraiserPic;
	}

	/**
	 * 用户头像 : APPRAISER_PIC
	 * 
	 * @return
	 */
	public void setAppraiserPic(String appraiserPic) {
		this.appraiserPic = appraiserPic;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 晒图1 个逗号分隔 : IMG
	 * 
	 * @return
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 晒图1 个逗号分隔 : IMG
	 * 
	 * @return
	 */
	public void setImg(String img) {
		this.img = img;
	}

}