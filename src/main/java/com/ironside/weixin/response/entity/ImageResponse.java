package com.ironside.weixin.response.entity;

/**
 * 图片回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月20日
 */
public class ImageResponse extends AbstractBaseResponse {

	/** 对应xml中定义的'文本消息内容'标识 */
	public final static String IMAGE = "Image";
	
	private Image image;
	
	public ImageResponse() {
		image = new Image();
		this.setMsgEnum(ResponseEnum.IMAGE);
	}
	
	public String getMediaId() {
		return this.image.getMediaId();
	}

	public void setMediaId(String mediaId) {
		this.image.setMediaId(mediaId);
	}

	public class Image {
		
		String mediaId;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}
}
