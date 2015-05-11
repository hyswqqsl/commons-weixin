package com.ironside.weixin;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.request.DefaultPostProcess;
import com.ironside.weixin.response.ResponseManager;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.TextResponse;

public class PostProcessorTest {
	
	/** POST方式推送给微信公众账号的消息处理 */
	private DefaultPostProcess postProcess;
	
	/** 文本消息post xml模板 */
	private final String text_post_xml_format = "<xml>" +
			"<ToUserName><![CDATA[toUser]]></ToUserName>" +
			"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>1348831860</CreateTime>" +
			"<MsgType><![CDATA[text]]></MsgType>" +
			"<Content><![CDATA[%1$s]]></Content>" +
			"<MsgId>1234567890123456</MsgId>" +
			"</xml>";
	
	/** click事件post xml模板 */
	private final String event_click_xml_format = "<xml>" +
			"<ToUserName><![CDATA[toUser]]></ToUserName>" +
			"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>123456789</CreateTime>" +
			"<MsgType><![CDATA[event]]></MsgType>" +
			"<Event><![CDATA[CLICK]]></Event>" +
			"<EventKey><![CDATA[%1$s]]></EventKey>" +
			"</xml>";

	@Before
	public void setUp() throws Exception {
		// 消息处理器
		MyPostProcessor processor = new MyPostProcessor();
		// POST方式推送给微信公众账号的消息处理
		postProcess = new DefaultPostProcess();
		// 设置消息处理器
		postProcess.setProcessor(processor);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTextPost() {
		// *** 准备textPost(查询桂花) ***
		String postData = String.format(text_post_xml_format, MyMessage.QUERY_OS);
		String result = this.postProcess.process(postData);
		// 验证
		ResponseManager responseManager = new ResponseManager();
		TextResponse response = responseManager.getTextResponse();
		response.setFromUserName("toUser");
		response.setToUserName("fromUser");
		response.setContent(MyMessage.OS_INFO);
		String validate_result = responseManager.responseToXml(response);
		// 验证返回文本回复(桂花介绍)
		Assert.assertEquals(result, validate_result);
		
		// *** 准备textPost(查询荷花) ***
		postData = String.format(text_post_xml_format, MyMessage.QUERY_LO);
		result = this.postProcess.process(postData);
		// 验证
		response.setContent(MyMessage.LO_INFO);
		validate_result = responseManager.responseToXml(response);
		// 验证返回文本回复(荷花介绍)
		Assert.assertEquals(result, validate_result);		
	}
	
	@Test
	public void testEventClickPost() {
		// *** 准备eventClick(点击文本项) ***
		String postData = String.format(event_click_xml_format, MyMessage.CLICK_TEXT);
		String result = this.postProcess.process(postData);
		// 验证
		ResponseManager responseManager = new ResponseManager();
		AbstractBaseResponse response = responseManager.getTextResponse();
		response.setFromUserName("toUser");
		response.setToUserName("fromUser");
		String validate_result = responseManager.responseToXml(response);
		// 验证返回默认文本回复
		Assert.assertEquals(result, validate_result);
		
		// *** 准备eventClick(点击图片项) ***
		postData = String.format(event_click_xml_format, MyMessage.CLICK_IMAGE);
		result = this.postProcess.process(postData);
		// 验证
		response = responseManager.getImageResponse();
		response.setFromUserName("toUser");
		response.setToUserName("fromUser");		
		validate_result = responseManager.responseToXml(response);
		// 验证返回默认图片回复
		Assert.assertEquals(result, validate_result);		
	}

}
