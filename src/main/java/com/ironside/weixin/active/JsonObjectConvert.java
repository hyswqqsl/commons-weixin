package com.ironside.weixin.active;

import java.io.Writer;
import java.util.List;

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
	 * 将微信服务器返回的json串转换为请求返回对象,
	 * 这种转换方式的json没有类名，需要先附加类名(object)
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @return 请求返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObject(String json, Class<T> cls) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.alias("object", cls);
		return (T) xStream.fromXML(json);
	}
	
	/**
	 * 将微信服务器返回的json串转换为请求返回对象,
	 * 这种转换方式的json有类名
	 * @param json 微信服务器返回的json串
	 * @param cls 请求返回类型
	 * @param className 类名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObject(String json, Class<T> cls, String className) {
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.alias(className, cls);
		return (T) xStream.fromXML(json);
	}
	
	/**
	 * 将微信服务器返回的json串转换为属性是List的请求返回对象
	 * @param json 微信服务器返回的json串
	 * @param classCls 请求返回类型
	 * @param nameOfList 对应List的属性名 
	 * @param nameCls 对应List的属性类型
	 * @return 请求返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObjectList(String json, Class<T> classCls, String nameOfList, Class<?> nameCls) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", classCls);
		// 设置attribute对应类中List类型
		xStream.alias(nameOfList, List.class);
		xStream.addImplicitCollection(classCls, nameOfList);
		xStream.alias(nameOfList, nameCls);
		return (T) xStream.fromXML(json);
	}	
	
	/**
	 * 将微信服务器返回的json串转换为子对象属性是List的请求返回对象
	 * @param json json 微信服务器返回的json串
	 * @param classCls 请求返回类型
	 * @param childClassName 子对象类名
	 * @param childClassCls 子对象类型
	 * @param nameOfList 对应List的属性名
	 * @param nameCls 对应List的属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T jsonToObjectSubList(String json, Class<T> classCls, String childClassName, Class<?> childClassCls, String nameOfList, Class<?> nameClas) {
		json = jsonTemplate.replaceAll("json", json);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.autodetectAnnotations(true);
		xStream.alias("object", classCls);
		xStream.alias(childClassName, childClassCls);
		// 设置attribute对应类中List类型
		xStream.alias(nameOfList, List.class);
		xStream.addImplicitCollection(childClassCls, nameOfList);
		xStream.alias(nameOfList, nameClas);		
		return (T) xStream.fromXML(json);		
	}
	
	/**
	 * 对象转换为json，根据实际情况，设置是否显示类名(displayClassName)，是否启用标签(useAnnotation)
	 * @param classCls 请求返回类型
	 * @param object 需要转换的对象
	 * @param displayClassName 是否显示类名
	 * @return 转换后的json串
	 */
	public <T> String ObjectToJson(Class<T> classCls, T object, boolean displayClassName) {
		XStream xStream;
		if (displayClassName==true) {
			// 如果需要在json串中显示类名
			xStream = new XStream(new JettisonMappedXmlDriver());
		} else {
			// 如果不需要在json串中显示类名
			xStream = new XStream(new JettisonMappedXmlDriver() {
				public HierarchicalStreamWriter createWriter(Writer writer) {
					return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
				}
			});
		}
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.autodetectAnnotations(true);
		String json = (String)xStream.toXML(object);
		return json;
	}
	
	/**
	 * 验证json错误信息，如果json是正常信息，或返回码是0，验证通过，否则抛出WeixinException异常
	 * @param json 待验证json
	 * @throws WeixinException 验证不通过，抛出异常
	 */
	public void validateJsonException(String json) throws WeixinException {
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
