package com.ironside.weixin.push;

/**
 * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
 * 并且带上四个参数（signature、timestamp、nonce、echostr），
 * 开发者通过对签名（即signature）的效验，来判断此条消息的真实性。 
 * @author 雪庭
 * @since 1.0
 */
public interface IConfirmProcess {

	/**
	 * 开发者通过检验signature对请求进行校验（下面有校验方式）。
	 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，
	 * 则接入生效，成为开发者成功，否则接入失败。</br>
	 * 加密/校验流程如下：
	 * <ul>
	 * <li>将token、timestamp、nonce三个参数进行字典序排序</li>
	 * <li>将三个参数字符串拼接成一个字符串进行sha1加密/li>
	 * <li>开发者获得加密后的字符串可与signature对比，标识该请求来源于微信</li>
	 * </ul>
	 * @param token 公众平台上，开发者设置的token
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳 
	 * @param nonce 随机数
	 * @return
	 */
	String process(String token, String signature, String timestamp, String nonce);
}
