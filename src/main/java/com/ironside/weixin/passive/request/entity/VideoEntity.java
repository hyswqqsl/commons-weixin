package com.ironside.weixin.passive.request.entity;

/**
 * 视频消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月7日
 */
public class VideoEntity extends AbstractBaseEntity {
	
	/** 视频消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String MediaId;
	/** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String ThumbMediaId;
	/** 消息id，64位整型 */
	private String MsgId;
	
	/**
	 * 取得视频消息媒体id
	 * @return 视频消息媒体id
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * 设置视频消息媒体id
	 * @param mediaId 视频消息媒体id
	 */	
	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}
	
	/**
	 * 取得视频消息缩略图的媒体id
	 * @return 视频消息缩略图的媒体id
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	
	/**
	 * 设置视频消息缩略图的媒体id
	 * @param thumbMediaId 视频消息缩略图的媒体id
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.ThumbMediaId = thumbMediaId;
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
