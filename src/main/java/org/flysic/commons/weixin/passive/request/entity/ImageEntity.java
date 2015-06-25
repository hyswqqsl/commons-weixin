package org.flysic.commons.weixin.passive.request.entity;

/**
 * 图片消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月6日
 */
public class ImageEntity extends AbstractBaseEntity {
	
	/** 图片链接 */
	private String PicUrl;
	/** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String MediaId;
	/** 消息id，64位整型 */
	private String MsgId;
	
	/**
	 * 取得图片链接
	 * @return 图片链接
	 */
	public String getPicUrl() {
		return PicUrl;
	}
	
	/**
	 * 设置图片链接
	 * @param picUrl 图片链接
	 */
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
	
	/**
	 * 取得图片消息媒体id
	 * @return 图片消息媒体id
	 */	
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * 设置图片消息媒体id
	 * @param mediaId 图片消息媒体id
	 */	
	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
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
