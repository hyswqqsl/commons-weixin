package com.ironside.weixin.push;

import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

import com.ironside.weixin.push.entity.EntityEnum;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public class DefaultPostProcess extends AbstractPostProcess {
	
	/** 消息处理器 */
	private IPostProcessor processor;

	@Override
	protected Entry<EntityEnum, Object> analyze(String postData) {
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
	private Properties doAnalyze(String postData) {
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
	private Entry<EntityEnum, Object> doAnalyze(Properties properties) {
		Assert.notNull(properties);
		String msgType = properties.getProperty("MsgType");
		
		return null;
	}

	@Override
	protected String process(Entry<EntityEnum, Object> entity) {
		// TODO Auto-generated method stub
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
