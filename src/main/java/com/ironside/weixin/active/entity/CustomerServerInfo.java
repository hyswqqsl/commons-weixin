package com.ironside.weixin.active.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class CustomerServerInfo {

	/**	完整客服账号，格式为：账号前缀@公众号微信号 */
	@XStreamAlias("kf_account")
	private String count;
	/** 客服在线状态 1：pc在线，2：手机在线。若pc和手机同时在线则为 1+2=3 */
	@XStreamOmitField
	private int status;
	/**	客服工号 */
	@XStreamOmitField
	@XStreamAlias("kf_id")
	private String kfId;
	/** 客服设置的最大自动接入数 */
	@XStreamOmitField
	@XStreamAlias("auto_accept")
	private int autoAccept;
	/** 客服当前正在接待的会话数 */
	@XStreamOmitField
	@XStreamAlias("accepted_case") 
	private int acceptedCase;
	/** 客服昵称，最长6个汉字或12个英文字符 */
	@XStreamAlias("nickname")
	private String nickName;
	/** 客服账号登录密码，格式为密码明文的32位加密MD5值 */
	private String password;
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getKfId() {
		return kfId;
	}
	
	public void setKfId(String kfId) {
		this.kfId = kfId;
	}
	
	public int getAutoAccept() {
		return autoAccept;
	}
	
	public void setAutoAccept(int autoAccept) {
		this.autoAccept = autoAccept;
	}
	
	public int getAcceptedCase() {
		return acceptedCase;
	}
	
	public void setAcceptedCase(int acceptedCase) {
		this.acceptedCase = acceptedCase;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
