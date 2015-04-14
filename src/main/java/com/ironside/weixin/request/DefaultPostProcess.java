package com.ironside.weixin.request;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.request.entity.AbstractBaseEntity;
import com.ironside.weixin.request.entity.EntityEnum;
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
import com.ironside.weixin.response.XmlParse;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public class DefaultPostProcess extends AbstractPostProcess {
	
	/** 消息处理器 */
	private IPostProcessor processor;
	private XmlParse xmlParse;

	public XmlParse getXmlParse() {
		return xmlParse;
	}

	public void setXmlParse(XmlParse xmlParse) {
		this.xmlParse = xmlParse;
	}		
	
	/**
	 * 构造函数，设置默认POST处理器
	 */
	public DefaultPostProcess() {
		this.processor = new DefaultPostProcessor();
	} 

	@Override
	AbstractBaseEntity analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		// 将postData解析成properties对象
		Properties properties = xmlParse.parseString(postData);
		// 解析properties对象，建立entity对象
		AbstractBaseEntity entry = doAnalyze(properties);
		return entry; 
	}

	/**
	 * 解析properties对象，建立entity对象
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体对象
	 */
	AbstractBaseEntity doAnalyze(Properties properties) {
		Assert.notNull(properties);
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		
		// 解析事件消息
		if (msgType.equals(EntityEnum.EVENT_CLICK.getMsgType())) {
			return doEventAnalyze(properties); 
		}
		// 解析普通消息
		return doMessageAnalyze(properties);
	}
	
	/**
	 * 解析事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体对象
	 */
	private AbstractBaseEntity doEventAnalyze(Properties properties) {
		String event = properties.getProperty(AbstractBaseEntity.EVENT);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		if (event.equals(EntityEnum.EVENT_SUBSCRIBE.getEvent()) && StringUtils.isEmpty(eventKey)) {
			return doEventSubscribeAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_UNSUBSCRIBE.getEvent()) && StringUtils.isEmpty(eventKey)) {
			return doEventUnSubscribeAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_SCAN_SUBSCRIBE.getEvent()) && StringUtils.isEmpty(eventKey)==false) {
			return doEventScanSubscribeAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_SCAN.getEvent())) {
			return doEventScanAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_LOCATION.getEvent())) {
			return doEventLocationAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_CLICK.getEvent())) {
			return doEventClickAnalyze(properties);
		}
		if (event.equals(EntityEnum.EVENT_VIEW.getEvent())) {
			return doEventViewAnalyze(properties);
		}
		throw new IllegalStateException(String.format("解析事件消息出错:(%s)事件类型未知", event));
	}

	/**
	 * 解析关注/取消关注-订阅事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doEventSubscribeAnalyze(Properties properties) {
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[FromUser]]></FromUserName>
		 <CreateTime>123456789</CreateTime>
		 <MsgType><![CDATA[event]]></MsgType>
		 <Event><![CDATA[subscribe]]></Event>
		 </xml>
		 */
		EventSubscribeEntity entity = new EventSubscribeEntity();
		doBaseAnalyze(properties, entity);
		
		return entity;
	}
	
	/**
	 * 解析关注/取消关注-取消订阅事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doEventUnSubscribeAnalyze(Properties properties) {
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[FromUser]]></FromUserName>
		 <CreateTime>123456789</CreateTime>
		 <MsgType><![CDATA[event]]></MsgType>
		 <Event><![CDATA[unsubscribe]]></Event>
		 </xml>
		 */
		EventUnSubscribeEntity entity = new EventUnSubscribeEntity();
		doBaseAnalyze(properties, entity);
		
		return entity;
	}

	/**
	 * 解析扫描带参数二维码-用户未关注时，进行关注后的事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doEventScanSubscribeAnalyze(Properties properties) {
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
		EventScanSubscribeEntity entity = new EventScanSubscribeEntity();
		doBaseAnalyze(properties, entity);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		Assert.hasText(eventKey);
		String ticket = properties.getProperty(EventScanSubscribeEntity.TICKET);
		Assert.hasText(ticket);
		
		entity.setEventKey(eventKey);
		entity.setTicket(ticket);
		
		return entity;
	}

	/**
	 * 解析扫描带参数二维码-用户已关注时的事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */	
	private AbstractBaseEntity doEventScanAnalyze(Properties properties) {
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
		EventScanEntity entity = new EventScanEntity();
		doBaseAnalyze(properties, entity);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		Assert.hasText(eventKey);
		String ticket = properties.getProperty(EventScanEntity.TICKET);
		Assert.hasText(ticket);
		
		entity.setEventKey(eventKey);
		entity.setTicket(ticket);
		
		return entity;
	}

	/**
	 * 解析上报地理位置事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */		
	private AbstractBaseEntity doEventLocationAnalyze(Properties properties) {
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
		EventLocationEntity entity = new EventLocationEntity();
		doBaseAnalyze(properties, entity);
		String latitude = properties.getProperty(EventLocationEntity.LATITUDE);
		Assert.hasText(latitude);
		String longitude = properties.getProperty(EventLocationEntity.LONGITUDE);
		Assert.hasText(longitude);
		String precision = properties.getProperty(EventLocationEntity.PRECISION);
		Assert.hasText(precision);
		
		entity.setLatitude(latitude);
		entity.setLongitude(longitude);
		entity.setPrecision(precision);
		
		return entity;
	}

	/**
	 * 解析自定义菜单-点击菜单拉取消息时的事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doEventClickAnalyze(Properties properties) {
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
		EventClickEntity entity = new EventClickEntity();
		doBaseAnalyze(properties, entity);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		Assert.hasText(eventKey);
		
		entity.setEventKey(eventKey);
		
		return entity;
	}

	/**
	 * 解析自定义菜单-点击菜单跳转链接时的事件消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doEventViewAnalyze(Properties properties) {
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
		EventViewEntity entity = new EventViewEntity();
		doBaseAnalyze(properties, entity);
		String eventKey = properties.getProperty(AbstractBaseEntity.EVENT_KEY);
		Assert.hasText(eventKey);
		
		entity.setEventKey(eventKey);
		
		return entity;
	}

	/**
	 * 解析普通消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体对象
	 */
	private AbstractBaseEntity doMessageAnalyze(Properties properties) {
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		if (msgType.equals(EntityEnum.TEXT.getMsgType())) {
			return doTextAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.IMAGE.getMsgType())) {
			return doImageAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VOICE.getMsgType())) {
			return doVoiceAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VIDEO.getMsgType())) {
			return doVideoAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.SHORTVIDEO.getMsgType())) {
			return doShortVideoAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.LOCATION.getMsgType())) {
			return doLocationAnalyze(properties);
		}		
		if (msgType.equals(EntityEnum.LINK.getMsgType())) {
			return doLinkAnalyze(properties);
		}
		throw new IllegalStateException(String.format("解析普通消息出错:(%s)消息类型未知", msgType));
	}
	
	/**
	 * 基础解析
	 * @param properties POST推送数据解析后的properties
	 * @param entity 用于基础解析的实体
	 */
	private void doBaseAnalyze(Properties properties, AbstractBaseEntity entity) {
		String toUserName = properties.getProperty(TextEntity.TO_USER_NAME);
		Assert.hasText(toUserName);
		String fromUserName = properties.getProperty(TextEntity.FORM_USER_NAME);
		Assert.hasText(fromUserName);
		String createTimeStr = properties.getProperty(TextEntity.CREATE_TIME);
		Assert.hasText(createTimeStr);
		// 将时间整形转换为对象
		Date createTime = new Date(Long.parseLong(createTimeStr));
		Assert.notNull(createTime);
		
		entity.setToUserName(toUserName);
		entity.setFromUserName(fromUserName);
		entity.setCreateTime(createTime);
	}

	/**
	 * 解析普通文本消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doTextAnalyze(Properties properties) {
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
		TextEntity entity = new TextEntity();
		doBaseAnalyze(properties, entity);
		String content = properties.getProperty(TextEntity.CONTENT);
		Assert.hasText(content);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);		

		entity.setContent(content);
		entity.setMsgId(msgId);
		
		return entity;
	}
	
	/**
	 * 解析普通图片消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doImageAnalyze(Properties properties) {
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
		ImageEntity entity = new ImageEntity();
		doBaseAnalyze(properties, entity);
		String picUrl = properties.getProperty(ImageEntity.PIC_URL);
		Assert.hasText(picUrl);
		String mediaId = properties.getProperty(ImageEntity.MEDIA_ID);
		Assert.hasText(mediaId);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);				

		entity.setPicUrl(picUrl);
		entity.setMediaId(mediaId);
		entity.setMsgId(msgId);
		
		return entity;
	}

	/**
	 * 解析普通语音消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doVoiceAnalyze(Properties properties) {
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
		VoiceEntity entity = new VoiceEntity();
		doBaseAnalyze(properties, entity);
		String mediaId = properties.getProperty(VoiceEntity.MEDIA_ID);
		Assert.hasText(mediaId);
		String format = properties.getProperty(VoiceEntity.FORMAT);
		Assert.hasText(format);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);				
		
		entity.setMediaId(mediaId);
		entity.setFormat(format);
		entity.setMsgId(msgId);
		
		return entity;
	}	
	
	/**
	 * 解析普通视频消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doVideoAnalyze(Properties properties) {
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
		VideoEntity entity = new VideoEntity();
		doBaseAnalyze(properties, entity);
		String mediaId = properties.getProperty(VideoEntity.MEDIA_ID);
		Assert.hasText(mediaId);
		String thumbMediaId = properties.getProperty(VideoEntity.THUMB_MEDIA_ID);
		Assert.hasText(thumbMediaId);
		String msgId = properties.getProperty(VideoEntity.MSG_ID);
		Assert.hasText(msgId);				
		
		entity.setMediaId(mediaId);
		entity.setThumbMediaId(thumbMediaId);
		entity.setMsgId(msgId);
		
		return entity;
	}

	/**
	 * 解析普通小视频消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doShortVideoAnalyze(Properties properties) {
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
		ShortVideoEntity entity = new ShortVideoEntity();
		doBaseAnalyze(properties, entity);
		String mediaId = properties.getProperty(VideoEntity.MEDIA_ID);
		Assert.hasText(mediaId);
		String thumbMediaId = properties.getProperty(VideoEntity.THUMB_MEDIA_ID);
		Assert.hasText(thumbMediaId);
		String msgId = properties.getProperty(VideoEntity.MSG_ID);
		Assert.hasText(msgId);				
		
		entity.setMediaId(mediaId);
		entity.setThumbMediaId(thumbMediaId);
		entity.setMsgId(msgId);
		
		return entity;
	}	

	/**
	 * 解析普通位置消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doLocationAnalyze(Properties properties) {
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
		LocationEntity entity = new LocationEntity();
		doBaseAnalyze(properties, entity);
		String locationX = properties.getProperty(LocationEntity.LOCATION_X);
		Assert.hasText(locationX);
		String locationY = properties.getProperty(LocationEntity.LOCATION_Y);
		Assert.hasText(locationY);
		String scale = properties.getProperty(LocationEntity.SCALE);
		Assert.hasText(scale);
		String label = properties.getProperty(LocationEntity.LABEL);
		Assert.hasText(label);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);
		
		entity.setLocationX(locationX);
		entity.setLocationY(locationY);
		entity.setScale(scale);
		entity.setLabel(label);
		entity.setMsgId(msgId);
		
		return entity;
	}

	/**
	 * 解析普通链接消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体
	 */
	private AbstractBaseEntity doLinkAnalyze(Properties properties) {
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
		 <CreateTime>1351776360</CreateTime>
		 <MsgType><![CDATA[link]]></MsgType>
		 <Title><![CDATA[公众平台官网链接]]></Title>
		 <Description><![CDATA[公众平台官网链接]]></Description>
		 <Url><![CDATA[url]]></Url>
		 <MsgId>1234567890123456</MsgId>
		 </xml> 
		 */
		LinkEntity entity = new LinkEntity();
		doBaseAnalyze(properties, entity);
		String title = properties.getProperty(LinkEntity.TITLE);
		Assert.hasText(title);
		String description = properties.getProperty(LinkEntity.DESCRIPTION);
		Assert.hasText(description);
		String url = properties.getProperty(LinkEntity.URL);
		Assert.hasText(url);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);
		
		entity.setTitle(title);
		entity.setDescription(description);
		entity.setUrl(url);
		entity.setMsgId(msgId);
		
		return entity;
	}

	@Override
	String process(AbstractBaseEntity entity) {
		switch(entity.getMsgEnum()) {
		case TEXT:
			return this.processor.postProcessText((TextEntity)entity);
		case IMAGE:
			return this.processor.postProcessImage((ImageEntity)entity);
		case VOICE:
			return this.processor.postProcessVoice((VoiceEntity)entity);
		case VIDEO:
			return this.processor.postProcessVideo((VideoEntity)entity);
		case SHORTVIDEO:
			return this.processor.postProcessShortVideo((ShortVideoEntity)entity);
		case LOCATION:
			return this.processor.postProcessLocation((LocationEntity)entity);
		case LINK:
			return this.processor.postProcessLink((LinkEntity)entity);
		case EVENT_SUBSCRIBE:
			return this.processor.postProcessEventSubscribe((EventSubscribeEntity)entity);
		case EVENT_UNSUBSCRIBE:
			return this.processor.postProcessEventUnSubscribe((EventUnSubscribeEntity)entity);
		case EVENT_SCAN_SUBSCRIBE:
			return this.processor.postProcessEventScanSubscribe((EventScanSubscribeEntity)entity);
		case EVENT_SCAN:
			return this.processor.postProcessEventScan((EventScanEntity)entity);
		case EVENT_LOCATION:
			return this.processor.postProcessEventLocation((EventLocationEntity)entity);
		case EVENT_CLICK:
			return this.processor.postProcessEventClick((EventClickEntity)entity);
		case EVENT_VIEW:
			return this.processor.postProcessEventView((EventViewEntity)entity);
		default:
			throw new IllegalStateException(String.format("处理实体异常-实体不是预期的类型(%s)", entity.getMsgType()));
		}
	}

	/**
	 * 取得信息处理器
	 * @return 处理器
	 */
	public IPostProcessor getProcessor() {
		return processor;
	}

	/**
	 * 设置信息处理器
	 * @param processor 处理器
	 */
	public void setProcessor(IPostProcessor processor) {
		this.processor = processor;
	}

}
