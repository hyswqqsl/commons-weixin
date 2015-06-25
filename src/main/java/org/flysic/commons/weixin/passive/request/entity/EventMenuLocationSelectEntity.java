package org.flysic.commons.weixin.passive.request.entity;

/**
 * 自定义菜单-弹出地理位置选择器的事件消息实体
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年6月15日
 */
public class EventMenuLocationSelectEntity extends EventMenuClickEntity {
	
	/** 发送的位置信息 */
	private SendLocationInfo SendLocationInfo;
	
	public SendLocationInfo getSendLocationInfo() {
		return SendLocationInfo;
	}
	
	public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}

	/** 发送的位置信息 */
	public class SendLocationInfo {
		/** X坐标信息 */
		private int Location_X;
		/** Y坐标信息 */
		private int Location_Y;
		/** 精度，可理解为精度或者比例尺、越精细的话 scale越高 */
		private int Scale;
		/** 地理位置的字符串信息 */
		private String Label;
		/** 朋友圈POI的名字，可能为空 */
		private String Poiname;
		
		public int getLocation_X() {
			return Location_X;
		}
		
		public void setLocation_X(int location_X) {
			Location_X = location_X;
		}
		
		public int getLocation_Y() {
			return Location_Y;
		}
		
		public void setLocation_Y(int location_Y) {
			Location_Y = location_Y;
		}
		
		public int getScale() {
			return Scale;
		}
		
		public void setScale(int scale) {
			Scale = scale;
		}
		
		public String getLabel() {
			return Label;
		}
		
		public void setLabel(String label) {
			Label = label;
		}
		
		public String getPoiname() {
			return Poiname;
		}
		
		public void setPoiname(String poiname) {
			Poiname = poiname;
		}
		
	}
	
	

}
