package com.ironside.weixin.active;

import com.ironside.weixin.active.entity.AccessToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * 与认证相关的处理
 * @author 雪庭
 * @sine at 2015年5月29日
 */
public class AuthorizeRequest implements IAuthorize {
	
	private AccessToken accessToken;
	private String jsonTemplate = "{object:json}";
	private String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public AuthorizeRequest() {
	}

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
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		json = jsonTemplate.replaceAll("json", json);
		xStream.alias("object", AccessToken.class);
		AccessToken accessToken = (AccessToken)xStream.fromXML(json);
		accessToken.setAccess_time(System.currentTimeMillis()/1000);
		return accessToken;
	}

}
