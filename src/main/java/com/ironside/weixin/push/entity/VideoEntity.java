package com.ironside.weixin.push.entity;

/**
 * 视频消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月7日
 */
public class VideoEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'语音格式'标识 */
	public final static String THUMB_MEDIA_ID = "ThumbMediaId";
	
	/** 视频消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String mediaId;
	/** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String thumbMediaId;
	/** 消息id，64位整型 */
	private String msgId;
	
	/**
	 * 构造函数，设置类型
	 */
	public VideoEntity() {
		this.setMsgEnum(EntityEnum.VIDEO);
	}
	
	/**
	 * 取得视频消息媒体id
	 * @return 视频消息媒体id
	 */
	public String getMediaId() {
		return mediaId;
	}
	
	/**
	 * 设置视频消息媒体id
	 * @param mediaId 视频消息媒体id
	 */	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	/**
	 * 取得视频消息缩略图的媒体id
	 * @return 视频消息缩略图的媒体id
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	/**
	 * 设置视频消息缩略图的媒体id
	 * @param thumbMediaId 视频消息缩略图的媒体id
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
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
