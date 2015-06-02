package com.ironside.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

public class IpAddresses {
	
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
