package com.ironside.weixin.active;

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
	


}
