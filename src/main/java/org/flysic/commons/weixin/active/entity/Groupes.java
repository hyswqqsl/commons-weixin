package org.flysic.commons.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 用户所有分组
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年6月9日
 */
@XStreamAlias("groupes")
public class Groupes {

	@XStreamImplicit
	@XStreamAlias("groups")
	private List<Group> groupList;
	
	public Groupes() {
		this.groupList = new ArrayList<Group>();
	}
	
	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

}
