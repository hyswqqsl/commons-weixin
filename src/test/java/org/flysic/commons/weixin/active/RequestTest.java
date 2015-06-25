package org.flysic.commons.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.flysic.commons.weixin.active.ActiveRequest;
import org.flysic.commons.weixin.active.CustomerServerRequest;
import org.flysic.commons.weixin.active.MenuRequest;
import org.flysic.commons.weixin.active.UserRequest;
import org.flysic.commons.weixin.active.entity.AccessToken;
import org.flysic.commons.weixin.active.entity.Button;
import org.flysic.commons.weixin.active.entity.CustomerServerInfo;
import org.flysic.commons.weixin.active.entity.Group;
import org.flysic.commons.weixin.active.entity.Groupes;
import org.flysic.commons.weixin.active.entity.IpAddresses;
import org.flysic.commons.weixin.active.entity.Menu;
import org.flysic.commons.weixin.active.entity.MenuFactory;
import org.flysic.commons.weixin.active.entity.UserInfo;
import org.flysic.commons.weixin.active.entity.UserList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

public class RequestTest {

	private final String openid = "oraSIuB0b-zzRtyfFaHV-hH1eojs";
	// ironside公众号
	private final String appid = "wx2b2655fa5312a8d0";
	private final String id = "gh_f4722dc86212";
	private final String secret = "c7fcb3867fee2d7c91cfc5edbe18f2ac";
	private final String aseKey = "Ltgx7XmuZm3ugsCzui1lOPV0ItB9GusSGIx5DVVHjuE";
	// flysic-free公众号
	/*
	private final String appid = "wxc3c531f0f5bd5887";
	private final String id = "flysic-free";
	private final String secret = "6929af2c937f8d7a172e188368b3d31b";
	private final String aseKey = "m7St9PPv8D3gLAvAWJjGGjBpwFOWLWQYcxGJye82bQh";
	*/
	

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
	
	// @Test 
	public void testDeleteGroup() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		Groupes groupes = UserRequest.getInstance().getGroupes(accessToken);		
		UserRequest.getInstance().deleteGroup(accessToken, groupes.getGroupList().get(groupes.getGroupList().size()-1));
		Groupes groupes2 = UserRequest.getInstance().getGroupes(accessToken);
		assertEquals(groupes.getGroupList().size(), groupes2.getGroupList().size()+1);
	}
	
	// @Test
	public void testCreateGroup() {
		Group resultGroup = null;
		String name = "flysic";
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		// 删除分组
		UserRequest.getInstance().deleteGroup(accessToken, resultGroup);
		Group group = new Group();
		group.setName(name);
		resultGroup = UserRequest.getInstance().createGroup(accessToken, group);
		assertEquals(resultGroup.getName(), name);
		assertTrue((resultGroup.getId()>0));
	}
	
	@Test
	public void testQueryGroupes() {
		Groupes groupes = null;
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		assertTrue((groupes.getGroupList().size()>1));
		assertTrue((groupes.getGroupList().get(1).getId()>0));
	}
	
	// @Test
	public void testUpdateGroup() {
		// 先获得分组
		Groupes groupes = null;
		AccessToken accessToken = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		// 修改分组名
		Group group = groupes.getGroupList().get(2);
		String name = "flysic";
		group.setName(name);
		UserRequest.getInstance().updateGroup(accessToken, group);
		// 验证
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		assertEquals(groupes.getGroupList().get(2).getId(), group.getId());
		assertEquals(groupes.getGroupList().get(2).getName(), name);
	}
	
	@Test
	public void testUpdateUserGroup() {
		// 先获得分组
		Groupes groupes = null;
		AccessToken accessToken = null;
		accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		groupes = UserRequest.getInstance().getGroupes(accessToken);
		Group group = groupes.getGroupList().get(2);
		// 生成用户信息
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
	
	@Test
	public void testCreateMenu() {
		// 生成菜单对象
		Menu menu = new Menu();
		Button button = MenuFactory.makeClickButton("按钮1", "click1");
		menu.addButton(button);
		button = MenuFactory.makeClickButton("请看子菜单", "click2");
		menu.addButton(button);
		Button subButton = MenuFactory.makeClickButton("子菜单1", "sub1");
		button.addSubButton(subButton);
		subButton = MenuFactory.makeScancodePushButton("子菜单2", "sub2");
		button.addSubButton(subButton);
		subButton = MenuFactory.makeViewButton("子菜单3", "http://cn.bing.com/?FORM=Z9FD1");
		button.addSubButton(subButton);
		button = MenuFactory.makeClickButton("按钮2", "click3");
		menu.addButton(button);
		// 建立菜单
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		MenuRequest.getInstance().createMenu(accessToken, menu);
		// 验证
		Menu menu2 = MenuRequest.getInstance().queryMenu(accessToken);
		assertEquals(menu2.getButtonList().size(), 3);
	}
	
	@Test
	public void testGetCustomerServeres() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		CustomerServerInfo customerServerInfo = new CustomerServerInfo();
		customerServerInfo.setCount("test1@gh_881cb5a2dc3a");
		customerServerInfo.setNickName("客服1");
		customerServerInfo.setPassword("96e79218965eb72c92a549dd5a330112");
		String json = CustomerServerRequest.getInstance().addCustomerServer(accessToken, customerServerInfo);
		json = CustomerServerRequest.getInstance().getCustomerServeres(accessToken);
		org.junit.Assert.assertNotNull(json);
	}
	
}
