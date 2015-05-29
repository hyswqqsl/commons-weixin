package com.ironside.weixin.passive.request.entity;

/**
 * 上报地理位置事件消息实体
 * @author ZXJ
 * @sine 1.0 at 2015年4月8日
 */
public class EventLocationEntity extends AbstractBaseEvent {
	
	/** 地理位置纬度 */
	private String Latitude;
	/** 地理位置经度 */
	private String Longitude;
	/** 地理位置精度 */
	private String Precision;
	
	/**
	 * 取得地理位置纬度
	 * @return 地理位置纬度
	 */
	public String getLatitude() {
		return Latitude;
	}
	
	/**
	 * 设置地理位置纬度
	 * @param latitude 地理位置纬度
	 */
	public void setLatitude(String latitude) {
		this.Latitude = latitude;
	}
	
	/**
	 * 取得地理位置经度
	 * @return 地理位置经度
	 */
	public String getLongitude() {
		return Longitude;
	}
	
	/**
	 * 设置地理位置经度
	 * @param longitude 地理位置经度
	 */
	public void setLongitude(String longitude) {
		this.Longitude = longitude;
	}
	
	/**
	 * 取得地理位置精度
	 * @return 地理位置精度
	 */
	public String getPrecision() {
		return Precision;
	}
	
	/**
	 * 设置地理位置精度
	 * @param precision 地理位置精度
	 */
	public void setPrecision(String precision) {
		this.Precision = precision;
	}

}
