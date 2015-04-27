package com.ironside.weixin.response;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.ResponseEnum;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VoiceResponse;

/**
 * 回复实体管理
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class ResponseManager {

	/** 默认文本类型回复字符串 */
	/*
	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> 
	 * <MsgType><![CDATA[text]]></MsgType>
	 * <Content><![CDATA[你好]]></Content> 
	 * </xml>
	 */
	private final String DEFAULT_TEXT_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
	
	/** 默认图片类型回复字符串 */
	/*
 	 * <xml> 
	 * <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime>
	 * <MsgType><![CDATA[image]]></MsgType>
	 * <Image>
	 * <MediaId><![CDATA[media_id]]></MediaId>
	 * </Image>
	 * </xml>
	 */	
	private final String DEFAULT_IMAGE_XML_STRING = "<xml>	<ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[media_id]]></MediaId></Image></xml>";
	
	/** 默认语音类型回复字符串 */
	/*
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <Voice>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * </Voice>
     * </xml>
     */
	private final String DEFAULT_VOICE_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>12345678</CreateTime><MsgType><![CDATA[voice]]></MsgType><Voice><MediaId><![CDATA[media_id]]></MediaId></Voice></xml>";

	/** 文本类型回复xml文件 */
	private String textXmlFile;
	/** 图片类型回复xml文件 */
	private String imageXmlFile;
	/** 语音类型回复xml文件 */
	private String voiceXmlFile;

	/** xml解析对象 */
	private XmlParse xmlParse;
	
	/** 文本回复消息缓冲 */
	TextResponse textResponse;
	/** 图片回复消息缓冲 */
	ImageResponse imageResponse;
	/** 语音回复消息缓冲 */
	VoiceResponse voiceResponse;

	/**
	 * 取得文本类型回复xml文件
	 * 
	 * @return 文本类型回复xml文件
	 */
	public String getTextXmlFile() {
		return textXmlFile;
	}

	/**
	 * 设置文本类型回复xml文件
	 * 
	 * @param textXmlFile
	 *            文本类型回复xml文件
	 */
	public void setTextXmlFile(String textXmlFile) {
		Assert.hasText(textXmlFile);
		this.textXmlFile = textXmlFile;
		// 清空缓存
		this.textResponse = null;
	}

	/**
	 * 取得图片类型回复xml文件
	 * 
	 * @return 文本类型回复xml文件
	 */
	public String getImageXmlFile() {
		return imageXmlFile;
	}

	/**
	 * 设置图片类型回复xml文件
	 * 
	 * @param textXmlFile
	 *            图片类型回复xml文件
	 */	
	public void setImageXmlFile(String imageXmlFile) {
		Assert.hasText(imageXmlFile);
		this.imageXmlFile = imageXmlFile;
		// 清空缓存
		this.imageResponse = null;
	}
	
	/**
	 * 取得语音类型回复xml文件
	 * @return 语音类型回复xml文件
	 */
	public String getVoiceXmlFile() {
		return voiceXmlFile;
	}

	/**
	 * 设置语音类型回复xml文件
	 * @param voiceXmlFile 语音类型回复xml文件
	 */
	public void setVoiceXmlFile(String voiceXmlFile) {
		Assert.hasText(voiceXmlFile);
		this.voiceXmlFile = voiceXmlFile;
		// 清空缓存
		this.voiceResponse = null;		
	}

	/**
	 * 取得xml解析对象
	 * 
	 * @return xml解析对象
	 */
	public XmlParse getXmlParse() {
		return xmlParse;
	}

	/**
	 * 设置xml解析对象
	 * 
	 * @param xmlParse
	 *            xml解析对象
	 */
	public void setXmlParse(XmlParse xmlParse) {
		Assert.notNull(xmlParse);
		this.xmlParse = xmlParse;
	}

	/**
	 * 取得文本回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @return 文本回复实体
	 */
	public TextResponse getTextResponse() {
		if (this.textResponse == null) {
			this.textResponse = doGetTextResponse();
		}
		return this.textResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 
	 * 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 文本回复实体
	 */
	private TextResponse doGetTextResponse() {
		Properties properties;
		if (StringUtils.isEmpty(this.textXmlFile)) {
			properties = xmlParse.parseString(DEFAULT_TEXT_XML_STRING);
			return doTextResponse(properties);
		}
		URL url = ClassLoader.getSystemResource(this.textXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.textXmlFile = null;
			properties = xmlParse.parseString(DEFAULT_TEXT_XML_STRING);
			return doTextResponse(properties);
		}
		// 取得名字和值信息
		properties = xmlParse.parseXmlFile(this.textXmlFile);
		// 用完清楚xml文件，防止再次解析
		this.textXmlFile = null;
		// 根据名字和值对应生成对象
		return doTextResponse(properties);
	}

	/**
	 * 基础解析
	 * 
	 * @param properties
	 *            解析后的properties
	 * @param entity
	 *            用于基础解析的实体
	 */
	private void doBaseAnalyze(Properties properties,
			AbstractBaseResponse entity) {
		String toUserName = properties.getProperty(TextResponse.TO_USER_NAME);
		Assert.hasText(toUserName);
		String fromUserName = properties
				.getProperty(TextResponse.FORM_USER_NAME);
		Assert.hasText(fromUserName);
		String createTimeStr = properties.getProperty(TextResponse.CREATE_TIME);
		Assert.hasText(createTimeStr);
		// 将时间整形转换为对象
		Date createTime = new Date(Long.parseLong(createTimeStr));
		Assert.notNull(createTime);

		entity.setToUserName(toUserName);
		entity.setFromUserName(fromUserName);
		entity.setCreateTime(createTime);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param properties
	 *            xml名字和值对应
	 * @return 文本回复实体
	 */
	private TextResponse doTextResponse(Properties properties) {
		TextResponse entity = new TextResponse();
		doBaseAnalyze(properties, entity);
		String content = properties.getProperty(TextResponse.CONTENT);
		Assert.hasText(content);
		String msgType = properties.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.TEXT.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		entity.setContent(content);
		return entity;
	}

	/*
	 * 取得图片回复实体
	 * @return 图片回复实体
	 */
	public ImageResponse getImageResponse()
	{
		if (this.imageResponse == null) {
			this.imageResponse = doGetImageResponse();
		}
		return this.imageResponse;
	}
	
	/**
	 * 从xml文件中解析实体</br> 
	 * 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 图片回复实体
	 */	
	private ImageResponse doGetImageResponse() {
		Properties properties;
		if (StringUtils.isEmpty(this.imageXmlFile)) {
			properties = xmlParse.parseString(DEFAULT_IMAGE_XML_STRING);
			return doImageResponse(properties);
		}
		URL url = ClassLoader.getSystemResource(this.imageXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.imageXmlFile = null;
			properties = xmlParse.parseString(DEFAULT_IMAGE_XML_STRING);
			return doImageResponse(properties);
		}
		// 取得名字和值信息
		properties = xmlParse.parseXmlFile(this.imageXmlFile);
		// 用完清楚xml文件，防止再次解析
		this.imageXmlFile = null;		
		// 根据名字和值对应生成对象
		return doImageResponse(properties);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param properties
	 *            xml名字和值对应
	 * @return 图片回复实体
	 */
	private ImageResponse doImageResponse(Properties properties) {
		ImageResponse entity = new ImageResponse();
		doBaseAnalyze(properties, entity);
		String msgType = properties.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.IMAGE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));
		String mediaId = properties.getProperty(ImageResponse.MEDIA_ID);
		Assert.hasText(mediaId);

		entity.setMediaId(mediaId);
		return entity;
	}

	
	/**
	 * 取得语音回复实体
	 * @return 语音回复实体
	 */
	public VoiceResponse getVoiceResponse() {
		if (this.voiceResponse == null) {
			this.voiceResponse = doGetVoiceResponse();
		}
		return this.voiceResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 
	 * 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 语音回复实体
	 */
	private VoiceResponse doGetVoiceResponse() {
		Properties properties;
		if (StringUtils.isEmpty(this.voiceXmlFile)) {
			properties = xmlParse.parseString(DEFAULT_VOICE_XML_STRING);
			return doVoiceResponse(properties);
		}
		URL url = ClassLoader.getSystemResource(this.voiceXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.voiceXmlFile= null;
			properties = xmlParse.parseString(DEFAULT_VOICE_XML_STRING);
			return doVoiceResponse(properties);
		}
		// 取得名字和值信息
		properties = xmlParse.parseXmlFile(this.voiceXmlFile);
		// 用完清楚xml文件，防止再次解析
		this.voiceXmlFile = null;
		// 根据名字和值对应生成对象
		return doVoiceResponse(properties);
	}
	
	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param properties
	 *            xml名字和值对应
	 * @return 语音回复实体
	 */
	private VoiceResponse doVoiceResponse(Properties properties) {
		VoiceResponse entity = new VoiceResponse();
		doBaseAnalyze(properties, entity);
		String msgType = properties.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.VOICE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));
		String mediaId = properties.getProperty(VoiceResponse.MEDIA_ID);
		Assert.hasText(mediaId);

		entity.setMediaId(mediaId);
		return entity;
	}
	
	/** 取得视频回复实体 */
	// public VideoResponse getVideoResponse();

	/** 取得音乐回复实体 */
	// public MusicResponse getMusicResponse();

	/** 取得图文回复实体 */
	// public NewsResponse getNewsResponse();

}
