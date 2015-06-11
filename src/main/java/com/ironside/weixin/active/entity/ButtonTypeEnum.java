package com.ironside.weixin.active.entity;

/**
 * 菜单类型枚举
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
public enum ButtonTypeEnum {

	/** 点击推事件 */
	CLICK("click"),
	/** 跳转URL */
	VIEW("view"),
	/** 扫码推事件 */
	SCANCODE_PUSH("scancode_push"),
	/** 扫码推事件且弹出“消息接收中”提示框 */
	SCANCODE_WAITMSG("scancode_waitmsg"),
	/** 弹出系统拍照发图 */
	PIC_SYSPHOTO("pic_sysphoto"),
	/** 弹出拍照或者相册发图 */
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
	/** 弹出微信相册发图器 */
	PIC_WEIXIN("pic_weixin"),
	/** 弹出地理位置选择器 */
	LOCATION_SELECT("location_select"),
	/** 下发消息（除文本消息） */
	MEDIA_ID("media_id"),
	/** 跳转图文消息URL */
	VIEW_LIMITED("view_limited");
	
	private String name;
	
	private ButtonTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
