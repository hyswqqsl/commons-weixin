package com.ironside.weixin.push.entity;


/**
 * POST方式推送给微信公众账号的消息类型
 * @author 雪庭
 * @since 1.0
 */
public enum EntityEnum {

	TEXT("text", "文本消息"),
	IMAGE("image", "图像消息"),
	VOICE("voice", "语音消息"),
	VIDEO("video", "视频消息"), 
	SHORTVIDEO("shortvideo", "小视频消息"),
	LOCATION("location", "地理位置消息"),
	LINK("link", "链接消息"),
	/** 关注/取消关注-订阅事件 或 扫描带参数二维码-用户未关注时，进行关注后的事件(需要根据EventKey来区分) */	
	EVENT_SUBSCRIBE("subscribe", "关注/取消关注-订阅事件"),
	VENT_UNSUBSCRIBE("unsubscribe", "关注/取消关注-取消订阅事件"),
	/** 关注/取消关注-订阅事件 或 扫描带参数二维码-用户未关注时，进行关注后的事件(需要根据EventKey来区分) */	
	EVENT_SCAN_SUBSCRIBE("subscribe", "扫描带参数二维码-用户未关注时，进行关注后的事件"),
	EVENT_SCAN("scan", "扫描带参数二维码-用户已关注时的事件"),
	EVENT_LOCATION("location", "上报地理位置事件"),
	EVENT_CLICK("click", "自定义菜单-点击菜单拉取消息时的事件"),
	EVENT_VIEW("view", "自定义菜单-点击菜单跳转链接时的事件");

	/**
	 * 消息类型构造函数
	 * @param identify 消息标识
	 * @param remark 消息说明
	 */
	EntityEnum(String identify, String remark) {
		this.identify = identify;
		this.remark = remark;
	}
	
	/** 消息识别 */
	private String identify;
	/** 消息说明 */
	private String remark;
	
	/**
	 * 取得消息识别
	 * @return 消息识别
	 */
	public String getIdentify() {
		return identify;
	}

	/**
	 * 取得消息说明
	 * @return 消息说明
	 */
	public String getRemark() {
		return remark;
	}
	
}
