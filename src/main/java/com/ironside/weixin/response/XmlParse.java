package com.ironside.weixin.response;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;

/**
 * xml解析
 * @author 雪庭
 * @sine at 2015年4月14日
 */
public class XmlParse {
	
	/**
	 * 解析xml文件，得到名字和值信息
	 * @param xmlFile xml文件
	 * @return 名字和值信息
	 */
	public Properties parseXmlFile(String xmlFile) {
		Assert.hasText(xmlFile);
		URL url = ClassLoader.getSystemResource(xmlFile);
		// 如果xml文件不存在
		if (url==null) {
			throw new IllegalStateException(String.format("xml文件不存在: %s", xmlFile));
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(xmlFile).getPath();
		File file= new File(xmlFilePath);
		Assert.isTrue(file.isFile());
		// 创建SAX阅读器
		SAXReader reader= new SAXReader();
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
	 * @param xmlStr xml字符串
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
	 * @param document xml定义
	 * @return 名字和值信息
	 */
	@SuppressWarnings("rawtypes")
	private Properties parseDocument(Document document) {
		// 取得取得根节点
		Element root = document.getRootElement();
		// 取得子元素
		List elements = root.elements();
		Properties properties = new Properties();
		Element element;
		// 遍历子元素
		for (int i = 0; i < elements.size(); i++) {
			element = (Element) elements.get(i);
			// 添加名字和值信息
			properties.put(element.getName(), element.getText());
		}
		return properties;
	}
}
