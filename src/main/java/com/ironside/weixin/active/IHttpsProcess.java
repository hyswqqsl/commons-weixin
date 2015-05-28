package com.ironside.weixin.active;

/**
 * https方式访问接口
 * @author 雪庭
 * @sine 1.0 at 2015年5月28日
 */
public interface IHttpsProcess {

	/**
	 * https方式访问，get方法
	 * @param httpsUrl https地址
	 * @return 微信服务器返回的json串
	 */
	String processGet(String httpsUrl);
	
	/**
	 * https方式访问，get方法
	 * @param httpsUrl https地址
	 * @param json post方式发送的json串
	 * @return 微信服务器返回的json串
	 */
	String processPost(String httpsUrl, String json);
}
