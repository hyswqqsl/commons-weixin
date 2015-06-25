package org.flysic.commons.weixin.passive.request;

import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

/**
 * xml解析
 * 
 * @author 雪庭
 * @sine at 2015年4月14日
 */
public class XmlParse {

	/**
	 * 解析xml字符串，得到名字和值信息
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @return 解析后的properties对象
	 */
	public Properties parseString(String xmlStr) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Assert.notNull(document);
		return parseDocument(document);
	}

	/**
	 * 解析xml定义，得到名字和值信息
	 * 
	 * @param document
	 *            xml定义
	 * @return xml信息结构
	 */
	@SuppressWarnings("rawtypes")
	private Properties parseDocument(Document document) {
		// 取得取得根节点
		Element root = document.getRootElement();
		// 取得子元素
		List elements = root.elements();
		Properties properties = new Properties();
		Element element;
		for (int i = 0; i < elements.size(); i++) {
			element = (Element) elements.get(i);
			// 添加名字和值信息
			properties.setProperty(element.getName(), element.getText());
		}
		return properties;
	}
	
}
