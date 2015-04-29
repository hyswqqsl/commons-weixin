package com.ironside.weixin.response.entity;

import org.springframework.util.Assert;

/**
 * 语音回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月27日
 */
public class VoiceResponse extends AbstractBaseResponse {

	/** 对应xml中定义的'语音消息内容'标识 */
	public final static String VOICE = "Voice";
	
	/** 内部语音对象 */
	private Voice voice;
	
	public VoiceResponse() {
		voice = new Voice();
		this.setMsgEnum(ResponseEnum.VOICE);
	}
	
	@Override
	public Object getObject(int index) {
		return this.voice;
	}

	@Override
	public void addObject(Object obj) {
		Assert.isTrue(obj instanceof Voice);
		this.voice = (Voice)obj;
	}

	@Override
	public int getObjectCount() {
		return 1;
	}	
	
	public class Voice {
		
		private String mediaId;

		/**
		 * 取得通过上传多媒体文件，得到的id
		 * @return 通过上传多媒体文件，得到的id
		 */	
		public String getMediaId() {
			return mediaId;
		}

		/**
		 * 设置通过上传多媒体文件，得到的id
		 * @param mediaId 通过上传多媒体文件，得到的id
		 */
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

}
