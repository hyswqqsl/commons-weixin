package com.ironside.weixin.active;

import com.ironside.weixin.WeixinException;
import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;

/**
 * 与认证相关的处理
 * @author 雪庭
 * @sine 1.0 at 2015年5月29日
 */
public class ActiveRequest {
	
	/** 单例 */
	private static ActiveRequest instance;
	
	/**
	 * 隐藏默认构造函数
	 */
	private ActiveRequest() {
	}
	
	/**
	 * 单例模式
	 * @return ActiveRequest单例
	 */
	public static ActiveRequest getInstance() {
		if (instance==null) {
			instance = new ActiveRequest();
		}
		return instance;
	}
	
	/** 公众号全局凭证请求url */
	private final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 微信服务器IP地址请求url */
	private final String ip_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

	
	/** 公众号全局凭证缓存 */
	private AccessToken accessToken;
	
	/**
	 * access_token是公众号的全局凭证，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效
	 * 
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局凭证
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	public AccessToken getAccessToken(String appid, String secret) throws WeixinException {
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
	 * 生成公众号全局凭证
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局凭证
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	private AccessToken doAccessToken(String appid, String secret) throws WeixinException {
		String url = access_token_url.replaceAll("APPID", appid).replaceAll("APPSECRET", secret);
		String json = HttpsRequest.getInstance().processGet(url);
		JsonObjectConvert.getInstance().validateResponse(json);		
		AccessToken accessToken = JsonObjectConvert.getInstance().jsonToResponse(json, AccessToken.class);
		accessToken.setAccessTime(System.currentTimeMillis()/1000);
		return accessToken;
	}

	/**
	 * 获得微信服务器的IP地址列表
	 * @param accessToken 公众号的全局凭证
	 * @return ip地址列表
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */	
	public IpAddresses getIpAddress(AccessToken accessToken) throws WeixinException {
		String url = ip_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String json = HttpsRequest.getInstance().processGet(url);
		JsonObjectConvert.getInstance().validateResponse(json);		
		IpAddresses ipAddresses = JsonObjectConvert.getInstance().jsonToResponse(json, IpAddresses.class, "ip_list"); 
		return ipAddresses;
	}

}
