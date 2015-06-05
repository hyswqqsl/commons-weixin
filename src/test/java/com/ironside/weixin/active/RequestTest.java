package com.ironside.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ironside.weixin.active.entity.AccessToken;
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
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		assertTrue(accessToken.getExpiresIn()>7000 && accessToken.getExpiresIn()<8000);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccessTime()==0);
	}

	@Test
	public void testGetIpAddress() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		IpAddresses ipAddress = ActiveRequest.getInstance().getIpAddress(accessToken);
		assertTrue(ipAddress.getIpList().size()>0);
	}
	
	@Test 
	public void testGetUserInfo() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		UserInfo userInfo = UserRequest.getInstance().getUserInfo(accessToken, openid);
		assertEquals(userInfo.getOpenid(), openid);
		assertEquals(userInfo.getNickName(), "雪庭");
		assertEquals(userInfo.getCity(), "兰州市");
		assertEquals(userInfo.getProvince(), "甘肃");
	}
	
	@Test
	public void testGetUserList() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		UserList userList = UserRequest.getInstance().getUserList(accessToken, null);
		Assert.notNull(userList);
		UserList.UserListData data = userList.getData();
		Assert.notNull(data);
	}
	
	@Test
	public void testSetUserRemark() {
		AccessToken accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		boolean result = UserRequest.getInstance().setUserRemark(accessToken, "{\"openid\":\"oC2dusx6l3O4EM5fpMpuAADJrVxM\",\"remark\":\"机器\"}");
		Assert.isTrue(result);
	}
}
