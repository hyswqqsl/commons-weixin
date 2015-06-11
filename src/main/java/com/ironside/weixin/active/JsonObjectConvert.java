package com.ironside.weixin.active;

import java.io.Writer;

import net.sf.json.JSONObject;

import com.ironside.weixin.WeixinException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

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
	 * 将微信服务器返回的json串(没有类名)转换为请求返回对象，不使用标签
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @return 转换后的对象
	 */
	public <T> T jsonToObject(String json, Class<T> cls) {
		return jsonToObject(json, cls, true);
	}

	/**
	 * 将微信服务器返回的json串(没有类名)转换为请求返回对象
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @param annotation 是否使用标签
	 * @return 转换后的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObject(String json, Class<T> cls, boolean annotation) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.alias("object", cls);
		xStream.autodetectAnnotations(annotation);
		return (T) xStream.fromXML(json);		
	}
	
	/**
	 * 将微信服务器返回的json串(有类名)转换为请求返回对象,不使用标签 
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @param className 类名
	 * @return 转换后的对象
	 */
	public <T> T jsonToObject(String json, Class<T> cls, String className) {
		return jsonToObject(json, cls, className, true);
	}
	
	/**
	 * 将微信服务器返回的json串(有类名)转换为请求返回对象
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @param className 类名
	 * @param annotation 是否使用标签 
	 * @return 转换后的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObject(String json, Class<T> cls, String className, boolean annotation) {
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.alias(className, cls);
		xStream.autodetectAnnotations(annotation);
		return (T) xStream.fromXML(json);
	}
	
	/**
	 * 对象转换为json串，在json串中显示类名，不使用标签	
	 * @param classCls 对象类型
	 * @param object 对象
	 * @return 转换后的json串
	 */
	public <T> String ObjectToJson(Class<T> classCls, T object) {
		return ObjectToJson(classCls, object, true);
	}
	
	/**
	 * 对象转换为json串，在json串中显示类名
	 * @param classCls 对象类型
	 * @param object 对象
	 * @return 转换后的json串
	 */
	public <T> String ObjectToJson(Class<T> classCls, T object, boolean annotation) {
		XStream xStream;
		xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.autodetectAnnotations(annotation);
		String json = (String)xStream.toXML(object);
		return json;
	}			
	
	/**
	 * 对象转换为json串，在json串中不显示类名，不使用标签	
	 * @param classCls 对象类型
	 * @param object 对象
	 * @return 转换后的json串
	 */
	public <T> String ObjectToJsonNoClassName(Class<T> classCls, T object) {
		return ObjectToJsonNoClassName(classCls, object, true);
	}

	/**
	 * 对象转换为json串，在json串中不显示类名
	 * @param classCls 对象类型
	 * @param object 对象
	 * @return 转换后的json串
	 */	
	public <T> String ObjectToJsonNoClassName(Class<T> classCls, T object, boolean annotation) {
		XStream xStream;
		xStream = new XStream(new JettisonMappedXmlDriver() {
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.autodetectAnnotations(annotation);
		String json = (String)xStream.toXML(object);
		return json;
	}	
	
	/**
	 * 验证json错误信息，如果json是正常信息，或返回码是0，验证通过，否则抛出WeixinException异常
	 * @param json 待验证json
	 * @throws WeixinException 验证不通过，抛出异常
	 */
	public void validateJsonException(String json) {
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
