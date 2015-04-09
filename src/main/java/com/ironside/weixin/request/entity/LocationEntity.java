package com.ironside.weixin.request.entity;

/**
 * 位置消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年4月7日
 */
public class LocationEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'地理位置维度'标识 */
	public final static String LOCATION_X = "Location_X";
	/** 对应xml中定义的'地理位置经度'标识 */
	public final static String LOCATION_Y = "Location_Y";
	/** 对应xml中定义的'地图缩放大小'标识 */
	public final static String SCALE = "Scale";
	/** 对应xml中定义的'地理位置信息'标识 */
	public final static String LABEL = "Label";

	/** 地理位置维度 */
	private String locationX;
	/**	地理位置经度 */
	private String locationY;
	/** 地图缩放大小 */
	private String scale;
	/** 地理位置信息 */
	private String label;
	/** 消息id，64位整型 */
	private String msgId;
	
	/**
	 * 构造函数，设置类型
	 */
	public LocationEntity() {
		this.setMsgEnum(EntityEnum.LOCATION);		
	}
	
	/**
	 * 取得地理位置维度
	 * @return 地理位置维度
	 */
	public String getLocationX() {
		return locationX;
	}
	
	/**
	 * 设置地理位置维度
	 * @param locationX 地理位置维度
	 */
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	
	/**
	 * 取得地理位置经度
	 * @return 地理位置经度
	 */	
	public String getLocationY() {
		return locationY;
	}
	
	/**
	 * 设置地理位置经度
	 * @param locationY 地理位置经度
	 */	
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	
	/**
	 * 取得地图缩放大小
	 * @return 地图缩放大小
	 */	
	public String getScale() {
		return scale;
	}
	
	/**
	 * 设置地图缩放大小
	 * @param scale 地图缩放大小
	 */	
	public void setScale(String scale) {
		this.scale = scale;
	}
	
	/**
	 * 取得地理位置信息
	 * @return 地理位置信息
	 */	
	public String getLabel() {
		return label;
	}
	
	/**
	 * 设置地理位置信息
	 * @param label 地理位置信息
	 */	
	public void setLabel(String label) {
		this.label = label;
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
