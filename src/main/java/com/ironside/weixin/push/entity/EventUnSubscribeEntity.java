package com.ironside.weixin.push.entity;

/**
 * 关注/取消关注-取消订阅事件消息实体 
 * @author 雪庭
 * @sine 1.0 at 2015年4月8日
 */
public class EventUnSubscribeEntity extends AbstractBaseEntity {

	/**
	 * 构造函数，设置类型
	 */
	public EventUnSubscribeEntity() {
		this.setMsgEnum(EntityEnum.EVENT_UNSUBSCRIBE);
	}
	
	/**
	 * 取得事件类型
	 * @return 事件类型
	 */
	public String getEvent() {
		return getMsgEnum().getEvent();
	}	
	
}
