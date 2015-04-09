package com.ironside.weixin.push.entity;

/**
 * 关注/取消关注-订阅事件消息实体 
 * @author 雪庭
 * @sine 1.0 at 2015年4月8日
 */
public class EventSubscribeEntity extends AbstractBaseEntity {

	public EventSubscribeEntity() {
		this.setMsgEnum(EntityEnum.EVENT_SUBSCRIBE);
	}
	
	/**
	 * 取得事件类型
	 * @return 事件类型
	 */
	public String getEvent() {
		return getMsgEnum().getEvent();
	}	
}
