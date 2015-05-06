package com.ironside.weixin.request;

import java.util.Date;
import java.util.Properties;

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
	/** xml解析对象 */
	private XStream xStream;

	/**
	 * 构造函数，设置默认POST处理器
	 */
	public DefaultPostProcess() {
		this.processor = new PostProcessorAdapter();
		this.xStream = new XStream();
	}

	@Override
	AbstractBaseEntity analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		XmlParse xmlParse = new XmlParse();
		Properties properties = xmlParse.parseString(postData);
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		// 如果是事件消息
		if (msgType.equals(EntityEnum.EVENT_CLICK.getMsgType())) {
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
		switch (event) {
		case EntityEnum.EVENT_SUBSCRIBE.getEvent() :
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
			} else {
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
			}
			break;
		case EntityEnum.EVENT_UNSUBSCRIBE.getEvent() :
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
		case EntityEnum.EVENT_SCAN.getEvent() :
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
		case EntityEnum.EVENT_LOCATION.getEvent() :
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
		case EntityEnum.EVENT_CLICK.getEvent() :
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
		case EntityEnum.EVENT_VIEW.getEvent() :
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
	 * 
	 * @param postData POST方式推送的数据
	 * 
	 * @return 解析后的实体(抽象)
	 */
	private AbstractBaseEntity doMessageAnalyze(Properties properties, String postData) {
		String msgType = properties.getProperty(AbstractBaseEntity.MSG_TYPE);
		switch (msgType) {
		case EntityEnum.TEXT.getMsgType() :
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
		case EntityEnum.IMAGE.getMsgType() :
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
		case EntityEnum.VOICE.getMsgType() :
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
		case EntityEnum.VIDEO.getMsgType() :
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
		case EntityEnum.SHORTVIDEO.getMsgType() :
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
		case EntityEnum.LOCATION.getMsgType() :
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
		case EntityEnum.LINK.getMsgType() :
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
		switch (entity.getMsgEnum()) {
		case TEXT:
			return this.processor.postProcessText((TextEntity) entity);
		case IMAGE:
			return this.processor.postProcessImage((ImageEntity) entity);
		case VOICE:
			return this.processor.postProcessVoice((VoiceEntity) entity);
		case VIDEO:
			return this.processor.postProcessVideo((VideoEntity) entity);
		case SHORTVIDEO:
			return this.processor
					.postProcessShortVideo((ShortVideoEntity) entity);
		case LOCATION:
			return this.processor.postProcessLocation((LocationEntity) entity);
		case LINK:
			return this.processor.postProcessLink((LinkEntity) entity);
		case EVENT_SUBSCRIBE:
			return this.processor
					.postProcessEventSubscribe((EventSubscribeEntity) entity);
		case EVENT_UNSUBSCRIBE:
			return this.processor
					.postProcessEventUnSubscribe((EventUnSubscribeEntity) entity);
		case EVENT_SCAN_SUBSCRIBE:
			return this.processor
					.postProcessEventScanSubscribe((EventScanSubscribeEntity) entity);
		case EVENT_SCAN:
			return this.processor
					.postProcessEventScan((EventScanEntity) entity);
		case EVENT_LOCATION:
			return this.processor
					.postProcessEventLocation((EventLocationEntity) entity);
		case EVENT_CLICK:
			return this.processor
					.postProcessEventClick((EventClickEntity) entity);
		case EVENT_VIEW:
			return this.processor
					.postProcessEventView((EventViewEntity) entity);
		default:
			throw new IllegalStateException(String.format(
					"处理实体异常-实体不是预期的类型(%s)", entity.getMsgType()));
		}
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
