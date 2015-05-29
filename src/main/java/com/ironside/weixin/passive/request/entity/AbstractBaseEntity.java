package com.ironside.weixin.passive.request.entity;

/**
 * 基础POST实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月6日
 */
public abstract class AbstractBaseEntity {
	
	/** 对应xml中定义的'消息类型'标识 */
	public final static String MSG_TYPE = "MsgType";
	/** 对应xml中定义的'事件类型'标识 */
	public final static String EVENT = "Event";
	/** 事件KEY值 */
	public final static String EVENT_KEY = "EventKey";
	
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
	 * @param msgType 取得消息类型
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	/**
	 * 取得事件类型
	 * @return
	 */
	public String getEvent() {
		return null;
	}
	
	/**
	 * 取得事件KEY值
	 * @return 事件KEY值
	 */
	public String getEventKey() {
		return null;
	}
			
}
