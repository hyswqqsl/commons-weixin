package com.ironside.weixin.passive.request;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.passive.request.DefaultPostProcess;
import com.ironside.weixin.passive.request.entity.AbstractBaseEntity;
import com.ironside.weixin.passive.request.entity.EntityType;
import com.ironside.weixin.passive.request.entity.EventMenuPicSysphotoEntity;
import com.ironside.weixin.passive.request.entity.EventMenuScancodePushEntity;
import com.ironside.weixin.passive.request.entity.EventScanSubscribeEntity;
import com.ironside.weixin.passive.request.entity.EventSubscribeEntity;
import com.ironside.weixin.passive.request.entity.ImageEntity;

/**
 * POST方式推送给微信公众账号的消息处理测试
 * @author 雪庭
 * @sine 1.0 at 2015年4月14日
 */
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
	 * 测试解析普通消息
	 */
	@Test
	public void testAnalyzeMessage() {
		// 构造xml
		String xml = "<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
				"<CreateTime>1348831860</CreateTime>" +
				"<MsgType><![CDATA[image]]></MsgType>" +
				"<PicUrl><![CDATA[this is a url]]></PicUrl>" +
				"<MediaId><![CDATA[media_id]]></MediaId>" +
				"<MsgId>1234567890123456</MsgId>" +
				"</xml>";
		// 调用解析
		AbstractBaseEntity entity = process.analyze(xml);
		// 验证结果
		Assert.assertEquals(entity.getMsgType(), EntityType.IMAGE);
		Assert.assertEquals("toUser", entity.getToUserName());
		Assert.assertTrue(entity instanceof ImageEntity);
		ImageEntity iEntity = (ImageEntity)entity;
		Assert.assertEquals("this is a url", iEntity.getPicUrl()); 
	}
	
	/**
	 * 测试解析事件消息
	 */
	@Test
	public void testAnalyzeEvent() {
		/** 测试关注/取消关注-订阅事件 */
		// 构造xml
		String xml = 
				"<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" +
				"<CreateTime>123456789</CreateTime>" +
				"<MsgType><![CDATA[event]]></MsgType>" +
				"<Event><![CDATA[subscribe]]></Event>" +
				"</xml>";
		// 调用解析
		AbstractBaseEntity entity = process.analyze(xml);
		EventSubscribeEntity sEntity = (EventSubscribeEntity)entity;
		Assert.assertEquals(sEntity.getMsgType(), EntityType.EVENT);
		Assert.assertEquals(sEntity.getEvent(), EntityType.EVENT_SUBSCRIBE);
		Assert.assertNull(entity.getEventKey());
		Assert.assertEquals("toUser", sEntity.getToUserName());
		/** 扫描带参数二维码-用户未关注时，进行关注后的事件 */
		// 构造xml
		xml = 
				"<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" +
				"<CreateTime>123456789</CreateTime>" +
				"<MsgType><![CDATA[event]]></MsgType>" +
				"<Event><![CDATA[subscribe]]></Event>" +
				"<EventKey><![CDATA[qrscene_123123]]></EventKey>" +
				"<Ticket><![CDATA[TICKET]]></Ticket>" +
				"</xml>";
		// 调用解析
		entity = process.analyze(xml);
		// 验证结果
		Assert.assertEquals(entity.getEvent(), EntityType.EVENT_SUBSCRIBE);
		Assert.assertEquals(entity.getEventKey(), "qrscene_123123");
		EventScanSubscribeEntity ssEntity = (EventScanSubscribeEntity)entity;
		Assert.assertEquals(ssEntity.getMsgType(), EntityType.EVENT);
		Assert.assertEquals("toUser", ssEntity.getToUserName());
	}
	
	@Test 
	public void testAnalyzeeEventMenu() {
		/** 自定义菜单-扫码推事件的事件推送 */
		// 构造xml
		String xml =
				"<xml>" +
				"<ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
				"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
				"<CreateTime>1408090502</CreateTime>" +
				"<MsgType><![CDATA[event]]></MsgType>" +
				"<Event><![CDATA[scancode_push]]></Event>" +
				"<EventKey><![CDATA[6]]></EventKey>" +
				"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" +
				"<ScanResult><![CDATA[1]]></ScanResult>" +
				"</ScanCodeInfo>" +
				"</xml>";
 		// 调用解析
		AbstractBaseEntity entity = process.analyze(xml);
		EventMenuScancodePushEntity sEntity = (EventMenuScancodePushEntity)entity;
		Assert.assertEquals("gh_e136c6e50636", sEntity.getToUserName());
		Assert.assertEquals(sEntity.getMsgType(), EntityType.EVENT);
		Assert.assertEquals(sEntity.getEvent(), EntityType.EVENT_MENU_SCANCODE_PUSH);
		Assert.assertNotNull(sEntity.getScanCodeInfo());
		/** 自定义菜单-弹出系统拍照发图的事件推送 */
		// 构造xml
		xml =
				"<xml>" +
				"<ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" +
				"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" +
				"<CreateTime>1408090651</CreateTime>" +
				"<MsgType><![CDATA[event]]></MsgType>" +
				"<Event><![CDATA[pic_sysphoto]]></Event>" +
				"<EventKey><![CDATA[6]]></EventKey>" +
				"<SendPicsInfo><Count>1</Count>" +
				"<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>" +
				"</item>" +
				"</PicList>" +
				"</SendPicsInfo>" +
				"</xml>";
		// 调用解析
		entity = process.analyze(xml);
		EventMenuPicSysphotoEntity sEntity2 = (EventMenuPicSysphotoEntity)entity;
		Assert.assertEquals("gh_e136c6e50636", sEntity2.getToUserName());
		Assert.assertEquals(sEntity2.getMsgType(), EntityType.EVENT);
		Assert.assertEquals(sEntity2.getEvent(), EntityType.EVENT_MENU_PIC_SYSPHOTO);
		Assert.assertNotNull(sEntity2.getSendPicsInfo());
		Assert.assertEquals(sEntity2.getSendPicsInfo().getPicList().size(), 1);
		Assert.assertEquals(sEntity2.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
	}
	
}
