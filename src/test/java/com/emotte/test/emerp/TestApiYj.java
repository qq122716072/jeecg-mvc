package com.emotte.test.emerp;

import java.lang.reflect.Method;

import com.emotte.eserver.core.annotation.Api;
import com.emotte.eserver.core.annotation.Api.REQUESTYPE;
import com.emotte.eserver.core.annotation.EService;

@EService
public class TestApiYj {
	@Api(ip="1.1.1.1",isEnabled=true,isTest=true,module="testYJ",paramNeeds="name;sex",paramsExamp="{name:张三,sex:女,class:1,ser:A}",requestType=REQUESTYPE.GET,terminal="APP",returnSample="{code:0,msg:成功}",returnExplain="code为0代表成功返回", empolder = "")
	public void testApi(){
		System.out.println("TestApiDocument");
	}
	
	@Api(ip="2.2.2.2",isEnabled=true,isTest=true,module="testYJ",paramNeeds="name;sex",paramsExamp="{name:张三,sex:女,class:1,ser:A}",requestType=REQUESTYPE.POST,terminal="APP",returnSample="{code:-1,msg:失败}",returnExplain="code为-1代表失败返回", empolder = "")
	public void testApiA(){
		System.out.println("TestApiDocument");
	}
	@Api(module="testYJ",paramNeeds="name;sex",paramsExamp="{name:张三,sex:女,class:1,ser:A}",returnSample="{code:-1,msg:失败}",returnExplain="code为-1代表失败返回", empolder = "")
	public void testApiB(){
		System.out.println("TestApiDocument");
	}
	public static void main(String[] args) {
		Method[] me = TestApiYj.class.getMethods();
		
		for (Method method : me) {
			System.out.println(method.getName());
		}
		System.out.println(TestApiYj.class.getName());
	}
}
