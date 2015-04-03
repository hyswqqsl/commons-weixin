package com.ironside.weixin.push.entity;

import java.util.AbstractMap;
import java.util.Map;

/**
 * 解析POST消息为实体，包括普通消息和事件消息。
 * @author 雪庭
 * @since 1.0
 */
public class EntityAnalyzer {
	
	/** 文本消息 */
	static final String TEXT = "text";
	/** 图像消息 */
	static final String IMAGE = "image";
	/** 语音消息 */
	static final String VOICE = "voice";
	/** 视频消息 */
	static final String VIDEO = "video";
	/** 小视频消息 */
	static final String SHORTVIDEO = "shortvideo";
	/** 地理位置消息 */
	static final String LOCATION = "location";
	/** 链接消息 */
	static final String LINK = "link";
	/** 关注/取消关注-订阅事件 */
	static final String EVENT_SUBSCRIBE = "event_subscribe";
	/** 关注/取消关注-取消订阅事件 */
	static final String EVENT_UNSUBSCRIBE = "event_unsubscribe";	
	/** 扫描带参数二维码-用户未关注时，进行关注后的事件 */
	static final String EVENT_SCAN_SUBSCRIBE = "event_scan_subscribe";
	/** 扫描带参数二维码-用户已关注时的事件 */
	static final String EVENT_SCAN_= "event_scan";	
	/** 上报地理位置事件 */
	static final String EVENT_LOCATION = "event_location";
	/** 自定义菜单-点击菜单拉取消息时的事件 */
	static final String EVENT_CLICK = "event_click";
	/** 自定义菜单-点击菜单跳转链接时的事件 */
	static final String EVENT_VIEW = "event_view";	

	/**
	 * 解析POST消息为实体
	 * @param postData POST方式推送的数据
	 * @return 解析出来的信息实体
	 */
	public static Map.Entry<String, Object> analyze(String postData) {
		
		return new AbstractMap.SimpleEntry<String, Object>("abc", "k"); 
	}
}