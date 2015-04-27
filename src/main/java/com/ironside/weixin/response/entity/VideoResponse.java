package com.ironside.weixin.response.entity;

/**
 * 视频回复实体类
 * @author ZXJ
 * @sine 1.0 at 2015年4月27日
 */
public class VideoResponse extends AbstractBaseResponse {

	/** 对应xml中定义的'视频消息内容'标识 */
	public final static String VIDEO = "Video";	
	/** 对应xml中定义的'视频消息标题'标识 */
	
	/** 内部视频对象 */
	private Video video;
	
	public VideoResponse() {
		video = new Video();
		this.setMsgEnum(ResponseEnum.VIDEO);
	}
	
	/**
	 * 取得通过上传多媒体文件，得到的id
	 * @return 通过上传多媒体文件，得到的id
	 */
	public String getMediaId() {
		return this.video.getMediaId();
	}
	
	/**
	 * 设置通过上传多媒体文件，得到的id
	 * @param mediaId 通过上传多媒体文件，得到的id
	 */
	public void setMediaId(String mediaId) {
		this.video.setMediaId(mediaId);
	}
	
	/**
	 * 取得视频消息的标题
	 * @return 视频消息的标题
	 */
	public String getTitle() {
		return this.video.getTitle();
	}
	
	/**
	 * 设置视频消息的标题
	 * @param title 视频消息的标题
	 */
	public void setTitle(String title) {
		this.video.setTitle(title);
	}
	
	/**
	 * 取得视频消息的描述
	 * @return 视频消息的描述
	 */
	public String getDescription() {
		return this.video.getDescription();
	}
	
	/**
	 * 设置视频消息的描述
	 * @param description 视频消息的描述
	 */
	public void setDescription(String description) {
		this.video.setDescription(description);
	}
	
	public class Video {
		
		private String mediaId;
		private String title;
		private String description;		
		
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
		
		/**
		 * 取得视频消息的标题
		 * @return 视频消息的标题
		 */
		public String getTitle() {
			return title;
		}
		
		/**
		 * 设置视频消息的标题
		 * @param title 视频消息的标题
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * 取得视频消息的描述
		 * @return 视频消息的描述
		 */
		public String getDescription() {
			return description;
		}
		
		/**
		 * 设置视频消息的描述
		 * @param description 视频消息的描述
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		
	}
}
