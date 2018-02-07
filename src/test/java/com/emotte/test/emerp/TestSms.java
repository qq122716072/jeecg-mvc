package com.emotte.test.emerp;

import com.emotte.BaseTestService;
import com.emotte.eserver.core.context.Context;
import com.emotte.kernel.sms.SMSRedisHelper;

import net.sf.json.JSONObject;

public class TestSms extends BaseTestService {
	@org.junit.Test
	public  void Test(){
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject msg = new JSONObject();
		String[] params = new String[]{"88888","2017-08-09","2017-12-08"};
		msg.accumulate("mobiles", "15735343372");
		msg.accumulate("templet", "crm_1");
		msg.accumulate("channel", "yzm");
		msg.accumulate("rate", "1");
		msg.accumulate("system", "salaryWarn");
		msg.accumulate("params",params);
		SMSRedisHelper smsHe = (SMSRedisHelper) Context.getBean("smsHelper");
		String s = smsHe.send(msg.toString());
		System.out.println(s);
	}
	
	@org.junit.Test
	public  void Test1(){
		JSONObject data1 = new JSONObject();
		JSONObject source = new JSONObject();
		JSONObject destination = new JSONObject();
		String sourceIpPort = "192.168.001.010:6379";
		String destinationIpPort = "192.168.001.011:6380";
		String sourceSendInfo = "10000";//发送者id即登录系统用户
		String destinationRecInfo = "122716072@qq.com";//接受者信息(邮箱地址|手机号)
		source.accumulate("sourceIpPort", sourceIpPort);
		source.accumulate("sourceSendInfo", sourceSendInfo);
		destination.accumulate("destinationIpPort",destinationIpPort);
		destination.accumulate("destinationRecInfo",destinationRecInfo);
		
		String validate = "1110000";
		String sendMode = "1";
		String sendStatus = "0";//发送状态(0:未发送|1:已发送)
		String isReader = "0";//是否已读
		
		String title = "邮件send2java";//发送主题
		String content = "消息体内容";//内容
		String linker = "http://www.95081.com";//链接
		String type = "1";//业务类型
		String createTime = "2018-01-22 09:47:125";//创建时间
		
		data1.accumulate("title", title);
		data1.accumulate("content", content);
		data1.accumulate("type", type);
		data1.accumulate("linker", linker);
		data1.accumulate("createTime", createTime);
		
		String data = data1.toString();
		String md5 = "md5";
		JSONObject msg = new JSONObject();
		msg.accumulate("validate", validate);//校验 8位每一位校验对应的数据是否为空
		msg.accumulate("sourceAddress", source);//源ip+端口
		msg.accumulate("destinationAddress", destination);//目的ip+端口
		msg.accumulate("sendMode", sendMode);//发送模式 邮件还是短信
		msg.accumulate("sendStatus", sendStatus);//发送模式 邮件还是短信
		msg.accumulate("data", data);//发送数据
		msg.accumulate("md5", md5);//MD5 暂时不用
		msg.accumulate("isReader", isReader);
		System.out.println(msg.toString());
	}
}
