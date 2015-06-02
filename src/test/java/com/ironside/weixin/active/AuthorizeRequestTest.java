package com.ironside.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;

public class AuthorizeRequestTest {
	
	private IAuthorize authorizeRequest;
	private final String appid = "wxdce9a330da720609";
	private final String secret = "a9c33e27b711b683514f8b6404776967";	

	@Before
	public void setUp() throws Exception {
		authorizeRequest = new AuthorizeRequest();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() {
		AccessToken accessToken = authorizeRequest.getAccessToken(appid, secret);
		assertEquals(accessToken.getExpiresIn(), 7200);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccessTime()==0);
	}

	@Test
	public void testGetIpAddress() {
		AccessToken accessToken = authorizeRequest.getAccessToken(appid, secret);
		IpAddresses ipAddress = authorizeRequest.getIpAddress(accessToken);
		assertTrue(ipAddress.getIpList().size()>0);
	}
}
