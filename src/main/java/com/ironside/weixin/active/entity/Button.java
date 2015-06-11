package com.ironside.weixin.active.entity;

import org.springframework.util.Assert;

/**
 * 菜单类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
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
	private String mediaId;
	
	/**
	 * 隐藏构造函数
	 */
	private Button() {
	}
	
	/**
	 * 验证菜单标题，验证不通过，抛出IllegalArgumentException异常
	 * @param name 菜单标题
	 */
	private static void validateName(String name) {
		Assert.hasText(name);
		Assert.isTrue(name.length()<=16);
	}
	
	/**
	 * 验证菜单KEY值，验证不通过，抛出IllegalArgumentException异常
	 * @param key 菜单KEY值
	 */
	private static void validateKey(String key) {
		Assert.hasText(key);
		Assert.isTrue(key.length()<=128);
	}
	
	/**
	 * 验证网页链接，验证不通过，抛出IllegalArgumentException异常
	 * @param url
	 */
	private static void validateUrl(String url) {
		Assert.hasText(url);
		Assert.isTrue(url.length()<=256);		
	}
	
	/**
	 * 建立点击推事件菜单
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 点击推事件菜单
	 */
	public static Button makeClickButton(String name, String key) {
		validateName(name);
		validateKey(key);
		Button button = new Button();
		button.setType(CLICK);
		button.setName(name);
		button.setKey(key);
		return button;
	}
	
	/**
	 * 建立跳转URL菜单
	 * @param name 菜单标题
	 * @param url 网页链接
	 * @return 跳转URL菜单
	 */
	public static Button makeViewButton(String name, String url) {
		validateUrl(name);
		validateUrl(url);
		Button button = new Button();
		button.setType(VIEW);
		button.setName(name);
		button.setUrl(url);
		return button;
	}
	
	/**
	 * 建立扫码推事件菜单
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 扫码推事件菜单
	 */
	public static Button makeScancodePushButton(String name, String key) {
		validateName(name);
		validateKey(key);
		Button button = new Button();
		button.setType(SCANCODE_PUSH);
		button.setName(name);
		button.setKey(key);
		return button;
	}

	public String getType() {
		return type;
	}
	
	private void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	
	private void setKey(String key) {
		this.key = key;
	}
	
	public String getUrl() {
		return url;
	}
	
	private void setUrl(String url) {
		this.url = url;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	private void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	/** 点击推事件 */
	public static String CLICK = "click";
	/** 跳转URL */
	public static String VIEW = "view";
	/** 扫码推事件 */
	public static String SCANCODE_PUSH = "scancode_push";
	/** 扫码推事件且弹出“消息接收中”提示框 */
	public static String SCANCODE_WAITMSG = "scancode_waitmsg";
	/** 弹出系统拍照发图 */
	public static String PIC_SYSPHOTO = "pic_sysphoto";
	/** 弹出拍照或者相册发图 */
	public static String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	/** 弹出微信相册发图器 */
	public static String PIC_WEIXIN = "pic_weixin";
	/** 弹出地理位置选择器 */
	public static String LOCATION_SELECT = "location_select";
	/** 下发消息（除文本消息） */
	public static String MEDIA_ID = "media_id";
	/** 跳转图文消息URL */
	public static String VIEW_LIMITED= "view_limited";

}
