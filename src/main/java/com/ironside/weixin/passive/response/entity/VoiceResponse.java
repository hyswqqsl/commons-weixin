package com.ironside.weixin.passive.response.entity;

import org.springframework.util.Assert;

/**
 * 语音回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月27日
 */
public class VoiceResponse extends AbstractBaseResponse {

	/** 内部语音对象 */
	private Voice Voice;
	
	public VoiceResponse() {
		Voice = new Voice();
	}	
	
	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	public class Voice {
		
		private String MediaId;

		/**
		 * 取得通过上传多媒体文件，得到的id
		 * @return 通过上传多媒体文件，得到的id
		 */	
		public String getMediaId() {
			return MediaId;
		}

		/**
		 * 设置通过上传多媒体文件，得到的id
		 * @param mediaId 通过上传多媒体文件，得到的id
		 */
		public void setMediaId(String mediaId) {
			this.MediaId = mediaId;
		}
	}

}
