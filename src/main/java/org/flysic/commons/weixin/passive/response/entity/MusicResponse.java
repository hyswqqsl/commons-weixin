package org.flysic.commons.weixin.passive.response.entity;

import org.springframework.util.Assert;

/**
 * 音乐回复实体类
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年4月27日
 */
public class MusicResponse extends AbstractBaseResponse {
		
	private Music Music;
	
	public MusicResponse() {
		this.Music = new Music();
	}
		
	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

	public class Music {
		/** 音乐标题 */
		private String Title;
		/** 音乐描述 */
		private String Description;
		/** 音乐链接 */
		private String MusicUrl;
		/** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
		private String HQMusicUrl;
		/** 缩略图的媒体id，通过上传多媒体文件，得到的id */
		private String ThumbMediaId;
		
		/**
		 * 取得音乐标题
		 * @return 音乐标题
		 */
		public String getTitle() {
			return Title;
		}
		
		/**
		 * 设置音乐标题
		 * @param title 音乐标题
		 */
		public void setTitle(String title) {
			this.Title = title;
		}
		
		/**
		 * 取得音乐描述
		 * @return 音乐描述
		 */		
		public String getDescription() {
			return Description;
		}

		/**
		 * 设置音乐描述
		 * @param Title 音乐描述
		 */		
		public void setDescription(String description) {
			this.Description = description;
		}
		
		/**
		 * 取得音乐链接
		 * @return 音乐链接
		 */		
		public String getMusicUrl() {
			return MusicUrl;
		}
		
		/**
		 * 设置音乐链接
		 * @param Title 音乐链接
		 */		
		public void setMusicUrl(String musicUrl) {
			this.MusicUrl = musicUrl;
		}
		
		/**
		 * 取得高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 * @return 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 */		
		public String getHQMusicUrl() {
			return HQMusicUrl;
		}
		
		/**
		 * 设置高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 * @param Title 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		 */		
		public void setHQMusicUrl(String hQMusicUrl) {
			this.HQMusicUrl = hQMusicUrl;
		}
		
		/**
		 * 取得缩略图的媒体id，通过上传多媒体文件，得到的id
		 * @return 缩略图的媒体id，通过上传多媒体文件，得到的id
		*/		
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		
		/**
		 * 设置缩略图的媒体id，通过上传多媒体文件，得到的id
		 * @param Title 缩略图的媒体id，通过上传多媒体文件，得到的id
		 */		
		public void setThumbMediaId(String thumbMediaId) {
			this.ThumbMediaId = thumbMediaId;
		}
	}

}
