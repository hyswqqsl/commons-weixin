package com.ironside.weixin.active;

import java.util.List;

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
	 * @param attribute 对应List的属性名 
	 * @param attributeCls 子类的类型
	 * @return 请求返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToResponse(String json, Class<T> classCls, String attribute, Class<?> attributeCls) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", classCls);
		// 设置attribute对应类中List类型
		xStream.alias(attribute, List.class);
		xStream.addImplicitCollection(classCls, attribute);
		xStream.alias(attribute, attributeCls);		
		return (T) xStream.fromXML(json);
	}	
	
}
