package com.ironside.weixin.response;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class XmlParse {

	private String xmlFileName;
	private Document document;
	private final String EMPTY_STR = "";

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;;
		// 创建文件对象
		String url = ClassLoader.getSystemResource(xmlFileName).getPath();
		File file= new File(url);
		if (file.isFile()==false) {
			this.xmlFileName = null;
			this.document = null;
			return;
		}
		// 创建SAX阅读器
		SAXReader reader= new SAXReader();
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//读取内容生成Document对象
		Assert.notNull(document);			
	}

	@SuppressWarnings("rawtypes")
	public String getXmlString(String name) {
		if (document==null) {
			return EMPTY_STR;
		}
		// 取得根节点
		Element root = document.getRootElement();
		List elements = root.elements();
		Element element;
		for (int i = 0; i < elements.size(); i++) {
			element = (Element) elements.get(i);
			if (element.getName().equals(name)) {
				return element.getText();
			}
		}
		return EMPTY_STR;
	}

	/**
	 * 将xmlStr解析成properties对象
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @return 解析后的properties对象
	 */
	@SuppressWarnings("rawtypes")
	public Properties parse(String xmlStr) {
		Properties properties = new Properties();
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Assert.notNull(document);
		Element root = document.getRootElement();
		List elements = root.elements();
		Element element;
		for (int i = 0; i < elements.size(); i++) {
			element = (Element) elements.get(i);
			properties.put(element.getName(), element.getText());
		}
		return properties;
	}
}
