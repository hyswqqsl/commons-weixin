package com.ironside.weixin.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.entity.TextResponse;

public class ResponseManagerTest {
	
	// private final String XML_FILENAME = "text
	private ResponseManager responseManager;

	@Before
	public void setUp() throws Exception {
		responseManager = new ResponseManager();
		// responseManager.setXmlFileName(XML_FILENAME);
		XmlParse xmlParse = new XmlParse();
		//xmlParse.setXmlFileName(responseManager.getXmlFileName());
		responseManager.setXmlParse(xmlParse);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TextResponse textResponse = this.responseManager.getTextResponse();
		// 
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");
	}

}
