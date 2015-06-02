package com.ironside.weixin.active;

import java.util.List;

import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * 与认证相关的处理
 * @author 雪庭
 * @sine 1.0 at 2015年5月29日
 */
public class AuthorizeRequest implements IAuthorize {
	
	/** 公众号全局凭证请求url */
	private String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 微信服务器IP地址请求url */
	private String ip_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	
	/** 公众号全局凭证缓存 */
	private AccessToken accessToken;
	
	/** 用object标识包装json，方便json转换为对象 */
	private String jsonTemplate = "{object:json}";
	
	@Override
	public AccessToken getAccessToken(String appid, String secret) {
		// 如果公众号全局凭证不存在，就生成一个
		if (accessToken==null) {
			return doAccessToken(appid, secret);
		}
		// 如果公众号全局凭证过期，重新生成一个
		if (accessToken.isOver()==true) {
			return doAccessToken(appid, secret);
		}
		// 返回当前公众号全局凭证
		return this.accessToken;
	}

	/**
	 * 用object标识包装json，转换为对象
	 * @param xStream 已配置好的转换库
	 * @param json 微信服务器返回的json串
	 * @return 转换的对象
	 */
	private Object jsonToObject(XStream xStream, String json) {
		json = jsonTemplate.replaceAll("json", json);
		return xStream.fromXML(json);
	}

	/**
	 * 生成公众号全局凭证
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局凭证
	 */
	private AccessToken doAccessToken(String appid, String secret) {
		String url = access_token_url.replaceAll("APPID", appid).replaceAll("APPSECRET", secret);
		String json = HttpsRequest.getInstance().processGet(url);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		xStream.alias("object", AccessToken.class);
		AccessToken accessToken = (AccessToken)jsonToObject(xStream, json);
		accessToken.setAccessTime(System.currentTimeMillis()/1000);
		return accessToken;
	}

	@Override
	public IpAddresses getIpAddress(AccessToken accessToken) {
		String url = ip_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String json = HttpsRequest.getInstance().processGet(url);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());  
		// 设置ip_list对应类中List类型
		xStream.alias("ip_list", List.class);
		xStream.addImplicitCollection(IpAddresses.class, "ip_list");
		xStream.alias("ip_list", String.class);
		xStream.alias("object", IpAddresses.class);	
		return (IpAddresses)jsonToObject(xStream, json);
	}
	
	

}
