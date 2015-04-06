package com.ironside.weixin.push;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

import com.ironside.weixin.push.entity.AbstractBaseEntity;
import com.ironside.weixin.push.entity.EntityEnum;
import com.ironside.weixin.push.entity.ImageEntity;
import com.ironside.weixin.push.entity.TextEntity;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public class DefaultPostProcess extends AbstractPostProcess {
	
	/** 消息处理器 */
	private IPostProcessor processor;
	/** 开发者微信号  */
	//private String toUserName;
	/** 发送方帐号（一个OpenID） */
	//private String fromUserName;
	/** 消息创建时间  */
	//private Date createTime;
	/** 消息id，64位整型 */
	//private String msgId;	

	@Override
	AbstractBaseEntity analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		// 将postData解析成properties对象
		Properties properties = doAnalyze(postData);
		// 解析properties对象，建立entity对象
		AbstractBaseEntity entry = doAnalyze(properties);
		return entry; 
	}

	/**
	 * 将postData解析成properties对象
	 * @param postData POST方式推送的数据
	 * @return 解析后的properties对象
	 */
	Properties doAnalyze(String postData) {
		Properties properties = new Properties();
		Document document = null;
		try {
			document = DocumentHelper.parseText(postData);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Assert.notNull(document);
		Element root = document.getRootElement();
		List elements = root.elements();
		Element element;
		for (int i = 0; i < elements.size(); i++) {
			element = (Element)elements.get(i);
			properties.put(element.getName(), element.getText());
		}
		return properties;
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

		return null;
	}

	/**
	 * 解析普通消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体对象
	 */
	private AbstractBaseEntity doMessageAnalyze(Properties properties) {
		String msgType = properties.getProperty("MsgType");
		if (msgType.equals(EntityEnum.TEXT.getMsgType())) {
			return doTextAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.IMAGE.getMsgType())) {
			return doImageAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.LOCATION.getMsgType())) {
			return doLocationAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VIDEO.getMsgType())) {
			return doVideoAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VOICE.getMsgType())) {
			return doVoiceAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.SHORTVIDEO.getMsgType())) {
			return doShortVideoAnalyze(properties);
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
		/* 示例
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

		entity.setMsgEnum(EntityEnum.TEXT);
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
		/* 示例
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
		String mediaId = properties.getProperty(ImageEntity.MEDIA_URL);
		Assert.hasText(mediaId);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);				

		entity.setMsgEnum(EntityEnum.IMAGE);
		entity.setPicUrl(picUrl);
		entity.setMediaId(mediaId);
		entity.setMsgId(msgId);
		
		return entity;
	}

	private AbstractBaseEntity doLocationAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractBaseEntity doVideoAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractBaseEntity doVoiceAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractBaseEntity doShortVideoAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractBaseEntity doLinkAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String process(AbstractBaseEntity entity) {
		return null;
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
