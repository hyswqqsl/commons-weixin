package com.ironside.weixin;

import javax.imageio.ImageReader;

import org.springframework.util.Assert;

import com.ironside.weixin.request.entity.EventClickEntity;
import com.ironside.weixin.request.entity.TextEntity;
import com.ironside.weixin.request.entity.VideoEntity;
import com.ironside.weixin.response.ResponseManager;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VoiceResponse;

/**
 * 测试用的POST处理器
 * @author 雪庭
 * @sine 1.0 at 2015年5月8日
 */
public class MyPostProcessor extends PostProcessorAdapter {
	
	/** 回复实体管理 */
	private ResponseManager responseManager;
	
	/**
	 * 屏蔽默认构造函数
	 */
	private MyPostProcessor() {
	
	}
	
	public MyPostProcessor(ResponseManager responseManager) {
		Assert.notNull(responseManager);
		this.responseManager = responseManager;
	}

	@Override
	public String postProcessText(TextEntity entity) {
		String fromUserName = entity.getFromUserName();
		String toUserName = entity.getToUserName();
		// 取得文本内容(花名)
		String flower = entity.getContent();
		// 花的介绍
		String flowerInfo;
		// 数据库查找
		flowerInfo = MyServiceImpl.findFlower(flower);
		// 以文本效应方式返回花的介绍
		TextResponse textResponse = this.responseManager.getTextResponse();
		textResponse.setFromUserName(toUserName);
		textResponse.setToUserName(fromUserName);
		textResponse.setContent(flowerInfo);
		String result = this.responseManager.responseToXml(textResponse);
		return result;
	}

	@Override
	public String postProcessEventClick(EventClickEntity entity) {
		String fromUserName = entity.getFromUserName();
		String toUserName = entity.getToUserName();		
		// 取得点击菜单的事件KEY值
		String eventKey = entity.getEventKey();
		AbstractBaseResponse response = null;
		String result;
		switch(eventKey) {
		case MyMessage.CLICK_TEXT:
			response = this.responseManager.getTextResponse();
			// 可在这里进行处理
			break;
		case MyMessage.CLICK_IMAGE:
			response = this.responseManager.getImageResponse();
			// 可在这里进行处理
			break;
		case MyMessage.CLICK_VOICE:
			response = this.responseManager.getVoiceResponse();
			// 可在这里进行处理
			break;
		default:
			
		}
		response.setFromUserName(toUserName);
		response.setToUserName(fromUserName);
		result = this.responseManager.responseToXml(response);
		return result;
	}
	
	@Override
	public String postProcessVideo(VideoEntity entity) {
		String fromUserName = entity.getFromUserName();
		String toUserName = entity.getToUserName();		
		TextResponse response = this.responseManager.getTextResponse();
		response.setFromUserName(toUserName);
		response.setToUserName(fromUserName);
		return this.responseManager.responseToXml(response);
	}

}
