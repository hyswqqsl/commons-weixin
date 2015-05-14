package com.ironside.weixin.response.entity;

/**
 * 文本回复实体类，具备克隆功能
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class TextResponse extends AbstractBaseResponse implements Cloneable {
	
	/** 文本消息内容 */
	private String Content;	

	/**
	 * 取得文本消息内容
	 * @return 文本消息内容
	 */
	public String getContent() {
		return Content;
	}
	
	/**
	 * 设置文本消息内容
	 * @param content 文本消息内容
	 */
	public void setContent(String content) {
		this.Content = content;
	}

	@Override
	public TextResponse clone() {
		Object cloneObject = null;
		try {
			cloneObject = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (TextResponse)cloneObject;
	}	
	
}
