package com.ironside.weixin;


/**
 * 微信异常类
 * 公众号每次调用接口时，可能获得正确或错误的返回码，开发者可以根据返回码信息调试接口，排查错误。
 * @author 雪庭
 * @sine 1.0 at 2015年6月8日
 */
public class WeixinException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	
	public WeixinException(int code, String message) {
		super(code + ": " + message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
