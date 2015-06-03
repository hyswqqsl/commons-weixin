package com.ironside.weixin.active;

import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;

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
	 * 生成公众号全局凭证
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局凭证
	 */
	private AccessToken doAccessToken(String appid, String secret) {
		String url = access_token_url.replaceAll("APPID", appid).replaceAll("APPSECRET", secret);
		String json = HttpsRequest.getInstance().processGet(url);
		AccessToken accessToken = JsonObjectConvert.getInstance().jsonToResponse(json, AccessToken.class);
		accessToken.setAccessTime(System.currentTimeMillis()/1000);
		return accessToken;
	}

	@Override
	public IpAddresses getIpAddress(AccessToken accessToken) {
		String url = ip_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String json = HttpsRequest.getInstance().processGet(url);
		IpAddresses ipAddresses = JsonObjectConvert.getInstance().jsonToResponse(json, IpAddresses.class, "ip_list", String.class); 
		return ipAddresses;
	}
	
	

}
