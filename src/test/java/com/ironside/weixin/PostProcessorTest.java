package com.ironside.weixin;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.request.DefaultPostProcess;
import com.ironside.weixin.response.ResponseManager;
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

	@Before
	public void setUp() throws Exception {
		// 消息处理器
		ResponseManager responseManager = new ResponseManager();
		MyPostProcessor processor = new MyPostProcessor(responseManager);
		// POST方式推送给微信公众账号的消息处理
		postProcess = new DefaultPostProcess();
		// 设置消息处理器
		postProcess.setProcessor(processor);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTextPostRequest() {
		// 准备textPost
		String post_os = String.format(text_post_xml_format, MyMessage.QUERY_OS);
		String result = this.postProcess.process(post_os);
		// 验证
		ResponseManager responseManager = new ResponseManager();
		TextResponse response = responseManager.getTextResponse();
		response.setFromUserName("toUser");
		response.setToUserName("fromUser");
		response.setContent(MyMessage.OS_INFO);
		String validate_result = responseManager.responseToXml(response);
		Assert.assertEquals(result, validate_result);
	}

}
