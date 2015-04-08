package com.ironside.weixin.push.entity;

/**
 * 扫描带参数二维码-用户未关注时，进行关注后的事件消息实体 
 * @author ZXJ
 * @sine 1.0 at 2015年4月8日
 */
public class EventScanSubscribeEntity extends AbstractBaseEntity {

	/** 对应xml中定义的'二维码的ticket'标识 */
	public final static String TICKET = "Ticket";
	
	/** 事件KEY值，qrscene_为前缀，后面为二维码的参数值 */
	private String eventKey;
	/** 二维码的ticket，可用来换取二维码图片 */
	private String ticket;
	
	/**
	 * 构造函数，设置类型
	 */
	public EventScanSubscribeEntity() {
		this.setMsgEnum(EntityEnum.EVENT_SCAN_SUBSCRIBE);
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
	
	/**
	 * 取得二维码的ticket
	 * @return 二维码的ticket
	 */
	public String getTicket() {
		return ticket;
	}
	
	/**
	 * 设置二维码的ticket
	 * @param ticket 二维码的ticket
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
