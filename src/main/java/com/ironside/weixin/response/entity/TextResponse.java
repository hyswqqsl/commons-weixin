package com.ironside.weixin.response.entity;

/**
 * 文本回复实体类
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class TextResponse extends AbstractBaseResponse {
	 
	/** 对应xml中定义的'文本消息内容'标识 */
	public final static String CONTENT = "Content";

	/** 文本消息内容 */
	private String content;	

	/**
	 * 构造函数，设置类型
	 */
	public TextResponse() {
		this.setMsgEnum(ResponseEnum.TEXT);
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
	
}
