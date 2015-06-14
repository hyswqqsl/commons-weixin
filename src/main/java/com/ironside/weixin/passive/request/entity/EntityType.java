package com.ironside.weixin.passive.request.entity;

/**
 * 接收消息/事件类型
 * @author 雪庭
 * @sine 1.0 at 2015年4月17日
 */
public class EntityType {

	// *** 消息类型 ***
	/** 文本消息 */
	public static final String TEXT = "text";
	/** 图像消息 */
	public static final String IMAGE = "image";
	/** 语音消息 */
	public static final String VOICE = "voice";
	/** 视频消息 */
	public static final String VIDEO = "video";
	/** 小视频消息 */
	public static final String SHORTVIDEO = "shortvideo";
	/** 地理位置消息 */
	public static final String LOCATION = "location";
	/** 链接消息 */
	public static final String LINK = "link";
	/** 事件消息 */
	public static final String EVENT = "event";
	
	// *** 事件类型 ***
	/**
	 * subscribe类型对应有两种事件：
	 * <ul>
	 * <li>EventSubscribeEntity(没有eventKey属性)，关注/取消关注-订阅事件</li> 
	 * <li>EventScanSubscribeEntity(有eventKey属性)，扫描带参数二维码-用户未关注时，进行关注后的事件</li>
	 * </ul> 
	 **/
	public static final String EVENT_SUBSCRIBE =  "subscribe";
	/** 关注/取消关注-取消订阅事件 */
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	/** 扫描带参数二维码-用户已关注时的事件 */
	public static final String EVENT_SCAN = "scan";
	/** 上报地理位置事件 */
	public static final String EVENT_LOCATION = "location";
	/** 自定义菜单-点击菜单拉取消息时的事件 */
	public static final String EVENT_MENU_CLICK = "click";
	/** 自定义菜单-点击菜单跳转链接时的事件 */
	public static final String EVENT_MENU_VIEW = "view";
	/** 自定义菜单-扫码推事件 */
	public static final String EVENT_MENU_SCANCODE_PUSH = "scancode_push";
	/** 自定义菜单-扫码推事件且弹出“消息接收中”提示框 */
	public static final String EVENT_MENU_SCANCODE_WAITMSG = "scancode_waitmsg";
	/** 自定义菜单-弹出系统拍照发图 */
	public static final String EVENT_MENU_PIC_SYSPHOTO = "pic_sysphoto";
	/** 自定义菜单-弹出拍照或者相册发图 */
	public static final String EVENT_MENU_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	/** 自定义菜单-弹出微信相册发图器 */
	public static final String EVENT_MENU_PIC_WEIXIN = "pic_weixin";
	/** 自定义菜单-弹出地理位置选择器 */
	public static final String EVENT_MENU_LOCATION_SELECT = "location_select";
}
