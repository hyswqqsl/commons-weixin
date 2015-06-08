package com.ironside.weixin.active;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




import com.ironside.weixin.active.entity.UserList;

public class JsonObjectConvertTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertUserList() {
		String json = "{\"total\":2,\"count\":2,\"data\":{\"openid\":[\"OPENID1\",\"OPENID2\"]},\"next_openid\":\"NEXT_OPENID\"}";
		UserList userList = JsonObjectConvert.getInstance().jsonToObject(json, UserList.class, "data", UserList.UserListData.class, "openid");
		Assert.assertNotNull(userList);
		Assert.assertEquals(userList.getCount(), 2);
		Assert.assertEquals(userList.getData().getOpenid().size(), 2);
		Assert.assertEquals(userList.getData().getOpenid().get(0), "OPENID1");
		Assert.assertEquals(userList.getData().getOpenid().get(1), "OPENID2");
		Assert.assertEquals(userList.getNextOpenid(), "NEXT_OPENID");
	}

}
