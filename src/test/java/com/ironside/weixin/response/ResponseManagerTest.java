package com.ironside.weixin.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.MusicResponse;
import com.ironside.weixin.response.entity.NewsResponse;
import com.ironside.weixin.response.entity.ResponseType;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VideoResponse;
import com.ironside.weixin.response.entity.VoiceResponse;
import com.thoughtworks.xstream.converters.reflection.AbstractReflectionConverter.UnknownFieldException;

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
		Assert.assertEquals(textResponse.getMsgType(), ResponseType.TEXT);
		// 测试XStream.alias重叠问题
		ImageResponse imageResponse = this.responseManager.getImageResponse();
		Assert.assertEquals(imageResponse.getFromUserName(), "fromUser");
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setTextXmlFile(TEST_TEXT_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是根据xml文件解析消息
		Assert.assertEquals(textResponse.getFromUserName(), "testFromUser");
		Assert.assertEquals(textResponse.getToUserName(), "testToUser");
		Assert.assertEquals(textResponse.getMsgType(), ResponseType.TEXT);
		// *** 测试不存在的xml文件 ***
		this.responseManager.setTextXmlFile(NOEXISTING_TEST_XMLFILE);
		textResponse = this.responseManager.getTextResponse();
		// 验证取得的是默认消息
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getToUserName(), "toUser");
		Assert.assertEquals(textResponse.getMsgType(), ResponseType.TEXT);
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
		
		// *** 测试以缓冲方式取得回复消息 ***
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
		Assert.assertEquals(response.getMsgType(), ResponseType.IMAGE);
		Assert.assertEquals(((ImageResponse.Image)response.getImage()).getMediaId(), "media_id");
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setImageXmlFile("testImageResponse.xml");
		response = this.responseManager.getImageResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.IMAGE);
		Assert.assertEquals(((ImageResponse.Image)response.getImage()).getMediaId(), "test_media_id");
		Assert.assertNull(this.responseManager.imageXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setImageXmlFile(WRONG_XMLFILE);
		try {
			response = this.responseManager.getImageResponse();
		} catch(UnknownFieldException e) {
			Assert.assertNull(this.responseManager.imageXmlFile);
			response = this.responseManager.getImageResponse();
			Assert.assertEquals(response.getMsgType(), ResponseType.IMAGE);
			Assert.assertEquals(((ImageResponse.Image)response.getImage()).getMediaId(), "media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");				
	}
	
	@Test
	public void testVoiceResponse() {
		// *** 测试取得默认消息 ***
		VoiceResponse response = this.responseManager.getVoiceResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.VOICE);
		Assert.assertEquals(((VoiceResponse.Voice)response.getVoice()).getMediaId(), "media_id");
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setVoiceXmlFile("testVoiceResponse.xml");
		response = this.responseManager.getVoiceResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.VOICE);
		Assert.assertEquals(((VoiceResponse.Voice)response.getVoice()).getMediaId(), "test_media_id");
		Assert.assertNull(this.responseManager.voiceXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setVoiceXmlFile(WRONG_XMLFILE);
		try {
			response = this.responseManager.getVoiceResponse();
		} catch(UnknownFieldException e) {
			Assert.assertNull(this.responseManager.voiceXmlFile);
			response = this.responseManager.getVoiceResponse();
			Assert.assertEquals(response.getMsgType(), ResponseType.VOICE);
			Assert.assertEquals(((VoiceResponse.Voice)response.getVoice()).getMediaId(), "media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");		
	}
	
	@Test
	public void testVideoResponse() {
		// *** 测试取得默认消息 ***
		VideoResponse response = this.responseManager.getVideoResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.VIDEO);
		// 子对象
		VideoResponse.Video video = (VideoResponse.Video)response.getVideo();
		Assert.assertEquals(video.getMediaId(), "media_id");
		Assert.assertEquals(video.getTitle(), "title");
		Assert.assertEquals(video.getDescription(), "description");
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setVideoXmlFile("testVideoResponse.xml");
		response = this.responseManager.getVideoResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.VIDEO);
		// 子对象
		video = (VideoResponse.Video)response.getVideo();
		Assert.assertEquals(video.getMediaId(), "test_media_id");
		Assert.assertNull(video.getTitle());
		Assert.assertEquals(video.getDescription(), "test_description");
		Assert.assertNull(this.responseManager.videoXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setVideoXmlFile(WRONG_XMLFILE);
		
		try {
			response = this.responseManager.getVideoResponse();
		} catch(UnknownFieldException e) {
			Assert.assertNull(this.responseManager.videoXmlFile);
			response = this.responseManager.getVideoResponse();
			Assert.assertEquals(response.getMsgType(), ResponseType.VIDEO);
			// 子对象
			video = (VideoResponse.Video)response.getVideo();
			Assert.assertEquals(video.getMediaId(), "media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");				
	}
	
	@Test
	public void testMusicResponse() {
		// *** 测试取得默认消息 ***
		MusicResponse response = this.responseManager.getMusicResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.MUSIC);
		// 子对象
		MusicResponse.Music music = (MusicResponse.Music)response.getMusic();
		Assert.assertEquals(music.getTitle(), "title");
		Assert.assertEquals(music.getDescription(), "description");
		Assert.assertEquals(music.getMusicUrl(), "music_url");
		Assert.assertEquals(music.getHQMusicUrl(), "hq_music_url");
		Assert.assertEquals(music.getThumbMediaId(), "thumb_media_id");
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setMusicXmlFile("testMusicResponse.xml");
		response = this.responseManager.getMusicResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.MUSIC);
		// 子对象
		music = (MusicResponse.Music)response.getMusic();
		Assert.assertEquals(music.getThumbMediaId(), "test_thumb_media_id");
		Assert.assertNull(music.getTitle());
		Assert.assertEquals(music.getDescription(), "test_description");
		Assert.assertEquals(music.getMusicUrl(), "test_music_url");
		Assert.assertNull(this.responseManager.musicXmlFile);
		// *** 测试有问题的实体xml文件
		this.responseManager.setMusicXmlFile(WRONG_XMLFILE);
		
		try {
			response = this.responseManager.getMusicResponse();
		} catch(UnknownFieldException e) {
			Assert.assertNull(this.responseManager.musicXmlFile);
			response = this.responseManager.getMusicResponse();
			Assert.assertEquals(response.getMsgType(), ResponseType.MUSIC);
			// 子对象
			music = (MusicResponse.Music)response.getMusic();
			Assert.assertEquals(music.getThumbMediaId(), "thumb_media_id");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");				
	}

	@Test
	public void testNewsResponse() {
		// *** 测试取得默认消息 ***
		NewsResponse response = this.responseManager.getNewsResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.NEWS);
		Assert.assertEquals(response.getArticleCount(), 2);
		NewsResponse.News news;
		for(int i=0;i<response.getArticleCount();i++) {
			news=(NewsResponse.News)response.getArticles().get(i);
			Assert.assertEquals(news.getTitle(), "title");
			Assert.assertEquals(news.getDescription(), "description");
			Assert.assertEquals(news.getPicUrl(), "picUrl");
			Assert.assertEquals(news.getUrl(), "url");
		}
		// *** 测试根据回复实体xml文件取得回复实体 ***
		this.responseManager.setNewsXmlFile("testNewsResponse.xml");
		response = this.responseManager.getNewsResponse();
		Assert.assertEquals(response.getMsgType(), ResponseType.NEWS);
		Assert.assertEquals(response.getArticleCount(), 3);		
		for(int i=0;i<response.getArticleCount();i++) {
			news=(NewsResponse.News)response.getArticles().get(i);
			Assert.assertNull(news.getTitle());
			Assert.assertEquals(news.getDescription(), "description");
			Assert.assertEquals(news.getPicUrl(), "picUrl");
			Assert.assertEquals(news.getUrl(), "url");
		}
		// *** 测试如果图文数超过限制，节点数将是最大值
		this.responseManager.setNewsXmlFile("testNewsMaxResponse.xml");
		response = this.responseManager.getNewsResponse();
		Assert.assertEquals(response.getArticleCount(), NewsResponse.NEWS_CHILD_MAX_SIZE);
		// *** 测试有问题的实体xml文件
		this.responseManager.setNewsXmlFile(WRONG_XMLFILE);
		try {
			response = this.responseManager.getNewsResponse();
		} catch(UnknownFieldException e) {
			Assert.assertNull(this.responseManager.newsXmlFile);
			response = this.responseManager.getNewsResponse();
			Assert.assertEquals(response.getMsgType(), ResponseType.NEWS);
			// 子对象
			news = (NewsResponse.News)response.getArticles().get(0);
			Assert.assertEquals(news.getTitle(), "title");
			return;
		}
		Assert.fail("测试有问题的实体xml文件出错");
	}
}
