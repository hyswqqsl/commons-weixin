package org.flysic.commons.weixin.passive;

import org.flysic.commons.weixin.passive.request.entity.EventLocationEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuClickEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuLocationSelectEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicPhotoOrAlbumEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicSysphotoEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicWeixinEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuScancodePushEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuScancodeWaitmsgEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuViewEntity;
import org.flysic.commons.weixin.passive.request.entity.EventScanEntity;
import org.flysic.commons.weixin.passive.request.entity.EventScanSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.EventSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.EventUnSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.ImageEntity;
import org.flysic.commons.weixin.passive.request.entity.LinkEntity;
import org.flysic.commons.weixin.passive.request.entity.LocationEntity;
import org.flysic.commons.weixin.passive.request.entity.ShortVideoEntity;
import org.flysic.commons.weixin.passive.request.entity.TextEntity;
import org.flysic.commons.weixin.passive.request.entity.VideoEntity;
import org.flysic.commons.weixin.passive.request.entity.VoiceEntity;

/**
 * 由用户实现的POST处理器，由POST处理程序调用。
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @since 1.0 at 2015年4月3日
 */
public interface IPostProcessor {
	
	/**
	 * 处理文本消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessText(TextEntity entity);
	
	/** 
	 * 处理图片消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessImage(ImageEntity entity);
	
	/** 
	 * 处理语音消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessVoice(VoiceEntity entity);
	
	/** 
	 * 处理视频消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessVideo(VideoEntity entity);
	
	/** 
	 * 处理小视频消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessShortVideo(ShortVideoEntity entity);
	
	/** 
	 * 处理地理位置消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessLocation(LocationEntity entity);
	
	/** 
	 * 处理链接消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessLink(LinkEntity entity);
	
	/** 
	 * 处理关注/取消关注-订阅事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventSubscribe(EventSubscribeEntity entity);
	
	/** 
	 * 处理关注/取消关注-取消订阅事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventUnSubscribe(EventUnSubscribeEntity entity);
	
	/** 
	 * 处理扫描带参数二维码-用户未关注时，进行关注后的事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventScanSubscribe(EventScanSubscribeEntity entity);
	
	/** 
	 * 处理扫描带参数二维码-用户已关注时的事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventScan(EventScanEntity entity);
	
	/** 
	 * 处理上报地理位置事件消息实体	 
	 * @param entity 实体
	 * @return 响应消息	
	 */
	String postProcessEventLocation(EventLocationEntity entity);
	
	/** 
	 * 处理自定义菜单-点击菜单拉取消息时的事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuClick(EventMenuClickEntity entity);
	
	/** 
	 * 处理自定义菜单-点击菜单跳转链接时的事件消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuView(EventMenuViewEntity entity);
	
	/**
	 * 处理自定义菜单-扫码推事件的事件推送消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuScancodePush(EventMenuScancodePushEntity entity);
	
	/**
	 * 处理自定义菜单-扫码推事件且弹出“消息接收中”提示框的事件推送消息实体 
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuScancodeWaitmsg(EventMenuScancodeWaitmsgEntity entity);
	
	/**
	 * 处理自定义菜单-弹出系统拍照发图的事件推送消息实体 
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuPicSysphoto(EventMenuPicSysphotoEntity entity);
	
	/**
	 * 处理自定义菜单-弹出拍照或者相册发图的事件推送消息实体 
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuPicPhotoOrAlbum(EventMenuPicPhotoOrAlbumEntity entity);
	
	/**
	 * 处理自定义菜单-弹出微信相册发图器的事件推送消息实体
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuPicWeixin(EventMenuPicWeixinEntity entity);
	
	/**
	 * 处理自定义菜单-弹出地理位置选择器的事件推送
	 * @param entity 实体
	 * @return 响应消息
	 */
	String postProcessEventMenuLocationSelect(EventMenuLocationSelectEntity entity);
}
