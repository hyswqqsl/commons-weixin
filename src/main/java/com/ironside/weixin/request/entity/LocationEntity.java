package com.ironside.weixin.request.entity;

/**
 * 位置消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月7日
 */
public class LocationEntity extends AbstractBaseEntity {
	
	/** 地理位置维度 */
	private String LocationX;
	/**	地理位置经度 */
	private String LocationY;
	/** 地图缩放大小 */
	private String Scale;
	/** 地理位置信息 */
	private String Label;
	/** 消息id，64位整型 */
	private String MsgId;
	
	/**
	 * 取得地理位置维度
	 * @return 地理位置维度
	 */
	public String getLocationX() {
		return LocationX;
	}
	
	/**
	 * 设置地理位置维度
	 * @param locationX 地理位置维度
	 */
	public void setLocationX(String locationX) {
		this.LocationX = locationX;
	}
	
	/**
	 * 取得地理位置经度
	 * @return 地理位置经度
	 */	
	public String getLocationY() {
		return LocationY;
	}
	
	/**
	 * 设置地理位置经度
	 * @param locationY 地理位置经度
	 */	
	public void setLocationY(String locationY) {
		this.LocationY = locationY;
	}
	
	/**
	 * 取得地图缩放大小
	 * @return 地图缩放大小
	 */	
	public String getScale() {
		return Scale;
	}
	
	/**
	 * 设置地图缩放大小
	 * @param scale 地图缩放大小
	 */	
	public void setScale(String scale) {
		this.Scale = scale;
	}
	
	/**
	 * 取得地理位置信息
	 * @return 地理位置信息
	 */	
	public String getLabel() {
		return Label;
	}
	
	/**
	 * 设置地理位置信息
	 * @param label 地理位置信息
	 */	
	public void setLabel(String label) {
		this.Label = label;
	}
	
	/**
	 * 取得消息id
	 * @return 消息id
	 */	
	public String getMsgId() {
		return MsgId;
	}
	
	/**
	 * 设置消息id
	 * @param msgId 消息id
	 */	
	public void setMsgId(String msgId) {
		this.MsgId = msgId;
	}
	
}
