package com.ironside.weixin.active.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
