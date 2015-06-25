package org.flysic.commons.weixin.passive.request.entity;

/**
 * 自定义菜单-点击菜单拉取消息时的事件消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月8日
 */
public class EventMenuClickEntity extends AbstractBaseEvent {

	/** 事件KEY值 */
	private String EventKey;

	/**
	 * 取得事件KEY值
	 * @return 事件KEY值
	 */
	public String getEventKey() {
		return EventKey;
	}
	
	/**
	 * 设置事件KEY值
	 * @param eventKey 事件KEY值
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
}
