package com.ironside.weixin.push.entity;

/**
 * 小视频消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月7日
 */
public class ShortVideoEntity extends VideoEntity {

	/**
	 * 构造函数，设置类型
	 */
	public ShortVideoEntity() {
		this.setMsgEnum(EntityEnum.SHORTVIDEO);
	}
}
