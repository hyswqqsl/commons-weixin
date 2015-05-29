package com.ironside.weixin.active.entity;

import java.util.Date;

/**
 * access_token是公众号的全局唯一凭证，公众号调用各接口时都需使用access_token。
 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年5月29日
 */
public class AccessToken {

	/** 获取到的凭证 */
	private String access_token;

	/** 凭证有效时间，单位：秒 */
	private int expires_in;

	/** 获取到的时间 */
	private long access_time;

	/**
	 * 凭证是否过期
	 * 
	 * @return
	 */
	public boolean isOver() {
		long now = System.currentTimeMillis() / 1000;
		if ((now - access_time) >= expires_in) {
			return true;
		}
		return false;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public void setAccess_time(long access_time) {
		this.access_time = access_time;
	}

	public long getAccess_time() {
		return access_time;
	}

}
