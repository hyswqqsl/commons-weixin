package com.ironside.weixin.passive;

import com.ironside.weixin.passive.request.entity.EventClickEntity;
import com.ironside.weixin.passive.request.entity.EventLocationEntity;
import com.ironside.weixin.passive.request.entity.EventScanEntity;
import com.ironside.weixin.passive.request.entity.EventScanSubscribeEntity;
import com.ironside.weixin.passive.request.entity.EventSubscribeEntity;
import com.ironside.weixin.passive.request.entity.EventUnSubscribeEntity;
import com.ironside.weixin.passive.request.entity.EventViewEntity;
import com.ironside.weixin.passive.request.entity.ImageEntity;
import com.ironside.weixin.passive.request.entity.LinkEntity;
import com.ironside.weixin.passive.request.entity.LocationEntity;
import com.ironside.weixin.passive.request.entity.ShortVideoEntity;
import com.ironside.weixin.passive.request.entity.TextEntity;
import com.ironside.weixin.passive.request.entity.VideoEntity;
import com.ironside.weixin.passive.request.entity.VoiceEntity;

/**
 * 由用户实现的POST处理器，由POST处理程序调用。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public interface IPostProcessor {
	
	/**
	 * 处理文本实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessText(TextEntity entity);
	
	/** 
	 * 处理图片实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessImage(ImageEntity entity);
	
	/** 
	 * 处理语音实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessVoice(VoiceEntity entity);
	
	/** 
	 * 处理视频实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessVideo(VideoEntity entity);
	
	/** 
	 * 处理小视频实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessShortVideo(ShortVideoEntity entity);
	
	/** 
	 * 处理地理位置实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessLocation(LocationEntity entity);
	
	/** 
	 * 处理链接实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessLink(LinkEntity entity);
	
	/** 
	 * 处理关注/取消关注-订阅事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventSubscribe(EventSubscribeEntity entity);
	
	/** 
	 * 处理关注/取消关注-取消订阅事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventUnSubscribe(EventUnSubscribeEntity entity);
	
	/** 
	 * 处理扫描带参数二维码-用户未关注时，进行关注后的事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventScanSubscribe(EventScanSubscribeEntity entity);
	
	/** 
	 * 处理扫描带参数二维码-用户已关注时的事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventScan(EventScanEntity entity);
	
	/** 
	 * 处理上报地理位置事件实体	 
	 * @param entity 实体
	 * @return 响应消息	
	 */
	String postProcessEventLocation(EventLocationEntity entity);
	
	/** 
	 * 处理自定义菜单-点击菜单拉取消息时的事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventClick(EventClickEntity entity);
	
	/** 
	 * 处理自定义菜单-点击菜单跳转链接时的事件实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventView(EventViewEntity entity);
}
