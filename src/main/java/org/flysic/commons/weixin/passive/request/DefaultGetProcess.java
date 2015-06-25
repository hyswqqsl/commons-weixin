package org.flysic.commons.weixin.passive.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Assert;

/**
 * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
 * 并且带上四个参数(signature、timestamp、nonce、echostr)，
 * 开发者通过对签名(即signature)的效验，来判断此条消息的真实性。
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年5月26日
 */
public class DefaultGetProcess implements IGetProcess {

	public DefaultGetProcess() {

	}

	@Override
	public String process(String token, String signature, String timestamp,
			String nonce, String echostr) {
		Assert.hasText(token);
		Assert.hasText(signature);
		Assert.hasText(timestamp);
		Assert.hasText(nonce);
		Assert.hasText(echostr);
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		String temp = DigestUtils.sha1Hex(params.get(0) + params.get(1) + params.get(2));
		// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (temp.equals(signature)) {
			return echostr;
		}
		return null;
	}

}
