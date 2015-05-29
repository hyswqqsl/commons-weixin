package com.ironside.weixin.passive.request;

/**
 * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
 * 并且带上四个参数(signature、timestamp、nonce、echostr)，
 * 开发者通过对签名(即signature)的效验，来判断此条消息的真实性。 
 * @author 雪庭
 * @sine 1.0 at 2015年5月26日
 */
public interface IGetProcess {

	/**
	 * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
	 * 并且带上四个参数(signature、timestamp、nonce、echostr)，
	 * 开发者通过对签名(即signature)的效验，来判断此条消息的真实性。
	 * 开发者通过检验signature对请求进行校验，若确认此次GET请求来自微信服务器，
	 * 请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
	 * @token 微信公众号配置信息中的token
	 * @param signatur 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数 
	 * @param timestamp 时间戳 
	 * @param nonce 随机数 
	 * @param echostr 随机字符串 
	 * @return  若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败 
	 */
	String process(String token, String signatur, String timestamp, String nonce, String echostr);
	
}
