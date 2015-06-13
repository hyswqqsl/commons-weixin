package com.ironside.weixin.active.entity;

import org.springframework.util.Assert;

import com.ironside.weixin.passive.request.entity.EntityType;

/**
 * 菜单工厂类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
public class MenuFactory {

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
	 * 建立有Key值的菜单按钮
	 * @param type 菜单类型
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	private static Button makeEventkeyButton(String type, String name, String key) {
		validateName(name);
		validateKey(key);
		Button button = new Button();
		button.setType(type);
		button.setName(name);
		button.setKey(key);
		return button; 								
	}	
	
	/**
	 * 建立点击推事件菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makeClickButton(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_CLICK, name, key);
	}
	
	/**
	 * 建立跳转URL菜单按钮
	 * @param name 菜单标题
	 * @param url 网页链接
	 * @return 菜单按钮
	 */
	public static Button makeViewButton(String name, String url) {
		validateUrl(name);
		validateUrl(url);
		Button button = new Button();
		button.setType(EntityType.EVENT_MENU_VIEW);
		button.setName(name);
		button.setUrl(url);
		return button;
	}
	
	/**
	 * 建立扫码推事件菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makeScancodePushButton(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_SCANCODE_PUSH, name, key);
	}
	
	/**
	 * 建立扫码推事件且弹出“消息接收中”提示框菜单按钮
	 * @param name 菜单标题 
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */	
	public static Button makeScancodeWaitmsg(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_SCANCODE_WAITMSG, name, key);
	}
	
	/**
	 * 建立弹出系统拍照发图菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makePicSysphoto(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_PIC_SYSPHOTO, name, key);
	}
	
	/**
	 * 建立弹出拍照或者相册发图菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makePicPhotoOrAlbum(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_PIC_PHOTO_OR_ALBUM, name, key);
	}
	
	/**
	 * 建立弹出微信相册发图器菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makePicWeixin(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_PIC_WEIXIN, name, key);
	}
	
	/**
	 * 建立弹出地理位置选择器菜单按钮
	 * @param name 菜单标题
	 * @param key 菜单KEY值
	 * @return 菜单按钮
	 */
	public static Button makeLocationSelect(String name, String key) {
		return makeEventkeyButton(EntityType.EVENT_MENU_LOCATION_SELECT, name, key);
	}
	
}
