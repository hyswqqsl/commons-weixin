package com.ironside.weixin.response;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.request.entity.TextEntity;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ResponseEnum;
import com.ironside.weixin.response.entity.TextResponse;

/**
 * 回复实体管理
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class ResponseManager {

	/** 默认文本类型回复xml文件 */
	private final String DEFAULT_TEXT_XML_FILE = "textResponse.xml";

	/** 文本类型回复xml文件 */
	private String textXmlFile;
	/** xml解析对象 */
	private XmlParse xmlParse;
	/** 文本回复消息缓冲 */
	TextResponse textResponse;

	/**
	 * 取得文本类型回复xml文件
	 * @return 文本类型回复xml文件
	 */
	public String getTextXmlFile() {
		return textXmlFile;
	}

	/**
	 * 设置文本类型回复xml文件
	 * @param textXmlFile 文本类型回复xml文件
	 */
	public void setTextXmlFile(String textXmlFile) {
		Assert.hasText(textXmlFile);
		this.textXmlFile = textXmlFile;
		// 清空缓存
		this.textResponse = null;
	}

	/** 
	 * 取得xml解析对象
	 * @return xml解析对象
	 */
	public XmlParse getXmlParse() {
		return xmlParse;
	}

	/**
	 * 设置xml解析对象
	 * @param xmlParse xml解析对象
	 */
	public void setXmlParse(XmlParse xmlParse) {
		Assert.notNull(xmlParse);
		this.xmlParse = xmlParse;
	}	

	/**
	 * 取得文本回复实体</br>
	 * 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * @return 文本回复实体
	 */
	public TextResponse getTextResponse() {
		if (this.textResponse==null) {
			this.textResponse = doGetTextResponse();
		}
		return this.textResponse;
	}
	
	/**
	 * 从xml文件中解析实体</br>
	 * 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * @return 文本回复实体
	 */
	private TextResponse doGetTextResponse() {
		String xmlFile;
		if (StringUtils.isEmpty(this.textXmlFile)) {
			xmlFile = DEFAULT_TEXT_XML_FILE;
		} else {
			URL url = ClassLoader.getSystemResource(this.textXmlFile);
			// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
			if (url==null) {
				xmlFile = DEFAULT_TEXT_XML_FILE;
				this.textXmlFile = null;
			} else {
				xmlFile = this.textXmlFile;
			}
		}
		// 取得名字和值信息
		Properties properties = xmlParse.parseXmlFile(xmlFile);
		// 根据名字和值对应生成对象
		return doTextResponse(properties);
	}

	/*
	public TextResponse getTextResponse(String xmlFile) {
		// 取得名字和值信息
		Properties properties = xmlParse.parseXmlFile(xmlFile);
		// 根据名字和值对应生成对象
		return  doTextResponse(properties);
	}
	 */
	
	/**
	 * 基础解析
	 * @param properties 解析后的properties
	 * @param entity 用于基础解析的实体
	 */
	private void doBaseAnalyze(Properties properties, AbstractBaseResponse entity) {
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
	 * 根据名字和值对应生成对象
	 * @param properties xml名字和值对应
	 * @return 文本回复实体
	 */
	private TextResponse doTextResponse(Properties properties) {
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
		 <CreateTime>12345678</CreateTime>
		 <MsgType><![CDATA[text]]></MsgType>
		 <Content><![CDATA[你好]]></Content>
	  	 </xml>
		 */
		TextResponse entity = new TextResponse();
		doBaseAnalyze(properties, entity);
		String content = properties.getProperty(TextEntity.CONTENT);
		Assert.hasText(content);
		String msgType = properties.getProperty(TextEntity.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.TEXT.getMsgType()), String.format("文本回复xml中MsgType有误： %s", msgType));

		entity.setContent(content);
		return entity;
	}

	/** 取得图片回复实体 */
	// public 	ImageResponse getImageResponse();
	
	/** 取得语音回复实体 */
	// public 	VoiceResponse getVoiceResponse();
	
	/** 取得视频回复实体 */
	// public 	VideoResponse getVideoResponse();
	
	/** 取得音乐回复实体 */
	// public 	MusicResponse getMusicResponse();
	
	/** 取得图文回复实体 */
	// public 	NewsResponse getNewsResponse();

}
