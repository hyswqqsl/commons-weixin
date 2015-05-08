package com.ironside.weixin.request;

import org.springframework.util.Assert;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * POST方式推送给微信公众账号的加密消息处理，具体实现消息加密、解密。
 * @author 雪庭
 * @sine 1.0 at 2015年4月3日
 */
public class DefaultEncyptPostProcess extends AbstractEncyptPostProcess {
	
	/** 公众平台上，开发者设置的token */
	private String token;
	/** 公众平台上，开发者设置的EncodingAESKey */
	private String encodingAesKey;
	/** 公众平台上，开发者设置的上一次EncodingAESKey */
	private String encodingAesOldKey;
	/** 公众平台appid */
	private String appId;	

	/** 屏蔽默认构造函数 */
	@SuppressWarnings("unused")
	private DefaultEncyptPostProcess() {
	
	}
	
	/**
	 * 构造函数，设置加解密所需的参数
	 * @param token 公众平台上，开发者设置的token
	 * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
	 * @param encodingAesOldKey 公众平台上，开发者设置的上一次EncodingAESKey
	 * @param appId 公众平台appid
	 */
	public DefaultEncyptPostProcess(String token, String encodingAesKey, String encodingAesOldKey, String appId) {
		Assert.hasText(token, "token 参数不能为空");
		Assert.hasText(encodingAesKey, "encodingAesKey 参数不能为空");
		Assert.hasText(appId, "appId 参数不能为空");
		this.token = token;
		this.encodingAesKey = encodingAesKey;
		this.encodingAesOldKey = encodingAesOldKey;
		this.appId = appId;
	}

	@Override
	protected String decypt(String signature, String timeStamp, String nonce, String postData) throws AesException {
		String xmlStr = null;
		WXBizMsgCrypt pc;
		// 解密，并解析XML。出于安全考虑，公众平台网站提供了修改EncodingAESKey的功能(在EncodingAESKey可能泄漏时进行修改),
		// 所以建议公众账号保存当前的和上一次的EncodinAESKey，若当前EncodingAESKey解密失败，则尝试用上一次的EncodingAESKey的解密。
		// 回包时，用哪个Key解密成功，则用此Key加密对应的回包
		try{
			pc = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
			xmlStr = pc.decryptMsg(signature, timeStamp, nonce, postData);
		}catch (AesException e) {
			// 试验老的AESKEY
			try {
				pc = new WXBizMsgCrypt(this.token, this.encodingAesOldKey, this.appId);
				xmlStr = pc.decryptMsg(signature, timeStamp, nonce, postData);
			} catch (AesException e1) {
				throw e1;
			}
		}
		return xmlStr;
		//.encryptMsg(requestMap.get("Encrypt"), timestamp, nonce);
		//requestMap=MessageUtil.parseXml(new ByteArrayInputStream(mingwen.getBytes("UTF-8")));
	}

	@Override
	protected String encryption(String responseData, String timeStamp, String nonce) throws AesException {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
		return pc.encryptMsg(responseData, timeStamp, nonce);
	}

}
