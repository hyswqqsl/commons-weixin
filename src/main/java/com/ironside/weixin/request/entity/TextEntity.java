package com.ironside.weixin.request.entity;


/**
 * 文本消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月6日
 */
public class TextEntity extends AbstractBaseEntity{
	
	/** 对应xml中定义的'文本消息内容'标识 */
	public final static String CONTENT = "Content";

	/** 文本消息内容 */
	private String content;
	/** 消息id，64位整型 */
	private String msgId;

	/**
	 * 构造函数，设置类型
	 */
	public TextEntity() {
		this.setMsgEnum(EntityEnum.TEXT);
	}
	
	/**
	 * 取得文本消息内容
	 * @return 文本消息内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置文本消息内容
	 * @param content 文本消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 取得消息id
	 * @return 消息id
	 */
	public String getMsgId() {
		return msgId;
	}
	
	/**
	 * 设置消息id
	 * @param msgId 消息id
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}
