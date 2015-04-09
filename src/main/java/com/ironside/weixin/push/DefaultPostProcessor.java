package com.ironside.weixin.push;

import com.ironside.weixin.push.entity.EventClickEntity;
import com.ironside.weixin.push.entity.EventLocationEntity;
import com.ironside.weixin.push.entity.EventScanEntity;
import com.ironside.weixin.push.entity.EventScanSubscribeEntity;
import com.ironside.weixin.push.entity.EventSubscribeEntity;
import com.ironside.weixin.push.entity.EventUnSubscribeEntity;
import com.ironside.weixin.push.entity.EventViewEntity;
import com.ironside.weixin.push.entity.ImageEntity;
import com.ironside.weixin.push.entity.LinkEntity;
import com.ironside.weixin.push.entity.LocationEntity;
import com.ironside.weixin.push.entity.ShortVideoEntity;
import com.ironside.weixin.push.entity.TextEntity;
import com.ironside.weixin.push.entity.VideoEntity;
import com.ironside.weixin.push.entity.VoiceEntity;

/**
 * 默认POST处理器
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class DefaultPostProcessor implements IPostProcessor {
	
	/** 默认回复消息 */
	protected final String result = "success"; 

	public String postProcessText(TextEntity entity) {
		return result;
	}

	public String postProcessImage(ImageEntity entity) {
		return result;
	}

	public String postProcessVoice(VoiceEntity entity) {
		return result;
	}

	public String postProcessVideo(VideoEntity entity) {
		return result;
	}

	public String postProcessShortVideo(ShortVideoEntity entity) {
		return result;
	}

	public String postProcessLocation(LocationEntity entity) {
		return result;
	}

	public String postProcessLink(LinkEntity entity) {
		return result;
	}

	public String postProcessEventSubscribe(EventSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventUnSubscribe(EventUnSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventScanSubscribe(EventScanSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventScan(EventScanEntity entity) {
		return result;
	}

	public String postProcessEventLocation(EventLocationEntity entity) {
		return result;
	}

	public String postProcessEventClick(EventClickEntity entity) {
		return result;
	}

	public String postProcessEventView(EventViewEntity entity) {
		return result;
	}

}
