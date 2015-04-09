package com.ironside.weixin.request.entity;

/**
 * 语音消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月7日
 */
public class VoiceEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'语音格式'标识 */
	public final static String FORMAT = "Format";

	/** 语音消息媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String MediaId;
	/** 语音格式，如amr，speex等 */
	private String format;
	/** 消息id，64位整型 */
	private String msgId;
	
	/**
	 * 构造函数，设置类型 
	 */
	public VoiceEntity() {
		this.setMsgEnum(EntityEnum.VOICE);
	}
	
	/**
	 * 取得语音消息媒体id
	 * @return 语音消息媒体id
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * 设置语音消息媒体id
	 * @param mediaId 语音消息媒体id
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	/**
	 * 取得语音格式
	 * @return 语音格式
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * 设置语音格式
	 * @param format 语音格式
	 */	
	public void setFormat(String format) {
		this.format = format;
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
