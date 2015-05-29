package com.ironside.weixin.passive.request.entity;


/**
 * 链接消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月7日
 */
public class LinkEntity extends AbstractBaseEntity {
	
	/** 消息标题 */
	private String Title;
	/** 消息描述 */
	private String Description;
	/** 消息链接 */
	private String Url;
	/** 消息id，64位整型 */
	private String MsgId;
	
	/**
	 * 取得消息标题
	 * @return 消息标题
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * 设置消息标题
	 * @param title 消息标题
	 */
	public void setTitle(String title) {
		this.Title = title;
	}
	
	/**
	 * 取得消息描述
	 * @return 消息描述
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * 设置消息描述
	 * @param description 消息描述
	 */
	public void setDescription(String description) {
		this.Description = description;
	}
	
	/**
	 * 取得消息链接
	 * @return 消息链接 
	 */
	public String getUrl() {
		return Url;
	}
	
	/**
	 * 设置消息链接
	 * @param url 消息链接
	 */
	public void setUrl(String url) {
		this.Url = url;
	}
	
	/**
	 * 取得消息id
	 * @return 消息id
	 */
	public String getMsgId() {
		return MsgId;
	}
	
	/**
	 * 设置消息id
	 * @param msgId 消息id
	 */
	public void setMsgId(String msgId) {
		this.MsgId = msgId;
	}
}
