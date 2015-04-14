package com.ironside.weixin.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.entity.TextResponse;

/**
 * 回复实体管理测试
 * @author 雪庭
 * @sine at 2015年4月14日
 */
public class ResponseManagerTest {
	
	/** 回复实体管理 */
	private ResponseManager responseManager;
	/** 错误回复实体xml文件 */
	private final String WRONG_TEST_XMLFILE = "wrongTestResponse.xml";
	/** 回复实体xml文件 */
	private final String TEST_TEST_XMLFILE = "testTextResponse.xml";	

	@Before
	public void setUp() throws Exception {
		responseManager = new ResponseManager();
		XmlParse xmlParse = new XmlParse();
		responseManager.setXmlParse(xmlParse);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 测试取得文本回复消息
	 */
	@Test
	public void testGetTextResponse() {
		// 取得默认消息
		TextResponse textResponse = this.responseManager.getTextResponse();
		// 验证消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");
		// 测试根据传递的xml文件取得文本回复实体
		textResponse = this.responseManager.getTextResponse(TEST_TEST_XMLFILE);
		// 验证消息
		Assert.assertEquals(textResponse.getFromUserName(), "testFromUser");
		Assert.assertEquals(textResponse.getToUserName(), "testToUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");		
		// 测试不存在的xml文件
		try {
			textResponse = this.responseManager.getTextResponse(WRONG_TEST_XMLFILE);
		} catch(Exception e) {
			return;
		}
		Assert.fail("传入不存在的xml文件，不应执行到这里");
	}

}
