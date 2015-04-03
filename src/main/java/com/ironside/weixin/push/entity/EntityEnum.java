package com.ironside.weixin.push.entity;

/**
 * POST方式推送给微信公众账号的消息类型
 * @author 雪庭
 * @since 1.0
 */
public enum EntityEnum {

	/** 文本消息 */
	TEXT,
	/** 图像消息 */
	IMAGE,
	/** 语音消息 */
	VOICE,
	/** 视频消息 */
	VIDEO, 
	/** 小视频消息 */
	HORTVIDEO,
	/** 地理位置消息 */
	LOCATION,
	/** 链接消息 */
	LINK,
	/** 关注/取消关注-订阅事件 */
	EVENT_SUBSCRIBE,
	/** 关注/取消关注-取消订阅事件 */
	VENT_UNSUBSCRIBE,
	/** 扫描带参数二维码-用户未关注时，进行关注后的事件 */
	EVENT_SCAN_SUBSCRIBE,
	/** 扫描带参数二维码-用户已关注时的事件 */
	EVENT_SCAN,
	/** 上报地理位置事件 */
	EVENT_LOCATION,
	/** 自定义菜单-点击菜单拉取消息时的事件 */
	EVENT_CLICK,
	/** 自定义菜单-点击菜单跳转链接时的事件 */
	EVENT_VIEW
	
}
