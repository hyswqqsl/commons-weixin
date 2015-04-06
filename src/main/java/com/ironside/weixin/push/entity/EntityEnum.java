package com.ironside.weixin.push.entity;


/**
 * POST方式推送给微信公众账号的消息类型
 * @author 雪庭
 * @since 1.0
 */
public enum EntityEnum {

	TEXT("text", null, "文本消息"),
	IMAGE("image", null, "图像消息"),
	VOICE("voice", null, "语音消息"),
	VIDEO("video", null, "视频消息"), 
	SHORTVIDEO("shortvideo", null, "小视频消息"),
	LOCATION("location", null, "地理位置消息"),
	LINK("link", null, "链接消息"),
	EVENT_SUBSCRIBE("event", "subscribe", "关注/取消关注-订阅事件"),
	VENT_UNSUBSCRIBE("event", "unsubscribe", "关注/取消关注-取消订阅事件"),
	EVENT_SCAN_SUBSCRIBE("event", "subscribe", "扫描带参数二维码-用户未关注时，进行关注后的事件"),
	EVENT_SCAN("event", "scan", "扫描带参数二维码-用户已关注时的事件"),
	EVENT_LOCATION("event", "location", "上报地理位置事件"),
	EVENT_CLICK("event", "click", "自定义菜单-点击菜单拉取消息时的事件"),
	EVENT_VIEW("event", "view", "自定义菜单-点击菜单跳转链接时的事件");

	/**
	 * 消息类型构造函数
	 * @param msgType 消息类型
	 * @param remark 消息说明
	 */
	EntityEnum(String msgType, String event, String remark) {
		this.msgType = msgType;
		if (event.equals(null)) {
			this.event = "";
		} else {
			this.event = event;
		}
		this.remark = remark;
	}
	
	/** 消息类型 */
	private String msgType;
	/** 事件类型 */
	private String event;
	/** 消息说明 */
	private String remark;

	/**
	 * 取得消息类型
	 * @return 消息类型
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 取得事件类型
	 * @return 事件类型
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * 取得消息说明
	 * @return 消息说明
	 */
	public String getRemark() {
		return remark;
	}
	
}
