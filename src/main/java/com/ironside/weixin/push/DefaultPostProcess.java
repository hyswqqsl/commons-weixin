package com.ironside.weixin.push;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

import com.ironside.weixin.push.entity.BaseEntity;
import com.ironside.weixin.push.entity.EntityEnum;
import com.ironside.weixin.push.entity.TextEntity;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public class DefaultPostProcess extends AbstractPostProcess {
	
	/** 消息处理器 */
	private IPostProcessor processor;

	@Override
	Entry<EntityEnum, Object> analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		// 将postData解析成properties对象
		Properties properties = doAnalyze(postData);
		// 解析properties对象，建立entity对象
		Entry<EntityEnum, Object> entry = doAnalyze(properties);
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
	Entry<EntityEnum, Object> doAnalyze(Properties properties) {
		Assert.notNull(properties);
		String msgType = properties.getProperty("MsgType");
		
		// 解析事件消息
		if (msgType.equals("event")) {
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
	private Entry<EntityEnum, Object> doEventAnalyze(Properties properties) {

		return null;
	}

	/**
	 * 解析普通消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的实体对象
	 */
	private Entry<EntityEnum, Object> doMessageAnalyze(Properties properties) {
		String msgType = properties.getProperty("MsgType");
		if (msgType.equals(EntityEnum.TEXT.getIdentify())) {
			return doTextAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.IMAGE.getIdentify())) {
			return doImageAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.LOCATION.getIdentify())) {
			return doLocationAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VIDEO.getIdentify())) {
			return doVideoAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.VOICE.getIdentify())) {
			return doVoiceAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.SHORTVIDEO.getIdentify())) {
			return doShortVideoAnalyze(properties);
		}
		if (msgType.equals(EntityEnum.LINK.getIdentify())) {
			return doLinkAnalyze(properties);
		}
		throw new IllegalStateException(String.format("解析普通消息出错:(%s)消息类型未知", msgType));
	}

	/**
	 * 解析普通文本消息
	 * @param properties POST推送数据解析后的properties
	 * @return 解析后的文本对象
	 */
	private Entry<EntityEnum, Object> doTextAnalyze(Properties properties) {
		String toUserName = properties.getProperty(TextEntity.TO_USER_NAME);
		Assert.hasText(toUserName);
		String fromUserName = properties.getProperty(TextEntity.FORM_USER_NAME);
		Assert.hasText(fromUserName);
		String createTimeStr = properties.getProperty(TextEntity.CREATE_TIME);
		Assert.hasText(createTimeStr);
		// 将时间整形转换为对象
		Date createTime = new Date(Long.parseLong(createTimeStr));
		String content = properties.getProperty(TextEntity.CONTENT);
		Assert.hasText(content);
		String msgId = properties.getProperty(TextEntity.MSG_ID);
		Assert.hasText(msgId);
		
		TextEntity entity = new TextEntity();
		entity.setToUserName(toUserName);
		entity.setFromUserName(fromUserName);
		entity.setCreateTime(createTime);
		entity.setMsgType(EntityEnum.TEXT);
		entity.setContent(content);
		entity.setMsgId(msgId);
		
		return (BaseEntity)entity;
	}

	private Entry<EntityEnum, Object> doImageAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private Entry<EntityEnum, Object> doLocationAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private Entry<EntityEnum, Object> doVideoAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private Entry<EntityEnum, Object> doVoiceAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private Entry<EntityEnum, Object> doShortVideoAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	private Entry<EntityEnum, Object> doLinkAnalyze(Properties properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String process(Entry<EntityEnum, Object> entity) {
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
