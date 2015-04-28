package com.ironside.weixin.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.MusicResponse;
import com.ironside.weixin.response.entity.ResponseEnum;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VideoResponse;
import com.ironside.weixin.response.entity.VoiceResponse;

/**
 * 回复实体管理测试
 * @author 雪庭
 * @sine at 2015年4月14日
 */
public class ResponseManagerTest {
	
	/** 回复实体管理 */
	private ResponseManager responseManager;
	
	/** 回复实体xml文件 */
	private final String TEST_TEXT_XMLFILE = "testTextResponse.xml";
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
	public void testTextResponse() {
		// *** 测试取得默认消息 ***
		TextResponse textResponse = this.responseManager.getTextResponse();
		// 验证消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), ResponseEnum.TEXT.getMsgType());
		// *** 测试根据回复实体xml文件取得文本回复实体 ***
		this.responseManager.setTextXmlFile(TEST_TEXT_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是根据xml文件解析消息
		Assert.assertEquals(textResponse.getFromUserName(), "testFromUser");
		Assert.assertEquals(textResponse.getToUserName(), "testToUser");
		Assert.assertEquals(textResponse.getMsgtype(), ResponseEnum.TEXT.getMsgType());
		// *** 测试不存在的xml文件 ***
		this.responseManager.setTextXmlFile(NOEXISTING_TEST_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是默认消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgtype(), ResponseEnum.TEXT.getMsgType());
		Assert.assertNull(this.responseManager.textXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setTextXmlFile(WRONG_XMLFILE);
		try {
			textResponse = this.responseManager.getTextResponse();
		} catch(IllegalArgumentException e) {
			Assert.assertNull(this.responseManager.textXmlFile);
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
		this.responseManager.setTextXmlFile(TEST_TEXT_XMLFILE);
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
		this.responseManager.setTextXmlFile(TEST_TEXT_XMLFILE);
		textResponse = this.responseManager.getTextResponse(); 
		// 验证缓冲会更新
		Assert.assertTrue((textResponse.getContent().equals(cacheContent)==false));		
	}
	
	@Test
	public void testImageResponse() {
		// *** 测试取得默认消息 ***
		ImageResponse response = this.responseManager.getImageResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.IMAGE.getMsgType());
		Assert.assertEquals(response.getMediaId(), "media_id");
		// *** 测试根据回复实体xml文件取得图片回复实体 ***
		this.responseManager.setImageXmlFile("testImageResponse.xml");
		response = this.responseManager.getImageResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.IMAGE.getMsgType());
		Assert.assertEquals(response.getMediaId(), "test_media_id");
		Assert.assertNull(this.responseManager.imageXmlFile);
	}
	
	@Test
	public void testVoiceResponse() {
		// *** 测试取得默认消息 ***
		VoiceResponse response = this.responseManager.getVoiceResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.VOICE.getMsgType());
		Assert.assertEquals(response.getMediaId(), "media_id");
		// *** 测试根据回复实体xml文件取得图片回复实体 ***
		this.responseManager.setVoiceXmlFile("testVoiceResponse.xml");
		response = this.responseManager.getVoiceResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.VOICE.getMsgType());
		Assert.assertEquals(response.getMediaId(), "test_media_id");
		Assert.assertNull(this.responseManager.voiceXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setVoiceXmlFile(WRONG_XMLFILE);
		try {
			response = this.responseManager.getVoiceResponse();
		} catch(IllegalArgumentException e) {
			Assert.assertNull(this.responseManager.voiceXmlFile);
			response = this.responseManager.getVoiceResponse();
			Assert.assertEquals(response.getMsgtype(), ResponseEnum.VOICE.getMsgType());
			Assert.assertEquals(response.getMediaId(), "media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");		
	}
	
	@Test
	public void testVideoResponse() {
		// *** 测试取得默认消息 ***
		VideoResponse response = this.responseManager.getVideoResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.VIDEO.getMsgType());
		Assert.assertEquals(response.getMediaId(), "media_id");
		Assert.assertEquals(response.getTitle(), "title");
		Assert.assertEquals(response.getDescription(), "description");
		// *** 测试根据回复实体xml文件取得图片回复实体 ***
		this.responseManager.setVideoXmlFile("testVideoResponse.xml");
		response = this.responseManager.getVideoResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.VIDEO.getMsgType());
		Assert.assertEquals(response.getMediaId(), "test_media_id");
		Assert.assertNull(response.getTitle());
		Assert.assertEquals(response.getDescription(), "test_description");
		Assert.assertNull(this.responseManager.videoXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setVideoXmlFile(WRONG_XMLFILE);
		
		try {
			response = this.responseManager.getVideoResponse();
		} catch(IllegalArgumentException e) {
			Assert.assertNull(this.responseManager.videoXmlFile);
			response = this.responseManager.getVideoResponse();
			Assert.assertEquals(response.getMsgtype(), ResponseEnum.VIDEO.getMsgType());
			Assert.assertEquals(response.getMediaId(), "media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");				
	}
	
	@Test
	public void testMusicResponse() {
		// *** 测试取得默认消息 ***
		MusicResponse response = this.responseManager.getMusicResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.MUSIC.getMsgType());
		Assert.assertEquals(response.getTitle(), "title");
		Assert.assertEquals(response.getDescription(), "description");
		Assert.assertEquals(response.getMusicUrl(), "music_url");
		Assert.assertEquals(response.getHQMusicUrl(), "hq_music_url");
		Assert.assertEquals(response.getThumbMediaId(), "thumb_media_id");
		// *** 测试根据回复实体xml文件取得图片回复实体 ***
		this.responseManager.setMusicXmlFile("testMusicResponse.xml");
		response = this.responseManager.getMusicResponse();
		Assert.assertEquals(response.getMsgtype(), ResponseEnum.MUSIC.getMsgType());
		Assert.assertEquals(response.getThumbMediaId(), "test_thumb_media_id");
		Assert.assertNull(response.getTitle());
		Assert.assertEquals(response.getDescription(), "test_description");
		Assert.assertEquals(response.getMusicUrl(), "test_music_url");
		Assert.assertNull(this.responseManager.musicXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setMusicXmlFile(WRONG_XMLFILE);
		
		try {
			response = this.responseManager.getMusicResponse();
		} catch(IllegalArgumentException e) {
			Assert.assertNull(this.responseManager.musicXmlFile);
			response = this.responseManager.getMusicResponse();
			Assert.assertEquals(response.getMsgtype(), ResponseEnum.MUSIC.getMsgType());
			Assert.assertEquals(response.getThumbMediaId(), "thumb_media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");				
	}

}
