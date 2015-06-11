package com.ironside.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 菜单类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
@XStreamAlias("menu")
public class Menu {

	@XStreamImplicit
	@XStreamAlias("buttons")
	private List<Button> buttonList;

	public Menu() {
		this.buttonList = new ArrayList<Button>();
	}
	
	/**
	 * 添加一级菜单
	 * @param button
	 */
	public void addButton(Button button) {
		Assert.notNull(button);
		if (this.buttonList.size()>=3) {
			return;
		}
		this.buttonList.add(button);
	}
	
	public List<Button> getButtonList() {
		return buttonList;
	}

}
