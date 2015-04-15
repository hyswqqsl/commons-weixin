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
	
	/** 回复实体xml文件 */
	private final String TEST_TEST_XMLFILE = "testTextResponse.xml";
	/** 有问题的实体xml文件 */
	private final String WRONG_XMLFILE = "wrongTextResponse.xml";
	/** 不存在的回复实体xml文件 */
	private final String NOEXISTING_TEST_XMLFILE = "noexistingTestResponse.xml";

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
		// *** 测试取得默认消息 ***
		TextResponse textResponse = this.responseManager.getTextResponse();
		// 验证消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");
		// *** 测试根据回复实体xml文件取得文本回复实体 ***
		this.responseManager.setTextResponseXml(TEST_TEST_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是根据xml文件解析消息
		Assert.assertEquals(textResponse.getFromUserName(), "testFromUser");
		Assert.assertEquals(textResponse.getToUserName(), "testToUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");
		// *** 测试不存在的xml文件 ***
		this.responseManager.setTextResponseXml(NOEXISTING_TEST_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是默认消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), "text");
		// *** 测试有问题的实体xml文件
		this.responseManager.setTextResponseXml(WRONG_XMLFILE);
		try {
			textResponse = this.responseManager.getTextResponse();
		} catch(IllegalStateException e) {
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");
	}
	
	/**
	 * 测试以缓冲方式取得文本回复消息
	 */
	@Test
	public void testTextResponseCache() {
		String cacheContent = "cacheContent";
		
		// *** 测试以缓冲方式取得文本回复消息 ***
		// 根据xml文件解析消息
		this.responseManager.setTextResponseXml(TEST_TEST_XMLFILE);
		TextResponse textResponse = this.responseManager.getTextResponse(); 
		// 验证取得的消息内容(content)不是cacheContent
		Assert.assertTrue((textResponse.getContent().equals(cacheContent)==false));
		// 设置消息内容为cacheContent
		TextResponse textResponse2 = this.responseManager.textResponse;
		textResponse2.setContent(cacheContent);
		// 验证取得的消息内容是从缓冲取得
		textResponse = this.responseManager.getTextResponse();
		Assert.assertTrue((textResponse.getContent().equals(cacheContent)==true));
		// *** 测试再次设置xml文件，缓冲会更新 ***
		// 再次设置xml文件
		this.responseManager.setTextResponseXml(TEST_TEST_XMLFILE);
		textResponse = this.responseManager.getTextResponse(); 
		// 验证缓冲会更新
		Assert.assertTrue((textResponse.getContent().equals(cacheContent)==false));		
	}

}
