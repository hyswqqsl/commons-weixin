package com.ironside.weixin.request;

import java.util.Properties;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.request.entity.AbstractBaseEntity;
import com.ironside.weixin.request.entity.EntityType;
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
import com.thoughtworks.xstream.XStream;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * 
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public class DefaultPostProcess extends AbstractPostProcess {

	/** 消息处理器 */
	private IPostProcessor processor;

	/**
	 * 构造函数，设置默认POST处理器
	 */
	public DefaultPostProcess() {
		this.processor = new PostProcessorAdapter();
	}

	@Override
	AbstractBaseEntity analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		XmlParse xmlParse = new XmlParse();
		Properties properties = xmlParse.parseString(postData);
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		// 如果是事件消息
		if (msgType.equals(EntityType.EVENT)) {
			return doEventAnalyze(properties, postData);
		}
		// 如果是普通消息
		return doMessageAnalyze(properties, postData);
	}

	/**
	 * 解析事件消息
	 * 
	 * @param properties
	 *            名字和值信息
	 * @param postData
	 *            POST方式推送的数据
	 * @return 解析后的实体(抽象)
	 */
	private AbstractBaseEntity doEventAnalyze(Properties properties, String postData) {
		String event = properties.getProperty(AbstractBaseEntity.EVENT);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		XStream xStream = new XStream();
		switch (event) {
		case EntityType.EVENT_SUBSCRIBE:
			if (StringUtils.isEmpty(eventKey)) {
				/*
				 <xml>
				 <ToUserName><![CDATA[toUser]]></ToUserName>
				 <FromUserName><![CDATA[FromUser]]></FromUserName>
				 <CreateTime>123456789</CreateTime>
				 <MsgType><![CDATA[event]]></MsgType>
				 <Event><![CDATA[subscribe]]></Event>
				 </xml>
				 */
				xStream.alias("xml", EventSubscribeEntity.class);
				break;
			}
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[FromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[subscribe]]></Event>
			 <EventKey><![CDATA[qrscene_123123]]></EventKey>
			 <Ticket><![CDATA[TICKET]]></Ticket>
			 </xml>
			 */
			xStream.alias("xml", EventScanSubscribeEntity.class);			
			break;
		case EntityType.EVENT_UNSUBSCRIBE:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[FromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[unsubscribe]]></Event>
			 </xml>
			 */
			xStream.alias("xml", EventUnSubscribeEntity.class);			
			break;
		case EntityType.EVENT_SCAN:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[FromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[SCAN]]></Event>
			 <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
			 <Ticket><![CDATA[TICKET]]></Ticket>
			 </xml>
			 */
			xStream.alias("xml", EventScanEntity.class);
			break;
		case EntityType.EVENT_LOCATION:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[fromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[LOCATION]]></Event>
			 <Latitude>23.137466</Latitude>
			 <Longitude>113.352425</Longitude>
			 <Precision>119.385040</Precision>
			 </xml>
			 */
			xStream.alias("xml", EventLocationEntity.class);
			break;
		case EntityType.EVENT_CLICK:
			/*
		  	 <xml>
		 	 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[FromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[CLICK]]></Event>
			 <EventKey><![CDATA[EVENTKEY]]></EventKey>
			 </xml>
			 */
			 xStream.alias("xml", EventClickEntity.class);
			 break;
		case EntityType.EVENT_VIEW:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[FromUser]]></FromUserName>
			 <CreateTime>123456789</CreateTime>
			 <MsgType><![CDATA[event]]></MsgType>
			 <Event><![CDATA[VIEW]]></Event>
		  	 <EventKey><![CDATA[www.qq.com]]></EventKey>
			 </xml>
			 */
			xStream.alias("xml", EventViewEntity.class);
			break;
		default :
			throw new IllegalStateException(String.format("解析事件消息出错:(%s)事件类型未知", event));
		}
		return (AbstractBaseEntity)xStream.fromXML(postData);
	}

	/*
	 * 解析事件消息
	 * 
	 * @param properties 名字和值信息
	 * @param postData POST方式推送的数据
	 * @return 解析后的实体(抽象)
	 */
	private AbstractBaseEntity doMessageAnalyze(Properties properties, String postData) {
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		XStream xStream = new XStream();
		switch (msgType) {
		case EntityType.TEXT:
			/*
		 	 <xml>
		     <ToUserName><![CDATA[toUser]]></ToUserName>
		 	 <FromUserName><![CDATA[fromUser]]></FromUserName> 
		 	 <CreateTime>1348831860</CreateTime>
		 	 <MsgType><![CDATA[text]]></MsgType>
		 	 <Content><![CDATA[this is a test]]></Content>
		 	 <MsgId>1234567890123456</MsgId>
		 	 </xml>
		 	 */ 
			xStream.alias("xml", TextEntity.class);			
			break;
		case EntityType.IMAGE:
			/*
			 <xml>
	 		 <ToUserName><![CDATA[toUser]]></ToUserName>
	 		 <FromUserName><![CDATA[fromUser]]></FromUserName>
	 		 <CreateTime>1348831860</CreateTime>
	 		 <MsgType><![CDATA[image]]></MsgType>
	 		 <PicUrl><![CDATA[this is a url]]></PicUrl>
	 		 <MediaId><![CDATA[media_id]]></MediaId>
	 		 <MsgId>1234567890123456</MsgId>
	 		 </xml>
			 */
			xStream.alias("xml", ImageEntity.class);
			break;
		case EntityType.VOICE:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[fromUser]]></FromUserName>
			 <CreateTime>1357290913</CreateTime>
			 <MsgType><![CDATA[voice]]></MsgType>
			 <MediaId><![CDATA[media_id]]></MediaId>
			 <Format><![CDATA[Format]]></Format>
			 <MsgId>1234567890123456</MsgId>
			 </xml>
			 */
			xStream.alias("xml", ImageEntity.class);
			break;
		case EntityType.VIDEO:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[fromUser]]></FromUserName>
			 <CreateTime>1357290913</CreateTime>
			 <MsgType><![CDATA[video]]></MsgType>
			 <MediaId><![CDATA[media_id]]></MediaId>
			 <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
			 <MsgId>1234567890123456</MsgId>
			 </xml>
			 */
			xStream.alias("xml", VideoEntity.class);
			break;
		case EntityType.SHORTVIDEO:
			/*
			 <xml>
			 <ToUserName><![CDATA[toUser]]></ToUserName>
			 <FromUserName><![CDATA[fromUser]]></FromUserName>
			 <CreateTime>1357290913</CreateTime>
			 <MsgType><![CDATA[shortvideo]]></MsgType>
			 <MediaId><![CDATA[media_id]]></MediaId>
			 <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
			 <MsgId>1234567890123456</MsgId>
			 </xml>
			 */
			xStream.alias("xml", ShortVideoEntity.class);
			break;
		case EntityType.LOCATION:
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
	  	 <CreateTime>1351776360</CreateTime>
		 <MsgType><![CDATA[location]]></MsgType>
		 <Location_X>23.134521</Location_X>
		 <Location_Y>113.358803</Location_Y>
		 <Scale>20</Scale>
		 <Label><![CDATA[位置信息]]></Label>
		 <MsgId>1234567890123456</MsgId>
		 </xml> 		
		 */
			xStream.alias("xml", LocationEntity.class);
			break;
		case EntityType.LINK:
			/*
			 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
			 * <FromUserName><![CDATA[fromUser]]></FromUserName>
			 * <CreateTime>1351776360</CreateTime>
			 * <MsgType><![CDATA[link]]></MsgType>
			 * <Title><![CDATA[公众平台官网链接]]></Title>
			 * <Description><![CDATA[公众平台官网链接]]></Description>
			 * <Url><![CDATA[url]]></Url> <MsgId>1234567890123456</MsgId> </xml>
			 */
			xStream.alias("xml", LinkEntity.class);
			break;
		default :
			throw new IllegalStateException(String.format("解析普通消息出错:(%s)消息类型未知", msgType));
		}
		return (AbstractBaseEntity)xStream.fromXML(postData);
	}

	@Override
	String process(AbstractBaseEntity entity) {
		if (entity.getMsgType().equals(EntityType.EVENT)) {
			return doProcessEvent(entity);
		}
		return doProcessMessage(entity);
	}
	
	/**
	 * 处理事件实体
	 * @param entity 事件实体
	 * @return
	 */
	private String doProcessEvent(AbstractBaseEntity entity) {
		String event = entity.getEvent();
		String eventKey = entity.getEventKey();
		String result;
		switch(event) {
		case EntityType.EVENT_SUBSCRIBE:
			if (StringUtils.isEmpty(eventKey)) {
				result = this.processor.postProcessEventSubscribe((EventSubscribeEntity) entity);
			} else {
				result = this.processor.postProcessEventScanSubscribe((EventScanSubscribeEntity)entity);
			}
			break;
		case EntityType.EVENT_UNSUBSCRIBE:
			result = this.processor.postProcessEventUnSubscribe((EventUnSubscribeEntity) entity);
			break;
		case EntityType.EVENT_SCAN:
			result = this.processor.postProcessEventScan((EventScanEntity) entity);
			break;
		case EntityType.EVENT_LOCATION:
			result = this.processor.postProcessEventLocation((EventLocationEntity) entity);
			break;
		case EntityType.EVENT_CLICK:
			result = this.processor.postProcessEventClick((EventClickEntity) entity);
			break;
		case EntityType.EVENT_VIEW:
			result = this.processor.postProcessEventView((EventViewEntity) entity);
			break;
		default:
			throw new IllegalStateException(String.format("处理事件实体异常-事件实体不是预期的类型: %s", event));
		}
		return result;
	}

	/**
	 * 处理消息实体
	 * @param entity 消息实体
	 * @return
	 */
	private String doProcessMessage(AbstractBaseEntity entity) {
		String msgType = entity.getMsgType();
		String result;
		switch (msgType)
		{
		case EntityType.TEXT:
			result = this.processor.postProcessText((TextEntity) entity);
			break;
		case EntityType.IMAGE:
			result = this.processor.postProcessImage((ImageEntity) entity);
			break;
		case EntityType.VOICE:
			result = this.processor.postProcessVoice((VoiceEntity) entity);
			break;
		case EntityType.VIDEO:
			result = this.processor.postProcessVideo((VideoEntity) entity);
			break;
		case EntityType.SHORTVIDEO:
			result = this.processor.postProcessShortVideo((ShortVideoEntity) entity);
			break;
		case EntityType.LOCATION:
			result = this.processor.postProcessLocation((LocationEntity) entity);
			break;
		case EntityType.LINK:
			result = this.processor.postProcessLink((LinkEntity) entity);
			break;
		default:
			throw new IllegalStateException(String.format("处理消息实体异常-消息实体不是预期的类型: %s", msgType));
		}
		return result;
	}

	/**
	 * 取得信息处理器
	 * 
	 * @return 处理器
	 */
	public IPostProcessor getProcessor() {
		return processor;
	}

	/**
	 * 设置信息处理器
	 * 
	 * @param processor
	 *            处理器
	 */
	public void setProcessor(IPostProcessor processor) {
		this.processor = processor;
	}

}
