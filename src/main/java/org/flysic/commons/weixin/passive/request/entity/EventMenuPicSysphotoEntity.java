package org.flysic.commons.weixin.passive.request.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 自定义菜单-弹出系统拍照发图的事件消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年6月13日
 */
public class EventMenuPicSysphotoEntity extends EventMenuClickEntity {
	
	/** 发送的图片信息 */
	private SendPicsInfo SendPicsInfo;
	
	public SendPicsInfo getSendPicsInfo() {
		return SendPicsInfo;
	}

	public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}

	/** 发送的图片信息 */
	public class SendPicsInfo {
		
		/** 发送的图片数量 */
		private int Count;
		/** 图片列表 */
		@XStreamImplicit		
		private List<item> PicList;
		
		public int getCount() {
			return Count;
		}

		public void setCount(int count) {
			Count = count;
		}

		public List<item> getPicList() {
			return PicList;
		}

		public void setPicList(List<item> picList) {
			PicList = picList;
		}
		
		/**  */
		public class item {
			
			/** 图片的MD5值，开发者若需要，可用于验证接收到图片 */
			private String PicMd5Sum;

			public String getPicMd5Sum() {
				return PicMd5Sum;
			}

			public void setPicMd5Sum(String picMd5Sum) {
				PicMd5Sum = picMd5Sum;
			}

		}
	}
}
