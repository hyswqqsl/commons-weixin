package com.ironside.weixin.push.entity;

import java.util.Date;

/**
 * 基础实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月6日
 */
public abstract class AbstractBaseEntity {
	
	/** 对应xml中定义的'开发者微信号'标识 */
	public final static String TO_USER_NAME = "ToUserName";
	/** 对应xml中定义的'发送方帐号'标识 */
	public final static String FORM_USER_NAME = "FromUserName";
	/** 对应xml中定义的'消息创建时间'标识 */
	public final static String CREATE_TIME = "CreateTime";
	/** 对应xml中定义的'消息类型'标识 */
	public final static String MSG_TYPE = "MsgType";
	/** 对应xml中定义的'消息id'标识 */
	public final static String MSG_ID= "MsgId";
	/** 对应xml中定义的'消息媒体id'标识 */
	public final static String MEDIA_ID= "MediaId";
	
	/** 开发者微信号  */
	private String toUserName;
	/** 发送方帐号（一个OpenID） */
	private String fromUserName;
	/** 消息创建时间  */
	private Date createTime;
	/** 消息类型对象 */
	private EntityEnum msgEnum; 
	
	
	/**
	 * 取得开发者微信号
	 * @return 开发者微信号
	 */
	public String getToUserName() {
		return toUserName;
	}
	
	/**
	 * 设置开发者微信号
	 * @param toUserName 开发者微信号
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	/**
	 * 取得发送方帐号
	 * @return 发送方帐号
	 */
	public String getFromUserName() {
		return fromUserName;
	}
	
	/**
	 * 设置发送方帐号
	 * @param fromUserName 发送方帐号
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	/**
	 * 取得消息创建时间
	 * @return 消息创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置消息创建时间
	 * @param createTime 消息创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 取得消息类型对象
	 * @return 消息类型对象
	 */
	public EntityEnum getMsgEnum() {
		return msgEnum;
	}
	
	/**
	 * 设置消息类型对象
	 * @param msgType 消息类型对象
	 */
	public void setMsgEnum(EntityEnum msgEnum) {
		this.msgEnum = msgEnum;
	}
		
	/**
	 * 取得消息类型
	 * @return 消息类型
	 */
	public String getMsgType() {
		return getMsgEnum().getMsgType();
	}
	
}
