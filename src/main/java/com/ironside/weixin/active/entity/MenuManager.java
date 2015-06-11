package com.ironside.weixin.active.entity;

import org.springframework.util.Assert;

/**
 * 菜单管理类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
public class MenuManager {

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
		button.setType(Button.CLICK);
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
		button.setType(Button.VIEW);
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
		button.setType(Button.SCANCODE_PUSH);
		button.setName(name);
		button.setKey(key);
		return button;
	}
}
