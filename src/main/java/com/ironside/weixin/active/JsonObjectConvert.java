package com.ironside.weixin.active;

import java.util.List;

import net.sf.json.JSONObject;

import com.ironside.weixin.WeixinEnum;
import com.ironside.weixin.WeixinException;
import com.qq.weixin.mp.aes.AesException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * json串与对象转换类
 * @author 雪庭
 * @sine 1.0 at 2015年6月2日
 */
public class JsonObjectConvert {
	
	/** 用object标识包装json，方便json转换为对象 */
	private String jsonTemplate = "{object:json}";
	
	/** 单例 */
	private static JsonObjectConvert instance;
	
	/**
	 * 隐藏默认构造函数
	 */
	private JsonObjectConvert() {
	
	}
	
	/**
	 * 单例模式
	 * @return JsonObjectConvert单例
	 */
	public static JsonObjectConvert getInstance() {
		if(instance==null) {
			instance = new JsonObjectConvert();
		}
		return instance;
	}
	
	/**
	 * 将微信服务器返回的json串转换为请求返回对象
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @return 请求返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToResponse(String json, Class<T> cls) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", cls);
		return (T) xStream.fromXML(json);
	}
	
	/**
	 * 将微信服务器返回的json串转换为属性是List的请求返回对象
	 * @param json 微信服务器返回的json串
	 * @param classCls 请求返回类型
	 * @param nameOfList 对应List的属性名 
	 * @param attributeCls 子类的类型
	 * @return 请求返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToResponse(String json, Class<T> classCls, String nameOfList) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", classCls);
		// 设置attribute对应类中List类型
		xStream.alias(nameOfList, List.class);
		xStream.addImplicitCollection(classCls, nameOfList);
		xStream.alias(nameOfList, String.class);		
		return (T) xStream.fromXML(json);
	}	
	
	/**
	 * 将微信服务器返回的json串转换为子对象属性是List的请求返回对象
	 * @param json json 微信服务器返回的json串
	 * @param classCls classCls 请求返回类型
	 * @param childClassName 子对象类名
	 * @param childClassCls 子对象类型
	 * @param nameOfList 对应List的属性名 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToResponse(String json, Class<T> classCls, String childClassName, Class<?> childClassCls, String nameOfList) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", classCls);
		xStream.alias(childClassName, childClassCls);
		// 设置attribute对应类中List类型
		xStream.alias(nameOfList, List.class);
		xStream.addImplicitCollection(childClassCls, nameOfList);
		xStream.alias(nameOfList, String.class);		
		return (T) xStream.fromXML(json);		
	}
	
	/**
	 * 验证json错误信息，如果json是正常信息，或返回码是0，验证通过，否则抛出WeixinException异常
	 * @param json 待验证json
	 * @throws WeixinException 验证不通过，抛出异常
	 */
	public void validateResponse(String json) throws WeixinException {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object errcode = jsonObject.get("errcode");
		if (errcode==null) {
			return;
		}
		if (jsonObject.get("errcode").equals(Integer.valueOf(0))) {
			return;
		}		
		int code = (Integer) jsonObject.get("errcode");
		String message = (String) jsonObject.get("errmsg");
		throw new WeixinException(code, message);
	}
	
}
