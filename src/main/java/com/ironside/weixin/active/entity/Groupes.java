package com.ironside.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 用户所有分组
 * @author 雪庭
 * @sine 1.0 at 2015年6月9日
 */
@XStreamAlias("groupes")
public class Groupes {

	@XStreamImplicit
	private List<Group> groups;
	
	public Groupes() {
		this.groups = new ArrayList<Group>();
	}

	public List<Group> getGroupList() {
		return groups;
	}

	public void setGroupList(List<Group> groupList) {
		this.groups = groupList;
	}

}
