package com.ironside.weixin.push.entity;

/**
 * 自定义菜单-点击菜单跳转链接时的事件消息实体 
 * @author 雪庭
 * @sine 1.0 at 2015年4月8日
 */
public class EventViewEntity extends EventClickEntity {

	/**
	 * 构造函数，设置类型
	 */
	public EventViewEntity() {
		this.setMsgEnum(EntityEnum.EVENT_VIEW);
	}
}
