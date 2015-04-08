package com.ironside.weixin.push.entity;

/**
 * 扫描带参数二维码-用户已关注时的事件消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月8日
 */
public class EventScanEntity extends EventScanSubscribeEntity {
	
	/**
	 * 构造函数，设置类型
	 */
	public EventScanEntity(){
		this.setMsgEnum(EntityEnum.EVENT_SCAN);
	}

}
