package org.flysic.commons.weixin.active.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 二级按钮类
 * @author 雪庭
 * @sine 1.0 at 2015年6月13日
 */
@XStreamAlias("sub_button")
public class SubButton extends Button {
	
	public SubButton(Button button) {
		this.setKey(button.getKey());
		this.setMediaId(button.getMediaId());
		this.setName(button.getName());
		this.setType(button.getType());
		this.setUrl(button.getUrl());
	}

}
