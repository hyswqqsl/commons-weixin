package com.ironside.weixin.response;

import java.util.Properties;

/**
 * xml解析结构抽象类
 * @author 雪庭
 * @sine 1.0 at 2015年4月28日
 */
public abstract class AbstractXmlProperty {
	
	/** 名字 */
	protected String name;
	/** 键对值 */
	protected Properties properties;
	
	/**
	 * 根据键取得值
	 * @param key 键
	 * @return 值
	 */
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}
	
	/**
	 * 设置键对值
	 * @param key 键
	 * @param value 值
	 */
	public void setProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}
	
	/**
	 * 取得名字
	 * @return 名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名字
	 * @param name 名字
	 */
	public void setName(String name) {
		this.name = name;
	}	
	
	/**
	 * 加入叶子
	 * @param component 叶子
	 */
	public abstract void add(AbstractXmlProperty component);
	
	/**
	 * 取得叶子
	 * @return 子节点
	 */
	public abstract AbstractXmlProperty getChild(int index);
	
	/**
	 * 取得叶子数量
	 * @return 叶子数量
	 */
	public abstract int getChildSize(); 
}
