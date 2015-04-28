package com.ironside.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ironside.weixin.response.AbstractXmlProperty;

/**
 * xml解析结构树枝类
 * @author 雪庭
 * @sine 1.0 at 2015年4月28日
 */
public class XmlPropertyComposite extends AbstractXmlProperty {
	
	/** 叶子集合 */
	private List<AbstractXmlProperty> components;
	
	/**
	 * 构造函数
	 */
	public XmlPropertyComposite() {
		this.properties = new Properties();
		this.components = new ArrayList<AbstractXmlProperty>();
	}

	@Override
	public void add(AbstractXmlProperty component) {
		components.add(component);
	}

	@Override
	public AbstractXmlProperty getChild(int index) {
		return this.components.get(index);
	}

	@Override
	public int getChildSize() {
		return this.components.size();
	}

}
