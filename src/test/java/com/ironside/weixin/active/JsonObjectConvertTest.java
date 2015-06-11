package com.ironside.weixin.active;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.active.entity.Groupes;
import com.ironside.weixin.active.entity.IpAddresses;
import com.ironside.weixin.active.entity.UserList;

public class JsonObjectConvertTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testConvertIpAddresses() {
		String json = "{\"ip_list\":[\"127.0.0.1\",\"127.0.0.1\"]}";
		IpAddresses ipAddresses = JsonObjectConvert.getInstance().jsonToObject(json, IpAddresses.class, "ip_list", true);
		Assert.assertNotNull(ipAddresses);
		Assert.assertNotNull(ipAddresses.getIpList().get(0));
	}	

	@Test
	public void testConvertUserList() {
		String json = "{\"total\":2,\"count\":2,\"data\":{\"openid\":[\"OPENID1\",\"OPENID2\"]},\"next_openid\":\"NEXT_OPENID\"}";
		UserList userList = JsonObjectConvert.getInstance().jsonToObject(json, UserList.class, true); 
		Assert.assertNotNull(userList);
		Assert.assertEquals(userList.getCount(), 2);
		Assert.assertEquals(userList.getUseres().getOpenidList().size(), 2);
		Assert.assertEquals(userList.getUseres().getOpenidList().get(0), "OPENID1");
		Assert.assertEquals(userList.getUseres().getOpenidList().get(1), "OPENID2");
		Assert.assertEquals(userList.getNextOpenid(), "NEXT_OPENID");
	}
	
	@Test
	public void testConvertGroupes() {
		String json = "{\"groups\":[{\"id\":2,\"name\":\"星标组\",\"count\":0},{\"id\":100,\"name\":\"flysic\",\"count\":1}]}";
		Groupes groupes = JsonObjectConvert.getInstance().jsonToObject(json, Groupes.class, "groups");
		String json1 = JsonObjectConvert.getInstance().ObjectToJson(Groupes.class, groupes);
		json1 = json1.replaceAll("\\{\"groupes\":", "");
		json1 = json1.substring(0, json1.length()-1);
		json1 = json1.replaceAll("group", "groups");
		Assert.assertEquals(json, json1);
	}

}
