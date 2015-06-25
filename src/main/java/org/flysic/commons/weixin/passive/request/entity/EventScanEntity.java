package org.flysic.commons.weixin.passive.request.entity;

/**
 * 扫描带参数二维码-用户已关注时的事件消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月8日
 */
public class EventScanEntity extends EventMenuClickEntity {
	
	/** 二维码的ticket，可用来换取二维码图片 */
	private String Ticket;
	
	/**
	 * 取得二维码的ticket
	 * @return 二维码的ticket
	 */
	public String getTicket() {
		return Ticket;
	}
	
	/**
	 * 设置二维码的ticket
	 * @param ticket 二维码的ticket
	 */
	public void setTicket(String ticket) {
		this.Ticket = ticket;
	}
		
}
