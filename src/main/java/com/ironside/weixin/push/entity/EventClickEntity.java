package com.ironside.weixin.push.entity;

/**
 * 自定义菜单-点击菜单拉取消息时的事件消息实体 
 * @author 雪庭
 * @sine at 2015年4月8日
 */
public class EventClickEntity extends AbstractBaseEntity {

	/** 事件KEY值，qrscene_为前缀，后面为二维码的参数值 */
	private String eventKey;
	
	/**
	 * 构造函数，设置类型
	 */
	public EventClickEntity() {
		this.setMsgEnum(EntityEnum.EVENT_CLICK);
	}
	
	/**
	 * 取得事件KEY值
	 * @return 事件KEY值
	 */
	public String getEventKey() {
		return eventKey;
	}
	
	/**
	 * 设置事件KEY值
	 * @param eventKey 事件KEY值
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}	
}
