package org.flysic.commons.weixin.active;

import static org.junit.Assert.*;

import org.flysic.commons.weixin.active.HttpsRequest;
import org.flysic.commons.weixin.active.entity.AccessToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * https方式访问测试
 * @author 雪庭
 * @sine 1.0 at 2015年5月28日
 */
public class HttpsRequestTest {
	
	private String httpsGetUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private String httpsPostUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private final String appid = "wxdce9a330da720609";
	private final String secret = "a9c33e27b711b683514f8b6404776967";
	private String jsonTemplate = "{object:json}";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void processTest() {
		httpsGetUrl = httpsGetUrl.replaceAll("APPID", appid).replaceAll("APPSECRET", secret);
		String json = HttpsRequest.getInstance().processGet(httpsGetUrl);
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		json = jsonTemplate.replaceAll("json", json);
		xStream.alias("object", AccessToken.class);
		AccessToken accessToken = (AccessToken)xStream.fromXML(json);
		accessToken.setAccessTime(System.currentTimeMillis()/1000);
		Assert.assertNotNull(json);
		Assert.assertNotNull(accessToken.getAccessToken());
		httpsPostUrl = httpsPostUrl.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		json = HttpsRequest.getInstance().processPost(httpsPostUrl, "{\"button\":[{\"type\":\"click\",\"name\":\"text\",\"key\":\"1\"},"
				+ "{\"type\":\"click\",\"name\":\"imagee\",\"key\":\"2\"},"
				+ "{\"type\":\"click\",\"name\":\"voicee\",\"key\":\"3\"}]}");
		Assert.assertEquals(json, "{\"errcode\":0,\"errmsg\":\"ok\"}");
	}
	
}
