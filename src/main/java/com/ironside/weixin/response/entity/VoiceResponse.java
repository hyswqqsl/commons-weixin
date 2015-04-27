package com.ironside.weixin.response.entity;

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
	
	/**
	 * 取得通过上传多媒体文件，得到的id
	 * @return 通过上传多媒体文件，得到的id
	 */	
	public String getMediaId()  {
		return this.voice.getMediaId();
	}
	
	/**
	 * 设置通过上传多媒体文件，得到的id
	 * @param mediaId 通过上传多媒体文件，得到的id
	 */
	public void setMediaId(String mediaId) {
		this.voice.setMediaId(mediaId);
	}

	public class Voice {
		private String mediaId;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}
}
