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

public class ActiveRequestTest {
	
	private ActiveRequest activeRequest;
	private final String appid = "wxdce9a330da720609";
	private final String secret = "a9c33e27b711b683514f8b6404776967";
	private final String openid = "oC2dusx6l3O4EM5fpMpuAADJrVxM";

	@Before
	public void setUp() throws Exception {
		activeRequest = new ActiveRequest();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() {
		AccessToken accessToken = activeRequest.getAccessToken(appid, secret);
		assertTrue(accessToken.getExpiresIn()>7000 && accessToken.getExpiresIn()<8000);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccessTime()==0);
	}

	@Test
	public void testGetIpAddress() {
		AccessToken accessToken = activeRequest.getAccessToken(appid, secret);
		IpAddresses ipAddress = activeRequest.getIpAddress(accessToken);
		assertTrue(ipAddress.getIpList().size()>0);
	}
	
	@Test 
	public void testGetUserInfo() {
		AccessToken accessToken = activeRequest.getAccessToken(appid, secret);
		UserInfo userInfo = activeRequest.getUserInfo(accessToken, openid);
		assertEquals(userInfo.getOpenid(), openid);
		assertEquals(userInfo.getNickName(), "雪庭");
		assertEquals(userInfo.getCity(), "兰州市");
		assertEquals(userInfo.getProvince(), "甘肃");
	}
	
	@Test
	public void testGetUserList() {
		AccessToken accessToken = activeRequest.getAccessToken(appid, secret);
		UserList userList = activeRequest.getUserList(accessToken, null);
		Assert.notNull(userList);
		UserList.UserListData data = userList.getData();
		Assert.notNull(data);
	}
}
