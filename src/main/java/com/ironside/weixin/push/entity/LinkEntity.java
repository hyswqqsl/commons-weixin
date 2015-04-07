package com.ironside.weixin.push.entity;


/**
 * 链接消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月7日
 */
public class LinkEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'消息标题'标识 */
	public final static String TITLE = "Title";
	/** 对应xml中定义的'消息描述'标识 */
	public final static String DESCRIPTION = "Description";
	/** 对应xml中定义的'消息链接'标识 */
	public final static String URL = "Url";
	
	/** 消息标题 */
	private String title;
	/** 消息描述 */
	private String description;
	/** 消息链接 */
	private String url;
	/** 消息id，64位整型 */
	private String msgId;
	
	/**
	 * 构造函数，设置类型
	 */
	public LinkEntity() {
		this.setMsgEnum(EntityEnum.LINK);
	}
	
	/**
	 * 取得消息标题
	 * @return 消息标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 设置消息标题
	 * @param title 消息标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 取得消息描述
	 * @return 消息描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置消息描述
	 * @param description 消息描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 取得消息链接
	 * @return 消息链接 
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 设置消息链接
	 * @param url 消息链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 取得消息id
	 * @return 消息id
	 */
	public String getMsgId() {
		return msgId;
	}
	
	/**
	 * 设置消息id
	 * @param msgId 消息id
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
