package com.ironside.weixin.push.entity;

/**
 * 上报地理位置事件消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月8日
 */
public class EventLocationEntity extends AbstractBaseEntity {
	
	/** 对应xml中定义的'地理位置纬度'标识 */
	public final static String LATITUDE = "Latitude";
	/** 对应xml中定义的'地理位置经度'标识 */
	public final static String LONGITUDE = "Longitude";
	/** 对应xml中定义的'地理位置精度'标识 */
	public final static String PRECISION = "Precision";
	
	/** 地理位置纬度 */
	private String latitude;
	/** 地理位置经度 */
	private String longitude;
	/** 地理位置精度 */
	private String precision;
	
	/**
	 * 构造函数，设置类型
	 */
	public EventLocationEntity() {
		this.setMsgEnum(EntityEnum.EVENT_LOCATION);
	}
	
	/**
	 * 取得地理位置纬度
	 * @return 地理位置纬度
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * 设置地理位置纬度
	 * @param latitude 地理位置纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 取得地理位置经度
	 * @return 地理位置经度
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * 设置地理位置经度
	 * @param longitude 地理位置经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * 取得地理位置精度
	 * @return 地理位置精度
	 */
	public String getPrecision() {
		return precision;
	}
	
	/**
	 * 设置地理位置精度
	 * @param precision 地理位置精度
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
	/**
	 * 取得事件类型
	 * @return 事件类型
	 */
	public String getEvent() {
		return getMsgEnum().getEvent();
	}	

}
