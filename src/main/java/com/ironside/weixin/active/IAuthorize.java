package com.ironside.weixin.active;

import com.ironside.weixin.active.entity.AccessToken;

/**
 * 与认证相关的处理接口
 * @author 雪庭
 * @sine 1.0 at 2015年5月29日
 */
public interface IAuthorize {

	/**
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效
	 * 
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 公众号全局票据
	 */
	AccessToken getAccessToken(String appid, String secret);
	
}
