package com.ironside.weixin.request;

import java.io.ByteArrayInputStream;

import org.springframework.util.Assert;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.waukeen.weixin.msg.MessageUtil;

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
	 * @param appId 公众平台appid
	 */
	public DefaultEncyptPostProcess(String token, String encodingAesKey, String appId) {
		Assert.hasText(token, "token 参数不能为空");
		Assert.hasText(encodingAesKey, "encodingAesKey 参数不能为空");
		Assert.hasText(appId, "appId 参数不能为空");
		this.token = token;
		this.encodingAesKey = encodingAesKey;
		this.appId = appId;
	}

	@Override
	protected String decypt(String signature, String timeStamp, String nonce,	String postData) {
		String mingwen = null;
		//解密，并解析XML
		try{
			pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			mingwen = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
		}catch (AesException e) {
			if(e.getCode()==AesException.IllegalAesKey){
				//试验老的AESKEY
				pc = new WXBizMsgCrypt(token, encodingAesOldKey, appId);
				mingwen = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
			}else{
				throw e;
			}
		}
		//.encryptMsg(requestMap.get("Encrypt"), timestamp, nonce);
		requestMap=MessageUtil.parseXml(new ByteArrayInputStream(mingwen.getBytes("UTF-8")));
	}

	@Override
	protected String encryption(String responseData, String timeStamp, String nonce) {
		return null;
	}

}
