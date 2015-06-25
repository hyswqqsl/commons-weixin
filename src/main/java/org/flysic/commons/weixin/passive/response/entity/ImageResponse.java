package org.flysic.commons.weixin.passive.response.entity;


/**
 * 图片回复实体类
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年4月20日
 */
public class ImageResponse extends AbstractBaseResponse {
	
	/** 内部图片对象 */
	private Image Image;
	
	public ImageResponse() {
		Image = new Image();
	}
	
	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	public class Image {
		
		String MediaId;

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
