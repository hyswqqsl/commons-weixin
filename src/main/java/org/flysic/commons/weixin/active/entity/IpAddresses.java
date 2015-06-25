package org.flysic.commons.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * ip地址列表
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年6月10日
 */
@XStreamAlias("ipAddresses")
public class IpAddresses {
	
	@XStreamImplicit
	private List<String> ip_list;
	
	public IpAddresses() {
		this.ip_list = new ArrayList<String>();
	}

	public List<String> getIpList() {
		return ip_list;
	}

	public void setIpList(List<String> ipList) {
		this.ip_list = ipList;
	}

}
