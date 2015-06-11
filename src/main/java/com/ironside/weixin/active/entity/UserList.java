package com.ironside.weixin.active.entity;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 用户列表类
 * @author 雪庭
 * @sine 1.0 at 2015年6月5日
 */
@XStreamAlias("userList")
public class UserList {

	/** 关注该公众账号的总用户数 */
	private int total;
	/** 拉取的OPENID个数，最大值为10000 */
	private int count;
	/** 列表数据，OPENID的列表 */
	@XStreamAlias("data")
	private Useres useres;
	/** 拉取列表的后一个用户的OPENID */
	private String next_openid; 

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Useres getUseres() {
		return useres;
	}

	public void setUseres(Useres useres) {
		this.useres = useres;
	}

	public String getNextOpenid() {
		return next_openid;
	}

	public void setNextOpenid(String next_openid) {
		this.next_openid = next_openid;
	}

	/**
	 * OPENID的列表数据类
	 */
	public class Useres {
		
		@XStreamImplicit
		@XStreamAlias("openid")
		List<String> openidList;
		
		public Useres() {
			this.openidList = new ArrayList<String>();
		}

		public List<String> getOpenidList() {
			return openidList;
		}

		public void setOpenidList(List<String> openid) {
			this.openidList = openid;
		}
		
	}
}
