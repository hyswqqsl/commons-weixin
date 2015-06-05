package com.ironside.weixin.active;

import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;
import com.ironside.weixin.active.entity.UserInfo;
import com.ironside.weixin.active.entity.UserList;

/**
 * 与认证相关的处理
 * @author 雪庭
 * @sine 1.0 at 2015年5月29日
 */
public class ActiveRequest {
	
	/** 公众号全局凭证请求url */
	private final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 微信服务器IP地址请求url */
	private final String ip_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	/** 获取用户信息请求url */
	private final String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/** 获取帐号的关注者列表url */
	private final String user_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";	
	
	/** 公众号全局凭证缓存 */
	private AccessToken accessToken;
	
	/**
	 * access_token是公众号的全局凭证，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效
	 * 
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局票据
	 */
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

	/**
	 * 获得微信服务器的IP地址列表
	 * @param accessToken 公众号的全局凭证
	 * @return ip地址列表
	 */	
	public IpAddresses getIpAddress(AccessToken accessToken) {
		String url = ip_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String json = HttpsRequest.getInstance().processGet(url);
		IpAddresses ipAddresses = JsonObjectConvert.getInstance().jsonToResponse(json, IpAddresses.class, "ip_list"); 
		return ipAddresses;
	}

	/**
	 * 获取用户基本信息(unionId机制)，在关注者与公众号产生消息交互后，
	 * 公众号可获得关注者的openid（加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同）。
	 * 公众号可通过本接口来根据openid获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间
	 * @param accessToken 公众号的全局凭证
	 * @param openid 用户公众号
	 * @return
	 */
	public UserInfo getUserInfo(AccessToken accessToken, String openid) {
		String url = user_info_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken()).replaceAll("OPENID", openid);
		String json = HttpsRequest.getInstance().processGet(url);
		UserInfo userInfo = JsonObjectConvert.getInstance().jsonToResponse(json, UserInfo.class);
		return userInfo;
	}
	
	/**
	 * 获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
	 * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求 
	 * @param accessToken 公众号的全局凭证
	 * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return
	 */
	public UserList getUserList(AccessToken accessToken, String nextOpenid) {
		if (nextOpenid==null) {
			nextOpenid = "";
		}
		String url = user_list_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken()).replaceAll("NEXT_OPENID", nextOpenid);
		String json = HttpsRequest.getInstance().processGet(url);
		UserList userList = JsonObjectConvert.getInstance().jsonToResponse(json, UserList.class, "data", UserList.UserListData.class, "openid");
		return userList;
	}

}
