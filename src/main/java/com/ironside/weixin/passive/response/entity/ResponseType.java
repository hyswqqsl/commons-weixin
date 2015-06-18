package com.ironside.weixin.passive.response.entity;

/**
 * 被动回复用户消息类型
 * @author 雪庭
 * @sine 1.0 at 2015年4月16日
 */
public class ResponseType {
	
	/** 文本回复 */
	public static final String TEXT = "text";
	/** 图像回复 */
	public static final String IMAGE = "image";
	/** 语音回复 */
	public static final String VOICE = "voice";
	/** 视频回复 */
	public static final String VIDEO = "video";
	/** 音乐回复 */
	public static final String MUSIC = "music";
	/** 图文回复 */
	public static final String NEWS = "news";
	/** 消息转发到多客服 */
	public static final String TRANSFER_CUSTOMER = "transfer_customer_service";
}
