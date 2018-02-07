package com.emotte.eserver.dispatcher;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.emotte.eserver.constant.CommonConstant;
import com.emotte.eserver.core.context.Context;
import com.emotte.eserver.core.exception.ParamsException;
import com.emotte.eserver.core.exception.SignException;
import com.emotte.eserver.core.helper.JSONObjectHelper;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.eserver.core.local.CurrentUserManager;
import com.emotte.eserver.core.model.UserInfo;
import com.emotte.eserver.core.security.sign.SignFactory;
import com.emotte.eserver.core.security.sign.SignFactory.SIGNTYPE;
import com.emotte.eserver.core.security.sign.TextBuilder2;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.json.JsonUtil;

/**
 * 调度器
 * @author ChengJing
 * 2017年3月22日
 */
public class Dispatcher {
	private static Dispatcher dispatcher;
	private SignFactory<String> signFactory;
	private String publicKey;
	private String privateKey;
	private TextBuilder2<String> textBuilder = new TextBuilder2<String>() {
		@Override
		public String build(String t) {
			return t;
		}
	};
	
	private Dispatcher () {
//		String security = PropertiesHelper.getValue(CommonConstant.CONFIG, "scurity.type");
		String security = "md5";
		signFactory = new SignFactory<String>(SIGNTYPE.valueOf(security.toLowerCase()));
		switch (SIGNTYPE.valueOf(security.toLowerCase())) {
			case rsa: {
				publicKey = PropertiesHelper.getValue(CommonConstant.CONFIG, "rsa.publickey"); 
				privateKey = PropertiesHelper.getValue(CommonConstant.CONFIG, "rsa.privatekey");
				break;
			}
			case md5: {
//				publicKey = PropertiesHelper.getValue(CommonConstant.CONFIG, "md5.salt"); 
//				privateKey = PropertiesHelper.getValue(CommonConstant.CONFIG, "md5.salt");
				publicKey = "emotte";
				privateKey = "emotte";
				break;
			}
		}
		if (StringUtils.isEmpty(publicKey)) throw new BaseException("未找到密钥信息");
	}
	public static Dispatcher getInstance() {
		if (null == dispatcher) {
			dispatcher = new Dispatcher();
		}
		return dispatcher;
	}
	
	public JSONObject invoke (String json) {
		LogHelper.info(getClass(), "接收到的数据：" + json);
		JSONObject result = new JSONObject();
		try {
			JSONObject object = JSONObject.fromObject(json);
//			String rw = JSONObjectHelper.getJsonObjectValue(object, "rw", false);
			String service = JSONObjectHelper.getJsonObjectValue(object, "service", false);
			String method = JSONObjectHelper.getJsonObjectValue(object, "method", false);
			String params = JSONObjectHelper.getJsonObjectValue(object, "params", true);
			String version = JSONObjectHelper.getJsonObjectValue(object, "version", true);
			String userInfo = JSONObjectHelper.getJsonObjectValue(object, "user", true);
			if (null != userInfo) { // 将用户信息设置到本地线程
				UserInfo user = JsonUtil.fromJson(userInfo, UserInfo.class);
				CurrentUserManager.setUser(user); 
			}
			if (version == null) version = "0.9"; // 默认0.9
			String sign = JSONObjectHelper.getJsonObjectValue(object, "sign", false);
			// 验证数据有效性
			/*if (!signFactory.verify(textBuilder, params, publicKey, sign)) {
				throw new SignException("验证失败");
			}*/
			String obj = null;
			if (params == null) {
				obj = Context.invoke(service, version, method); // 加 e-是为了只处理EService标签的服务，可查看注解扫描器对EService的处理
			} else {
				obj = Context.invoke(service, version, method, params); // 加 e-是为了只处理EService标签的服务，可查看注解扫描器对EService的处理
			}
			result.accumulate("code", "1000");
			result.accumulate("data", obj);
			UserInfo user = CurrentUserManager.getUser();
			if (null != user) {
				result.accumulate("userInfo", user);
			}
			result.accumulate("sign", signFactory.sign(textBuilder, obj, privateKey));
		} catch (ParamsException e) {
			result.accumulate("code", "1001"); // 参数错误
			result.accumulate("msg", e.getMessage());
		} catch (SignException e) {
			result.accumulate("code", "1004"); // 签名错误
			result.accumulate("msg", e.getMessage());
		} catch (BaseException e) {
			result.accumulate("code", "1003"); // 系统错误
			result.accumulate("msg", e.getMessage());
		} catch (Exception e) {
			result.accumulate("code", "1002"); // 系统错误
			result.accumulate("msg", "请联系管理员");
			LogHelper.error(getClass(), e.getMessage(), e);
		}
		return result;
	}

}
