package com.ironside.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 一级按钮类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
@XStreamAlias("button")
public class Button {

	// 菜单的响应动作类型
	private String type;
	// 菜单标题，不超过16个字节，子菜单不超过40个字节 
	private String name;
	// 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;
	// 网页链接，用户点击菜单可打开链接，不超过256字节 
	private String url;
	// 调用新增永久素材接口返回的合法media_id
	@XStreamAlias("media_id")
	private String mediaId;
	// 二级菜单
	@XStreamImplicit
	@XStreamAlias("sub_button")
	private List<SubButton> subButtonList;
	
	public Button() {
		this.subButtonList = new ArrayList<SubButton>();
	}
	
	/**
	 * 添加二级菜单
	 * @param subButton 二级菜单
	 */
	public void addSubButton(Button subButton) {
		if (this.subButtonList.size()>=5) {
			return;
		}
		this.type = null;
		this.key = null;
		this.url = null;
		this.mediaId = null;
		this.subButtonList.add(new SubButton(subButton));
	}
		
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public List<SubButton> getButtonList() {
		return subButtonList;
	}

	public void setButtonList(List<SubButton> buttonList) {
		this.subButtonList = buttonList;
	}
	
}
