package com.ironside.weixin;

import com.ironside.weixin.request.entity.EventClickEntity;
import com.ironside.weixin.request.entity.EventLocationEntity;
import com.ironside.weixin.request.entity.EventScanEntity;
import com.ironside.weixin.request.entity.EventScanSubscribeEntity;
import com.ironside.weixin.request.entity.EventSubscribeEntity;
import com.ironside.weixin.request.entity.EventUnSubscribeEntity;
import com.ironside.weixin.request.entity.EventViewEntity;
import com.ironside.weixin.request.entity.ImageEntity;
import com.ironside.weixin.request.entity.LinkEntity;
import com.ironside.weixin.request.entity.LocationEntity;
import com.ironside.weixin.request.entity.ShortVideoEntity;
import com.ironside.weixin.request.entity.TextEntity;
import com.ironside.weixin.request.entity.VideoEntity;
import com.ironside.weixin.request.entity.VoiceEntity;
import com.ironside.weixin.response.ResponseManager;

/**
 * POST处理器缺省适配模式
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class PostProcessorAdapter implements IPostProcessor {
	
	/** 默认回复消息 */
	protected final String result = "success";
	/** 回复实体管理 */
	protected ResponseManager responseManager;
	
	public PostProcessorAdapter() {
		this.responseManager = new ResponseManager();
	}

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
