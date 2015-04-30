package com.ironside.weixin.response.xsentity;

public class ImageResponse extends AbstractBaseResponse {
	
	public Image Image;
	
	public ImageResponse() {
		Image = new Image();
	}

	public class Image {
		public String MediaId;
	}
}
