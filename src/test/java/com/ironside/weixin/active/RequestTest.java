package com.ironside.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.util.Assert;

import com.ironside.weixin.WeixinException;
import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.Group;
import com.ironside.weixin.active.entity.Groupes;
import com.ironside.weixin.active.entity.IpAddresses;
import com.ironside.weixin.active.entity.UserInfo;
import com.ironside.weixin.active.entity.UserList;

public class RequestTest {
	
	private final String appid = "wxdce9a330da720609";
	private final String secret = "a9c33e27b711b683514f8b6404776967";
	private final String openid = "oC2dusx6l3O4EM5fpMpuAADJrVxM";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() {
		AccessToken accessToken = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		assertTrue(accessToken.getExpiresIn()>7000 && accessToken.getExpiresIn()<8000);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccessTime()==0);
	}

	@Test
	public void testGetIpAddress() {
		AccessToken accessToken = null;
		IpAddresses ipAddress = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		ipAddress = ActiveRequest.getInstance().getIpAddress(accessToken);
		assertTrue(ipAddress.getIpList().size()>0);
	}
	
	@Test 
	public void testGetUserInfo() {
		AccessToken accessToken = null;
		UserInfo userInfo = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		userInfo = UserRequest.getInstance().getUserInfo(accessToken, openid);
		assertEquals(userInfo.getOpenid(), openid);
		assertEquals(userInfo.getNickName(), "雪庭");
		assertEquals(userInfo.getCity(), "兰州市");
		assertEquals(userInfo.getProvince(), "甘肃");
	}
	
	@Test
	public void testGetUserList() {
		AccessToken accessToken = null;
		UserList userList = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		userList = UserRequest.getInstance().getUserList(accessToken, null);
		Assert.notNull(userList);
		UserList.Useres useres = userList.getUseres();
		Assert.notNull(useres);
		Assert.notNull(useres.getOpenidList().get(0));
	}
	
	@Test
	public void testSetUserRemark() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		UserInfo userInfo = new UserInfo();
		userInfo.setOpenid(openid);
		userInfo.setRemark("湛然");
		UserRequest.getInstance().setUserRemark(accessToken, userInfo); 
	}
	
	@Test
	public void testCreateGroup() {
		Group resultGroup = null;
		String name = "好友";
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		Group group = new Group();
		group.setName(name);
		resultGroup = UserRequest.getInstance().createGroup(accessToken, group);
		assertEquals(resultGroup.getName(), name);
		assertTrue((resultGroup.getId()>0));
	}
	
	@Test
	public void testQueryGroupes() {
		// 先建立一个分组
		testCreateGroup();
		Groupes groupes = null;
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		assertTrue((groupes.getGroupList().size()>1));
		assertTrue((groupes.getGroupList().get(1).getId()>0));
	}
	
	@Test
	public void testUpdateGroup() {
		// 先获得分组
		Groupes groupes = null;
		AccessToken accessToken = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		// 修改分组名
		Group group = groupes.getGroupList().get(3);
		String name = "flysic";
		group.setName(name);
		UserRequest.getInstance().updateGroup(accessToken, group);
		// 验证
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		assertEquals(groupes.getGroupList().get(3).getId(), group.getId());
		assertEquals(groupes.getGroupList().get(3).getName(), name);
	}
	
	@Test
	public void testUpdateUserGroup() {
		// 先获得分组
		Groupes groupes = null;
		AccessToken accessToken = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		Group group = groupes.getGroupList().get(3);
		// 建立用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setOpenid(openid);
		userInfo.setGroupId(group.getId());
		// 修改用户群组
		UserRequest.getInstance().updateUserGroup(accessToken, userInfo);
		// 验证，查询用户信息
		UserInfo userInfo2 = null;
		userInfo2 = UserRequest.getInstance().getUserInfo(accessToken, openid);
		assertEquals(userInfo2.getOpenid(), userInfo.getOpenid());
		assertEquals(userInfo2.getGroupId(), userInfo.getGroupId());
	}
	
}
