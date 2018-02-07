package com.emotte.eserver.core.db.local.mapper;

import java.util.List;
import java.util.Map;

import com.emotte.eserver.core.db.annotation.Mapper;
import com.emotte.eserver.core.db.annotation.Params;
import com.emotte.eserver.core.db.annotation.SQL;
import com.emotte.eserver.core.db.annotation.SQL.SQLTYPE;
import com.emotte.eserver.core.db.annotation.SelectKey;
import com.emotte.eserver.core.db.local.model.Appraise;
import com.emotte.eserver.core.db.local.model.AppraiseReply;

@Mapper
public interface CommentMapper {
	public static final String TABLE = "T_EMP_APPRAISE";
	public static final String TABLE_REPLY = "T_EMP_APPRAISE_REPLY";

	@SQL(sql = "SELECT COUNT (1) as totalRecord FROM "
			+ TABLE
			+ " k WHERE 1=1 @IF(#{isFisrt} != null)[ and	IS_FISRT =  #{isFisrt} ]"
			+ "@IF(#{isShow}!=null)[ and IS_SHOW =  #{isShow}]"
			+ "@IF(#{isSh}!=null)[ and	IS_SH =  #{isSh}] "
			+ "@IF(#{ordId}!=null)[ and	ORD_ID =  #{ordId}]"
			+ "@IF(#{ordType}!=null)[ and	ORD_TYPE =  #{ordType}]"
			+ "@IF(#{ordContent}!=null)[ and	ORD_CONTENT =  #{ordContent}]"
			+ "@IF(#{reId}!=null)[ and	RE_ID =  #{reId}]"
			+ "@IF(#{logisticId}!=null)[ and	LOGISTIC_ID =  #{logisticId}]"
			+ "@IF(#{logisticContent}!=null)[ and	LOGISTIC_CONTENT =  #{logisticContent}]"
			+ "@IF(#{tags}!=null)[ and	TAGS =  #{tags}]"
			+ "@IF(#{starLevel}!=null)[ and	STAR_LEVEL =  #{starLevel}]"
			+ "@IF(#{appraiser}!=null)[ and	APPRAISER =  #{appraiser}]"
			+ "@IF(#{appraiserName}!=null)[ and	APPRAISER_NAME =  #{appraiserName}]"
			+ "@IF(#{appraiserPic}!=null)[ and	APPRAISER_PIC =  #{appraiserPic}]"
			+ "@IF(#{createTime}!=null)[ and CREATE_TIME = #{createTime} ]"
			+ "@IF(#{img}!=null)[ and	IMG =  #{img}]", type = SQLTYPE.SELECT)
	public Map<String, Object> getCount(Appraise para);

	@SQL(sql = "SELECT * FROM (SELECT t.*, ROWNUM rn FROM ("
			+ "SELECT ID as id ,"
			+ "	IS_FISRT as isFisrt ,"
			+ "	IS_SHOW as isShow ,"
			+ "	IS_SH as isSh ,	"
			+ "	ORD_ID as ordId ,"
			+ "	ORD_TYPE as ordType ,"
			+ "	ORD_CONTENT as ordContent ,	"
			+ "	RE_ID as reId ,	"
			+ " STAR_LEVEL as starLevel ,"
			+ "	LOGISTIC_ID as logisticId ,"
			+ "	LOGISTIC_CONTENT as logisticContent ,"
			+ "	TAGS as tags ,"
			+ "	APPRAISER as appraiser ,"
			+ "	APPRAISER_NAME as appraiserName ,"
			+ "	APPRAISER_PIC as appraiserPic ,"
			+ "	to_char(CREATE_TIME,'yyyy-MM-dd hh24:mi:ss') as createTime ,"
			+ "	IMG as img"
			+ " FROM "
			+ TABLE
			+ " k WHERE 1=1 @IF(#{isFisrt} != null)[ and	IS_FISRT =  #{isFisrt} ]"
			+ "@IF(#{isShow}!=null)[ and IS_SHOW =  #{isShow}]"
			+ "@IF(#{isSh}!=null)[ and	IS_SH =  #{isSh}] "
			+ "@IF(#{ordId}!=null)[ and	ORD_ID =  #{ordId}]"
			+ "@IF(#{ordType}!=null)[ and	ORD_TYPE =  #{ordType}]"
			+ "@IF(#{ordContent}!=null)[ and	ORD_CONTENT =  #{ordContent}]"
			+ "@IF(#{reId}!=null)[ and	RE_ID =  #{reId}]"
			+ "@IF(#{logisticId}!=null)[ and	LOGISTIC_ID =  #{logisticId}]"
			+ "@IF(#{logisticContent}!=null)[ and	LOGISTIC_CONTENT =  #{logisticContent}]"
			+ "@IF(#{tags}!=null)[ and	TAGS =  #{tags}]"
			+ "@IF(#{starLevel}!=null)[ and	STAR_LEVEL =  #{starLevel}]"
			+ "@IF(#{appraiser}!=null)[ and	APPRAISER =  #{appraiser}]"
			+ "@IF(#{appraiserName}!=null)[ and	APPRAISER_NAME =  #{appraiserName}]"
			+ "@IF(#{appraiserPic}!=null)[ and	APPRAISER_PIC =  #{appraiserPic}]"
			+ "@IF(#{createTime}!=null)[ and CREATE_TIME =#{createTime}]"
			+ "@IF(#{img}!=null)[ and	IMG =  #{img}]  order by CREATE_TIME desc"
			+ ") t WHERE ROWNUM <= #{end}) a WHERE a.rn > #{start}", type = SQLTYPE.SELECT, returnParam = Appraise.class)
	public List<Appraise> queryAppraiseListPage(Appraise para,
			@Params("start") Integer start, @Params("end") Integer end);

	@SQL(sql = "SELECT COUNT (1) as totalRecord FROM "
			+ TABLE
			+ " k WHERE 1=1 @IF(#{isFisrt} != null)[ and	IS_FISRT =  #{isFisrt} ]"
			+ "@IF(#{isShow}!=null)[ and IS_SHOW =  #{isShow}]"
			+ "@IF(#{isSh}!=null)[ and	IS_SH =  #{isSh}] "
			+ "@IF(#{ordId}!=null)[ and	ORD_ID =  #{ordId}]"
			+ "@IF(#{ordType}!=null)[ and	ORD_TYPE =  #{ordType}]"
			+ "@IF(#{ordContent}!=null)[ and	ORD_CONTENT =  #{ordContent}]"
			+ "@IF(#{reId}!=null)[ and	RE_ID =  #{reId}]"
			+ "@IF(#{logisticId}!=null)[ and	LOGISTIC_ID =  #{logisticId}]"
			+ "@IF(#{logisticContent}!=null)[ and	LOGISTIC_CONTENT =  #{logisticContent}]"
			+ "@IF(#{tags}!=null)[ and	TAGS =  #{tags}]"
			+ "@IF(#{starLevel}==1)[ and	STAR_LEVEL =  1]"
			+ "@IF(#{starLevel}==2)[ and	STAR_LEVEL in (2,3)]"
			+ "@IF(#{starLevel}==3)[ and	STAR_LEVEL in (4,5)]"
			+ "@IF(#{appraiser}!=null)[ and	APPRAISER =  #{appraiser}]"
			+ "@IF(#{appraiserName}!=null)[ and	APPRAISER_NAME =  #{appraiserName}]"
			+ "@IF(#{appraiserPic}!=null)[ and	APPRAISER_PIC =  #{appraiserPic}]"
			+ "@IF(#{createTime}!=null)[ and CREATE_TIME = #{createTime} ]"
			+ "@IF(#{img}!=null)[ and	IMG =  #{img}]", type = SQLTYPE.SELECT)
	public Map<String, Object> getCountForStar(Appraise para);

	@SQL(sql = "SELECT * FROM (SELECT t.*, ROWNUM rn FROM ("
			+ "SELECT ID as id ,"
			+ "	IS_FISRT as isFisrt ,"
			+ "	IS_SHOW as isShow ,"
			+ "	IS_SH as isSh ,	"
			+ "	ORD_ID as ordId ,"
			+ "	ORD_TYPE as ordType ,"
			+ "	ORD_CONTENT as ordContent ,	"
			+ "	RE_ID as reId ,	"
			+ " STAR_LEVEL as starLevel ,"
			+ "	LOGISTIC_ID as logisticId ,"
			+ "	LOGISTIC_CONTENT as logisticContent ,"
			+ "	TAGS as tags ,"
			+ "	APPRAISER as appraiser ,"
			+ "	APPRAISER_NAME as appraiserName ,"
			+ "	APPRAISER_PIC as appraiserPic ,"
			+ "	to_char(CREATE_TIME,'yyyy-MM-dd hh24:mi:ss') as createTime ,"
			+ "	IMG as img"
			+ " FROM "
			+ TABLE
			+ " k WHERE 1=1 @IF(#{isFisrt} != null)[ and	IS_FISRT =  #{isFisrt} ]"
			+ "@IF(#{isShow}!=null)[ and IS_SHOW =  #{isShow}]"
			+ "@IF(#{isSh}!=null)[ and	IS_SH =  #{isSh}] "
			+ "@IF(#{ordId}!=null)[ and	ORD_ID =  #{ordId}]"
			+ "@IF(#{ordType}!=null)[ and	ORD_TYPE =  #{ordType}]"
			+ "@IF(#{ordContent}!=null)[ and	ORD_CONTENT =  #{ordContent}]"
			+ "@IF(#{reId}!=null)[ and	RE_ID =  #{reId}]"
			+ "@IF(#{logisticId}!=null)[ and	LOGISTIC_ID =  #{logisticId}]"
			+ "@IF(#{logisticContent}!=null)[ and	LOGISTIC_CONTENT =  #{logisticContent}]"
			+ "@IF(#{tags}!=null)[ and	TAGS =  #{tags}]"
			+ "@IF(#{starLevel}==1)[ and	STAR_LEVEL =  1]"
			+ "@IF(#{starLevel}==2)[ and	STAR_LEVEL in (2,3)]"
			+ "@IF(#{starLevel}==3)[ and	STAR_LEVEL in (4,5)]"
			+ "@IF(#{appraiser}!=null)[ and	APPRAISER =  #{appraiser}]"
			+ "@IF(#{appraiserName}!=null)[ and	APPRAISER_NAME =  #{appraiserName}]"
			+ "@IF(#{appraiserPic}!=null)[ and	APPRAISER_PIC =  #{appraiserPic}]"
			+ "@IF(#{createTime}!=null)[ and CREATE_TIME =#{createTime}]"
			+ "@IF(#{img}!=null)[ and	IMG =  #{img}]  order by CREATE_TIME desc"
			+ ") t WHERE ROWNUM <= #{end}) a WHERE a.rn > #{start}", type = SQLTYPE.SELECT, returnParam = Appraise.class)
	public List<Appraise> queryAppraiseForStarListPage(Appraise para,
			@Params("start") Integer start, @Params("end") Integer end);

	@SQL(sql = "SELECT ID as id ," + "APPR_ID as apprId ,	"
			+ "REPLY_USER_ID as replyUserId ,"
			+ "REPLY_USER_NAME as replyUserName ,"
			+ "REPLY_USER_PIC as replyUserPic ,"
			+ "to_char(CREATE_TIME,'yyyy-MM-dd hh24:mi:ss') as createTime ,"
			+ "RE_CONTENT as reContent FROM " + TABLE_REPLY
			+ " k WHERE APPR_ID=#{id}", type = SQLTYPE.SELECT, returnParam = AppraiseReply.class)
	public List<AppraiseReply> queryAppraiseReplyByAppraiseId(Long id);

	@SQL(sql = "insert into "
			+ TABLE
			+ " ( ID , IS_FISRT , IS_SHOW , IS_SH , ORD_ID , ORD_TYPE ,"
			+ "ORD_CONTENT , RE_ID , LOGISTIC_ID , LOGISTIC_CONTENT , TAGS ,"
			+ "STAR_LEVEL , APPRAISER , APPRAISER_NAME , APPRAISER_PIC , CREATE_TIME , IMG)"
			+ "values ( #{id} , #{isFisrt} , #{isShow} , #{isSh} , #{ordId} ,"
			+ "#{ordType} , #{ordContent} , #{reId} , #{logisticId} , #{logisticContent} ,"
			+ "#{tags} ,#{starLevel} , #{appraiser} , #{appraiserName} , #{appraiserPic} ,"
			+ "sysdate , #{img} )", type = SQLTYPE.INSERT, selectKey = @SelectKey(sql = "SELECT getseq() FROM dual", prop = "id", returnType = Long.class))
	public int insertAppraise(Appraise para);

	@SQL(sql = "insert into " + TABLE_REPLY
			+ " ( ID , APPR_ID , REPLY_USER_ID , REPLY_USER_NAME ,"
			+ " REPLY_USER_PIC , CREATE_TIME ,RE_CONTENT)"
			+ "values ( #{id} , #{apprId} , #{replyUserId} , "
			+ "#{replyUserName} , #{replyUserPic} ,sysdate , #{reContent})", type = SQLTYPE.INSERT, selectKey = @SelectKey(sql = "SELECT getseq() FROM dual", prop = "id", returnType = Long.class))
	public int insertAppraiseReply(AppraiseReply para);
	
	@SQL(sql = "SELECT * FROM (SELECT t.*, ROWNUM rn FROM ("
			+ "SELECT k.ID as id ,"
			+ "	k.IS_FISRT as isFisrt ,"
			+ "	k.IS_SHOW as isShow ,"
			+ "	k.IS_SH as isSh ,"
			+ "	k.ORD_ID as ordId ,"
			+ "	k.ORD_TYPE as ordType ,"
			+ "	k.ORD_CONTENT as ordContent ,	"
			+ "	k.RE_ID as reId ,	"
			+ "	(select bd.dict_name from t_base_dictionary bd where bd.id = k.RE_ID) as courseName ,	"
			+ " k.STAR_LEVEL as starLevel ,"
			+ "	k.LOGISTIC_ID as logisticId ,"
			+ "	k.LOGISTIC_CONTENT as logisticContent ,"
			+ "	k.TAGS as tags ,"
			+ "	k.APPRAISER as appraiser ,"
			+ "	k.APPRAISER_NAME as appraiserName ,"
			+ "	k.APPRAISER_PIC as appraiserPic ,"
			+ "	to_char(k.CREATE_TIME,'yyyy-MM-dd hh24:mi:ss') as createTime ,"
			+ " p.name as \"reName\""
			+ " FROM "
			+ " T_EMP_APPRAISE "
			+ " k left join emerp.t_emp_personnel p on p.id=k.re_id "
			+ " WHERE 1=1 @IF(#{isFisrt} != null)[ and k.IS_FISRT =  #{isFisrt} ]"
			+ "@IF(#{isShow}!=null)[ and k.IS_SHOW =  #{isShow}]"
			+ "@IF(#{isSh}!=null)[ and	k.IS_SH =  #{isSh}] "
			+ "@IF(#{ordId}!=null)[ and	k.ORD_ID =  #{ordId}]"
			+ "@IF(#{ordType}!=null)[ and	k.ORD_TYPE =  #{ordType}]"
			+ "@IF(#{ordContent}!=null)[ and	k.ORD_CONTENT =  #{ordContent}]"
			+ "@IF(#{reId}!=null)[ and	k.RE_ID =  #{reId}]"
			+ "@IF(#{logisticId}!=null)[ and	k.LOGISTIC_ID =  #{logisticId}]"
			+ "@IF(#{logisticContent}!=null)[ and	k.LOGISTIC_CONTENT =  #{logisticContent}]"
			+ "@IF(#{tags}!=null)[ and	k.TAGS like  \"#{tags}%\"]"
			+ "@IF(#{appraiser}!=null)[ and	k.APPRAISER =  #{appraiser}]"
			+ "@IF(#{appraiserName}!=null)[ and	k.APPRAISER_NAME =  #{appraiserName}]"
			+ "@IF(#{appraiserPic}!=null)[ and	k.APPRAISER_PIC =  #{appraiserPic}]"
			+ "@IF(#{createTime}!=null)[ and k.CREATE_TIME =#{createTime}]"
			+ "@IF(#{img}!=null)[ and	k.IMG =  #{img}]  order by CREATE_TIME desc"
			+ ") t WHERE ROWNUM <= #{end}) a WHERE a.rn > #{start}", type = SQLTYPE.SELECT, returnParam = Appraise.class)
	public int test(Map<String, Object> para);
}
