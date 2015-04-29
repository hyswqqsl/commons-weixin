package com.ironside.weixin.response.entity;

import org.springframework.util.Assert;

/**
 * 音乐回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月27日
 */
public class MusicResponse extends AbstractBaseResponse {
	
	/** 对应xml中定义的'音乐消息内容'标识 */
	public final static String MUSIC = "Music";
	/** 对应xml中定义的'音乐链接'标识 */
	public final static String MUSIC_URL = "MusicUrl";
	/** 对应xml中定义的'高质量音乐链接，WIFI环境优先使用该链接播放音乐'标识 */
	public final static String H_Q_MUSIC_URL = "HQMusicUrl";
	/** 对应xml中定义的'缩略图的媒体id，通过上传多媒体文件，得到的id'标识 */
	public final static String THUMB_MEDIA_ID = "ThumbMediaId";
	
	private Music music;
	
	public MusicResponse() {
		this.music = new Music();
		this.setMsgEnum(ResponseEnum.MUSIC);
	}
	
	@Override
	public Object getObject(int index) {
		return this.music;
	}

	@Override
	public void addObject(Object obj) {
		Assert.isTrue(obj instanceof Music);
		this.music = (Music)obj;
	}

	@Override
	public int getObjectCount() {
		return 1;
	}	
	
	public class Music {
		/** 音乐标题 */
		private String title;
		/** 音乐描述 */
		private String description;
		/** 音乐链接 */
		private String musicUrl;
		/** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
		private String hQMusicUrl;
		/** 缩略图的媒体id，通过上传多媒体文件，得到的id */
		private String thumbMediaId;
		
		/**
		 * 取得音乐标题
		 * @return 音乐标题
		 */
		public String getTitle() {
			return title;
		}
		
		/**
		 * 设置音乐标题
		 * @param title 音乐标题
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * 取得音乐描述
		 * @return 音乐描述
		 */		
		public String getDescription() {
			return description;
		}

		/**
		 * 设置音乐描述
		 * @param title 音乐描述
		 */		
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * 取得音乐链接
		 * @return 音乐链接
		 */		
		public String getMusicUrl() {
			return musicUrl;
		}
		
		/**
		 * 设置音乐链接
		 * @param title 音乐链接
		 */		
		public void setMusicUrl(String musicUrl) {
			this.musicUrl = musicUrl;
		}
		
		/**
		 * 取得高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 * @return 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 */		
		public String getHQMusicUrl() {
			return hQMusicUrl;
		}
		
		/**
		 * 设置高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 * @param title 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 */		
		public void setHQMusicUrl(String hQMusicUrl) {
			this.hQMusicUrl = hQMusicUrl;
		}
		
		/**
		 * 取得缩略图的媒体id，通过上传多媒体文件，得到的id
		 * @return 缩略图的媒体id，通过上传多媒体文件，得到的id
		*/		
		public String getThumbMediaId() {
			return thumbMediaId;
		}
		
		/**
		 * 设置缩略图的媒体id，通过上传多媒体文件，得到的id
		 * @param title 缩略图的媒体id，通过上传多媒体文件，得到的id
		 */		
		public void setThumbMediaId(String thumbMediaId) {
			this.thumbMediaId = thumbMediaId;
		}
	}

}
