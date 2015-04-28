package com.ironside.weixin;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.response.AbstractXmlProperty;

/**
 * xml解析
 * 
 * @author 雪庭
 * @sine at 2015年4月14日
 */
public class XmlParse {

	/**
	 * 解析xml文件，得到名字和值信息
	 * 
	 * @param xmlFile
	 *            xml文件
	 * @return 名字和值信息
	 */
	public AbstractXmlProperty parseXmlFile(String xmlFile) {
		Assert.hasText(xmlFile);
		URL url = ClassLoader.getSystemResource(xmlFile);
		// 如果xml文件不存在
		if (url == null) {
			throw new IllegalStateException(String.format("xml文件不存在: %s",
					xmlFile));
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(xmlFile).getPath();
		File file = new File(xmlFilePath);
		Assert.isTrue(file.isFile());
		// 创建SAX阅读器
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 读取内容生成Document对象
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Assert.notNull(document);
		return parseDocument(document);
	}

	/**
	 * 解析xml字符串，得到名字和值信息
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @return 解析后的properties对象
	 */
	public AbstractXmlProperty sparseString(String xmlStr) {
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
	private AbstractXmlProperty parseDocument(Document document) {
		// 取得取得根节点
		Element root = document.getRootElement();
		// 取得子元素
		List elements = root.elements();
		AbstractXmlProperty xmlProperty = new XmlPropertyComposite();
		Element element;
		for (int i = 0; i < elements.size(); i++) {
			element = (Element) elements.get(i);
			// 添加名字和值信息
			// 如果节点没有子节点，直接获取内容
			if (element.elements().size() == 0) {
				xmlProperty.setProperty(element.getName(), element.getText());
				continue;
			}
			// 如果有子节点，处理节点
			xmlProperty.setName(element.getName());
			parseComposite(xmlProperty, element);
		}
		return xmlProperty;
	}
	
	/**
	 * 处理节点</br>
	 * 分为两种情况：
	 * <ul>
	 * <li>如果第一个子节点没有子节点，直接加入叶子</li>
	 * <li>如果第一个子节点有子节点，以每个子节点作为叶子加入</li>
	 * </ul>
	 * @param xmlProperty 名字和值信息
	 * @param element 节点
	 * @return xml信息结构
	 */
	@SuppressWarnings("rawtypes")
	private AbstractXmlProperty parseComposite(AbstractXmlProperty xmlProperty, Element element) {
		List elements = element.elements();
		// 如果第一个子节点没有子节点，直接加入叶子
		Element childElement = (Element) elements.get(0);
		if (childElement.elements().size() == 0)
		{
			parseComponent(xmlProperty, element);
			return xmlProperty;	
		}
		// 如果第一个子节点有子节点，以每个子节点作为叶子加入
		for (int i = 0; i < elements.size(); i++) {
			childElement = (Element) elements.get(i);
			parseComponent(xmlProperty, childElement);
		}
		return xmlProperty;
	}

	/**
	 * 将节点作为叶子加入
	 * @param xmlProperty xml信息结构
	 * @param element 节点
	 */
	@SuppressWarnings("rawtypes")
	private void parseComponent(AbstractXmlProperty xmlProperty, Element element) {
		// 生产叶子，加入到树枝
		AbstractXmlProperty component = new XmlPropertyComponent();
		component.setName(element.getName());
		xmlProperty.add(component);
		List elements = element.elements();
		Element childElement;
		for (int i=0; i<elements.size(); i++) {
			childElement = (Element)elements.get(i);
			component.setProperty(childElement.getName(), childElement.getText());
		}
	}

}
