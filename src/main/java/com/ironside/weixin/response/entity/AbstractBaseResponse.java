package com.ironside.weixin.response.entity;

import java.util.Date;

/**
 * 基础回复实体类
 * @author ZXJ
 * @sine 1.0 at 2015年4月9日
 */
public abstract class AbstractBaseResponse {
	
	/** 对应xml中定义的'开发者微信号'标识 */
	public final static String TO_USER_NAME = "ToUserName";
	/** 对应xml中定义的'发送方帐号'标识 */
	public final static String FORM_USER_NAME = "FromUserName";
	/** 对应xml中定义的'消息创建时间'标识 */
	public final static String CREATE_TIME = "CreateTime";
	/** 对应xml中定义的'消息类型'标识 */
	public final static String MSG_TYPE = "MsgType";
	/** 对应xml中定义的'通过上传多媒体文件，得到的id '标识 */
	public final static String MEDIA_ID = "MediaId";	
	public final static String TITLE = "Title";	
	/** 对应xml中定义的'视频消息描述'标识 */
	public final static String DESCRIPTION = "Description";	
		
	/** 开发者微信号  */
	private String ToUserName;
	/** 发送方帐号（一个OpenID） */
	private String FromUserName;
	/** 消息创建时间  */
	private String CreateTime;
	/** 消息类型 */
	private String MsgType;
	
	/**
	 * 取得开发者微信号
	 * @return 开发者微信号
	 */
	public String getToUserName() {
		return ToUserName;
	}
	
	/**
	 * 设置开发者微信号
	 * @param toUserName 开发者微信号
	 */
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	
	/**
	 * 取得发送方帐号
	 * @return 发送方帐号
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	
	/**
	 * 设置发送方帐号
	 * @param fromUserName 发送方帐号
	 */
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	
	/**
	 * 取得消息创建时间
	 * @return 消息创建时间
	 */
	public String getCreateTime() {
		return CreateTime;
	}
	
	/**
	 * 设置消息创建时间
	 * @param createTime 消息创建时间
	 */
	public void setCreateTime(String createTime) {
		this.CreateTime = createTime;
	}

	/**
	 * 取得消息类型
	 * @return 消息类型
	 */
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * 设置消息类型
	 * @param msgType 消息类型
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
 	}

}
