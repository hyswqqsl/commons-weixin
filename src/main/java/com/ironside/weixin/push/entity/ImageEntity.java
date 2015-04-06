package com.ironside.weixin.push.entity;

/**
 * 图片消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月6日
 */
public class ImageEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'图片链接'标识 */
	public final static String PIC_URL= "PicUrl";
	/** 对应xml中定义的'图片消息媒体id'标识 */
	public final static String MEDIA_URL= "MediaId";
	

	/** 图片链接 */
	private String picUrl;
	/** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String mediaId;
	/** 消息id，64位整型  */
	private String msgId;
	
	/**
	 * 取得图片链接
	 * @return 图片链接
	 */
	public String getPicUrl() {
		return picUrl;
	}
	
	/**
	 * 设置图片链接
	 * @param picUrl 图片链接
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	/**
	 * 取得图片消息媒体id
	 * @return 图片消息媒体id
	 */	
	public String getMediaId() {
		return mediaId;
	}
	
	/**
	 * 设置图片消息媒体id
	 * @param picUrl 图片消息媒体id
	 */	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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
	 * @param picUrl 消息id
	 */	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}
