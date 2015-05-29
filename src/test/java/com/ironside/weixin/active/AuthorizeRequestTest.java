package com.ironside.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.active.entity.AccessToken;

public class AuthorizeRequestTest {
	
	private IAuthorize authorizeApplication;
	private final String appid = "wxdce9a330da720609";
	private final String secret = "a9c33e27b711b683514f8b6404776967";	

	@Before
	public void setUp() throws Exception {
		authorizeApplication = new AuthorizeRequest();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() {
		AccessToken accessToken = authorizeApplication.getAccessToken(appid, secret);
		assertEquals(accessToken.getExpires_in(), 7200);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccess_time()==0);
	}

}
