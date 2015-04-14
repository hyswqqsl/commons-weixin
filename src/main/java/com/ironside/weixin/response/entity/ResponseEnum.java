package com.ironside.weixin.response.entity;

/**
 * 被动回复用户消息类型
 * @author 雪庭
 * @sine 1.0 at 2015年4月14日
 */
public enum ResponseEnum {
	
	/** 文本回复 */
	TEXT("text"),
	/** 图像回复 */
	IMAGE("image"),
	/** 语音回复 */
	VOICE("voice"),
	/** 视频回复 */
	VIDEO("video"),
	/** 音乐回复 */
	MUSIC("music"),
	/** 图文回复 */
	NEWS("news");
	
	/**
	 * 消息类型构造函数
	 * @param msgType 消息类型
	 */	
	private ResponseEnum(String msgType) {
		this.msgType = msgType;
	}
	
	private String msgType;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	} 
}
