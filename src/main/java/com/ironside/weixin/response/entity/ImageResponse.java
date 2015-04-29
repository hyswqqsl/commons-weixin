package com.ironside.weixin.response.entity;

import org.springframework.util.Assert;

/**
 * 图片回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月20日
 */
public class ImageResponse extends AbstractBaseResponse {

	/** 对应xml中定义的'图片消息内容'标识 */
	public final static String IMAGE = "Image";
	
	/** 内部图片对象 */
	private Image image;
	
	public ImageResponse() {
		image = new Image();
		this.setMsgEnum(ResponseEnum.IMAGE);
	}
	
	@Override
	public Object getObject(int index) {
		return image;
	}

	@Override
	public void addObject(Object obj) {
		Assert.isTrue(obj instanceof Image);
		this.image = (Image)obj;
	}

	@Override
	public int getObjectCount() {
		return 1;
	}	
	
	public class Image {
		
		String mediaId;

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
