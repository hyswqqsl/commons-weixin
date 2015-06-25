package org.flysic.commons.weixin.active.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 用户组
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年6月8日
 */
@XStreamAlias("group")
public class Group {

	private int id;
	private String name;
	private int count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

	
}
