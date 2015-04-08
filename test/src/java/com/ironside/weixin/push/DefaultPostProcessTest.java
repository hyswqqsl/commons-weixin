package com.ironside.weixin.push;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.push.entity.AbstractBaseEntity;
import com.ironside.weixin.push.entity.EntityEnum;
import com.ironside.weixin.push.entity.ImageEntity;

public class DefaultPostProcessTest {
	
	/** POST方式推送给微信公众账号的消息处理 */
	DefaultPostProcess process;

	@Before
	public void setUp() throws Exception {
		process = new DefaultPostProcess();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 测试解析
	 */
	@Test
	public void testAnalyze() {
		// 构造xml
		String xml = "<xml>	 <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName>" +
					"<CreateTime>1348831860</CreateTime> <MsgType><![CDATA[image]]></MsgType> <PicUrl><![CDATA[this is a url]]>" +
					"</PicUrl>	 <MediaId><![CDATA[media_id]]></MediaId> <MsgId>1234567890123456</MsgId> </xml>";	
		// 调用解析
		AbstractBaseEntity entity = process.analyze(xml);
		// 验证结果
		Assert.assertEquals(entity.getMsgEnum(), EntityEnum.IMAGE);
		Assert.assertEquals("toUser", entity.getToUserName());
		Assert.assertTrue(entity.getCreateTime() instanceof Date);
		Assert.assertTrue(entity instanceof ImageEntity);
		ImageEntity iEntity = (ImageEntity)entity;
		Assert.assertEquals("this is a url", iEntity.getPicUrl()); 
	}

}
