package org.flysic.commons.weixin.passive.response.entity;


/**
 * 视频回复实体类
 * @author ZXJ
 * @sine 1.0 at 2015年4月27日
 */
public class VideoResponse extends AbstractBaseResponse {	
	
	/** 内部视频对象 */
	private Video Video;
	
	public VideoResponse() {
		Video = new Video();
	}
	
	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	public class Video {
		
		private String MediaId;
		private String Title;
		private String Description;		
		
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
		
		/**
		 * 取得视频消息的标题
		 * @return 视频消息的标题
		 */
		public String getTitle() {
			return Title;
		}
		
		/**
		 * 设置视频消息的标题
		 * @param title 视频消息的标题
		 */
		public void setTitle(String title) {
			this.Title = title;
		}
		
		/**
		 * 取得视频消息的描述
		 * @return 视频消息的描述
		 */
		public String getDescription() {
			return Description;
		}
		
		/**
		 * 设置视频消息的描述
		 * @param description 视频消息的描述
		 */
		public void setDescription(String description) {
			this.Description = description;
		}
		
		
	}

}
