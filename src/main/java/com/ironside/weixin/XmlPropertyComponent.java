package com.ironside.weixin;

import java.util.Properties;

import com.ironside.weixin.response.AbstractXmlProperty;

/**
 * xml解析结构叶子类
 * @author 雪庭
 * @sine 1.0 at 2015年4月28日
 */
public class XmlPropertyComponent extends AbstractXmlProperty {

	/**
	 * 构造函数
	 */
	public XmlPropertyComponent() {
		this.properties = new Properties();
	}
	
	@Override
	public void add(AbstractXmlProperty component) {
	}

	@Override
	public AbstractXmlProperty getChild(int index) {
		return null;
	}

	@Override
	public int getChildSize() {
		return 0;
	}

}
