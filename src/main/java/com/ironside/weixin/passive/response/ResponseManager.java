package com.ironside.weixin.passive.response;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.passive.request.entity.AbstractBaseEntity;
import com.ironside.weixin.passive.response.entity.AbstractBaseResponse;
import com.ironside.weixin.passive.response.entity.ImageResponse;
import com.ironside.weixin.passive.response.entity.ImageResponse.Image;
import com.ironside.weixin.passive.response.entity.MusicResponse;
import com.ironside.weixin.passive.response.entity.NewsResponse;
import com.ironside.weixin.passive.response.entity.NewsResponse.News;
import com.ironside.weixin.passive.response.entity.ResponseType;
import com.ironside.weixin.passive.response.entity.TextResponse;
import com.ironside.weixin.passive.response.entity.VideoResponse;
import com.ironside.weixin.passive.response.entity.VoiceResponse;
import com.thoughtworks.xstream.XStream;

/**
 * 回复实体管理
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年4月9日
 */
public class ResponseManager {

	/** 默认文本类型回复字符串 */
	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[text]]></MsgType>
	 * <Content><![CDATA[你好]]></Content> </xml>
	 */
	private final String DEFAULT_TEXT_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";

	/** 默认图片类型回复字符串 */
	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[image]]></MsgType>
	 * <Image> <MediaId><![CDATA[media_id]]></MediaId> </Image> </xml>
	 */
	private final String DEFAULT_IMAGE_XML_STRING = "<xml>	<ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[media_id]]></MediaId></Image></xml>";

	/** 默认语音类型回复字符串 */
	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[voice]]></MsgType>
	 * <Voice> <MediaId><![CDATA[media_id]]></MediaId> </Voice> </xml>
	 */
	private final String DEFAULT_VOICE_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[voice]]></MsgType><Voice><MediaId><![CDATA[media_id]]></MediaId></Voice></xml>";

	/** 默认视频类型回复字符串 */
	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[video]]></MsgType>
	 * <Video> <MediaId><![CDATA[media_id]]></MediaId>
	 * <Title><![CDATA[title]]></Title>
	 * <Description><![CDATA[description]]></Description> </Video> </xml>
	 */
	private final String DEFAULT_VIDEO_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[media_id]]></MediaId><Title><![CDATA[title]]></Title>"
			+ "<Description><![CDATA[description]]></Description></Video></xml>";

	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[music]]></MsgType>
	 * <Music> <Title><![CDATA[title]]></Title>
	 * <Description><![CDATA[description]]></Description>
	 * <MusicUrl><![CDATA[music_url]]></MusicUrl>
	 * <HQMusicUrl><![CDATA[hq_music_url]]></HQMusicUrl>
	 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId> </Music> </xml>
	 */
	private final String DEFAULT_MUSIC_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[music]]></MsgType><Music><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description>"
			+ "<MusicUrl><![CDATA[music_url]]></MusicUrl><HQMusicUrl><![CDATA[hq_music_url]]></HQMusicUrl><ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId></Music></xml>";

	/*
	 * <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
	 * <FromUserName><![CDATA[fromUser]]></FromUserName>
	 * <CreateTime>12345678</CreateTime> <MsgType><![CDATA[news]]></MsgType>
	 * <ArticleCount>2</ArticleCount> <Articles> <item>
	 * <Title><![CDATA[title]]></Title>
	 * <Description><![CDATA[description]]></Description>
	 * <PicUrl><![CDATA[picUrl]]></PicUrl> <Url><![CDATA[url]]></Url> </item>
	 * <item> <Title><![CDATA[title]]></Title>
	 * <Description><![CDATA[description]]></Description>
	 * <PicUrl><![CDATA[picUrl]]></PicUrl> <Url><![CDATA[url]]></Url> </item>
	 * </Articles> </xml>
	 */
	private final String DEFAULT_NEWS_XML_STRING = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount>"
			+ "<Articles><item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description>"
			+ "<PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item><item><Title><![CDATA[title]]></Title>"
			+ "<Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item>"
			+ "</Articles></xml>";

	/** 文本类型回复xml文件 */
	Properties textXmlFileProperties;
	/** 图片类型回复xml文件 */
	Properties imageXmlFileProperties;
	/** 语音类型回复xml文件 */
	Properties voiceXmlFileProperties;
	/** 视频类型回复xml文件 */
	Properties videoXmlFileProperties;
	/** 音乐类型回复xml文件 */
	Properties musicXmlFileProperties;
	/** 图文类型回复xml文件 */
	Properties newsXmlFileProperties;

	/** 文本回复消息缓冲 */
	TextResponse defaultTextResponse;
	Map<String, TextResponse> textResponseMap;
	/** 图片回复消息缓冲 */
	ImageResponse defaultImageResponse;
	Map<String, ImageResponse> imageResponseMap;
	/** 语音回复消息缓冲 */
	VoiceResponse defaultVoiceResponse;
	Map<String, VoiceResponse> voiceResponseMap;
	/** 视频回复消息缓冲 */
	VideoResponse defaultVideoResponse;
	Map<String, VideoResponse> videoResponseMap;
	/** 音乐回复消息缓冲 */
	MusicResponse defaultMusicResponse;
	Map<String, MusicResponse> musicResponseMap;
	/** 图文回复消息缓冲 */
	NewsResponse defaultNewsResponse;
	Map<String, NewsResponse> newsResponseMap;

	/** 用于替换的键对值 */
	private Properties properties;

	public ResponseManager() {
		this.textXmlFileProperties = new Properties();
		this.imageXmlFileProperties = new Properties();
		this.voiceXmlFileProperties = new Properties();
		this.videoXmlFileProperties = new Properties();
		this.musicXmlFileProperties = new Properties();
		this.newsXmlFileProperties = new Properties();
		this.textResponseMap = new HashMap<String, TextResponse>();
		this.imageResponseMap = new HashMap<String, ImageResponse>();
		this.voiceResponseMap = new HashMap<String, VoiceResponse>();
		this.videoResponseMap = new HashMap<String, VideoResponse>();
		this.musicResponseMap = new HashMap<String, MusicResponse>();
		this.newsResponseMap = new HashMap<String, NewsResponse>();
	}

	/**
	 * 设置文本类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param textXmlFile
	 *            文本类型回复xml文件
	 */
	public void setTextXmlFile(String key, String textXmlFile) {
		Assert.hasText(key);
		Assert.hasText(textXmlFile);
		this.textXmlFileProperties.setProperty(key, textXmlFile);
		// 清空缓存
		this.textResponseMap.remove(key);
	}

	/**
	 * 设置图片类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param textXmlFile
	 *            图片类型回复xml文件
	 */
	public void setImageXmlFile(String key, String imageXmlFile) {
		Assert.hasText(key);
		Assert.hasText(imageXmlFile);
		this.imageXmlFileProperties.setProperty(key, imageXmlFile);
		// 清空缓存
		this.imageResponseMap.remove(key);
	}

	/**
	 * 设置语音类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param voiceXmlFile
	 *            语音类型回复xml文件
	 */
	public void setVoiceXmlFile(String key, String voiceXmlFile) {
		Assert.hasText(key);
		Assert.hasText(voiceXmlFile);
		this.voiceXmlFileProperties.setProperty(key, voiceXmlFile);
		// 清空缓存
		this.voiceResponseMap.remove(key);
	}

	/**
	 * 设置视频类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param videoXmlFile
	 *            视频类型回复xml文件
	 */
	public void setVideoXmlFile(String key, String videoXmlFile) {
		Assert.hasText(key);
		Assert.hasText(videoXmlFile);
		this.videoXmlFileProperties.setProperty(key, videoXmlFile);
		// 清空缓存
		this.videoResponseMap.remove(key);
	}

	/**
	 * 设置音乐类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param videoXmlFile
	 *            音乐类型回复xml文件
	 */
	public void setMusicXmlFile(String key, String musicXmlFile) {
		Assert.hasText(key);
		Assert.hasText(musicXmlFile);
		this.musicXmlFileProperties.setProperty(key, musicXmlFile);
		// 清空缓存
		this.musicResponseMap.remove(key);
	}

	/**
	 * 设置图文类型回复xml文件
	 * 
	 * @param key
	 *            xml文件键值
	 * @param newsXmlFile
	 *            图文类型回复xml文件
	 */
	public void setNewsXmlFile(String key, String newsXmlFile) {
		Assert.hasText(key);		
		Assert.hasText(newsXmlFile);
		this.newsXmlFileProperties.setProperty(key, newsXmlFile);
		// 清空缓存
		this.newsResponseMap.remove(key);
	}

	/**
	 * 取得默认文本回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return
	 */
	TextResponse getTextResponse() {
		if (this.defaultTextResponse == null) {
			XStream xStream = new XStream();
			xStream.alias("xml", TextResponse.class);
			this.defaultTextResponse = (TextResponse) xStream
					.fromXML(DEFAULT_TEXT_XML_STRING);
		}
		return this.defaultTextResponse.clone();
	}

	/**
	 * 取得默认文本回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 文本回复实体
	 */
	public TextResponse getTextResponse(AbstractBaseEntity entity) {
		TextResponse response = getTextResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return response;
	}

	/**
	 * 取得文本回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 文本回复实体
	 */
	TextResponse getTextResponse(String key) {
		TextResponse textResponse;
		if (this.textResponseMap.get(key) == null) {
			textResponse = doGetTextResponse(key);
		} else {
			textResponse = this.textResponseMap.get(key);
		}
		// 克隆副本，替换内容
		TextResponse response = textResponse.clone();
		response.setContent(replace(response.getContent()));
		return response;
	}

	/**
	 * 取得文本回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param key
	 *            回复实体键值
	 * @param request
	 *            请求实体
	 * @return 文本回复实体
	 */
	public TextResponse getTextResponse(String key, AbstractBaseEntity request) {
		TextResponse response = getTextResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;
	}

	/**
	 * 用键对值替换字符串
	 * 
	 * @param str
	 *            字符串
	 * @return 替换过的字符串
	 */
	private String replace(String str) {
		if (this.properties == null) {
			return str;
		}
		Enumeration<Object> keys = this.properties.keys();
		String key;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			str = str.replaceAll(key, this.properties.getProperty(key));
		}
		return str;
	}

	/**
	 * 设置用于替换的键对值
	 * 
	 * @param properties
	 *            用于替换的键对值
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析，
	 * 同时将解析后的文本回复实体，加入到缓冲池中
	 *
	 * @param key
	 *            回复实体键值
	 * @return 文本回复实体
	 */
	private TextResponse doGetTextResponse(String key) {
		Assert.hasText(key);
		XStream xStream = new XStream();
		xStream.alias("xml", TextResponse.class);
		String textXmlFile = this.textXmlFileProperties.getProperty(key);
		// 如果没有对应xml文件
		if (StringUtils.isEmpty(textXmlFile)) {
			return getTextResponse();
		}
		URL url = ClassLoader.getSystemResource(textXmlFile);
		// 如果xml文件不存在，使用默认实体，同时将xml文件置空
		if (url == null) {
			return getTextResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(textXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		TextResponse response = (TextResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.TEXT),
				String.format("text回复xml中MsgType有误： %s", response.getMsgType()));
		this.textResponseMap.put(key, response);
		return response;
	}

	/*
	 * 取得默认图片回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return 图片回复实体
	 */
	ImageResponse getImageResponse() {
		if (this.defaultImageResponse == null) {
			XStream xStream = new XStream();
			xStream.alias("xml", ImageResponse.class);
			this.defaultImageResponse = (ImageResponse) xStream
					.fromXML(DEFAULT_IMAGE_XML_STRING);
		}
		return this.defaultImageResponse;
	}

	/**
	 * 取得默认图片回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 图片回复实体图片回复实体
	 */
	public ImageResponse getImageResponse(AbstractBaseEntity entity) {
		ImageResponse response = getImageResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		ImageResponse.Image image = response.new Image();
		image.setMediaId("f_79hUmXIreErst9BLJDe2i0HZUKPQF9kPs9VOaRm_JCbM4gsc04VuIdRRdb82dg");
		response.setImage(image);
		return response;
	}

	/**
	 * 取得图片回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 图片回复实体
	 */
	ImageResponse getImageResponse(String key) {
		ImageResponse response;
		if (this.imageResponseMap.get(key) == null) {
			response = doGetImageResponse(key);
		} else {
			response = this.imageResponseMap.get(key);
		}
		return response;
	}

	/**
	 * 取得图片回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param key
	 *            回复实体键值
	 * @param request
	 *            请求实体
	 * @return 图片回复实体
	 */
	public ImageResponse getImageResponse(String key, AbstractBaseEntity request) {
		ImageResponse response = getImageResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 图片回复实体
	 */
	private ImageResponse doGetImageResponse(String key) {
		Assert.hasText(key);
		XStream xStream = new XStream();
		xStream.alias("xml", ImageResponse.class);
		String imageXmlFile = this.imageXmlFileProperties.getProperty(key);
		// 如果没有对应xml文件
		if (StringUtils.isEmpty(imageXmlFile)) {
			return getImageResponse();
		}
		URL url = ClassLoader.getSystemResource(imageXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			return getImageResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(imageXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		ImageResponse response = (ImageResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.IMAGE), String
				.format("image回复xml中MsgType有误： %s", response.getMsgType()));
		this.imageResponseMap.put(key, response);
		return response;
	}

	/*
	 * 取得默认语音回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return 语音回复实体
	 */
	VoiceResponse getVoiceResponse() {
		if (this.defaultVoiceResponse == null) {
			XStream xStream = new XStream();
			xStream.alias("xml", VoiceResponse.class);
			this.defaultVoiceResponse = (VoiceResponse) xStream
					.fromXML(DEFAULT_VOICE_XML_STRING);
		}
		return this.defaultVoiceResponse;
	}

	/**
	 * 取得默认语音回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 语音回复实体图片回复实体
	 */
	public VoiceResponse getVoiceResponse(AbstractBaseEntity entity) {
		VoiceResponse response = getVoiceResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return response;
	}

	/**
	 * 取得语音回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 语音回复实体
	 */
	VoiceResponse getVoiceResponse(String key) {
		VoiceResponse response;
		if (this.voiceResponseMap.get(key) == null) {
			response = doGetVoiceResponse(key);
		} else {
			response = this.voiceResponseMap.get(key);
		}
		return response;
	}

	/**
	 * 取得语音回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param key
	 *            回复实体键值
	 * @param request
	 *            请求实体
	 * @return 语音回复实体
	 */
	public VoiceResponse getVoiceResponse(String key, AbstractBaseEntity request) {
		VoiceResponse response = getVoiceResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 *
	 * @param key
	 *            回复实体键值
	 * @return 语音回复实体
	 */
	private VoiceResponse doGetVoiceResponse(String key) {
		Assert.hasText(key);
		XStream xStream = new XStream();
		xStream.alias("xml", VoiceResponse.class);
		String voiceXmlFile = this.voiceXmlFileProperties.getProperty(key);
		if (StringUtils.isEmpty(voiceXmlFile)) {
			return getVoiceResponse();
		}
		URL url = ClassLoader.getSystemResource(voiceXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			return getVoiceResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(voiceXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		VoiceResponse response = (VoiceResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.VOICE), String
				.format("voice回复xml中MsgType有误： %s", response.getMsgType()));
		this.voiceResponseMap.put(key, response);
		return response;
	}

	/*
	 * 取得默认视频回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return 视频回复实体
	 */
	VideoResponse getVideoResponse() {
		if (this.defaultVideoResponse == null) {
			XStream xStream = new XStream();
			xStream.alias("xml", VideoResponse.class);
			this.defaultVideoResponse = (VideoResponse) xStream
					.fromXML(DEFAULT_VIDEO_XML_STRING);
		}
		return this.defaultVideoResponse;
	}

	/**
	 * 取得默认视频回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 视频回复实体图片回复实体
	 */
	public VideoResponse getVideoResponse(AbstractBaseEntity entity) {
		VideoResponse response = getVideoResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return response;
	}

	/**
	 * 取得视频回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 视频回复实体
	 */
	VideoResponse getVideoResponse(String key) {
		VideoResponse response;
		if (this.videoResponseMap.get(key) == null) {
			response = doGetVideoResponse(key);
		} else {
			response = this.videoResponseMap.get(key);
		}
		return response;
	}
	
	/**
	 * 取得视频回复实体，根据请求实体设置fromUser和toUser
	 * @param key 回复实体键值
	 * @param request 请求实体
	 * @return 视频回复实体
	 */
	public VideoResponse getVideoResponse(String key, AbstractBaseEntity request) {
		VideoResponse response = getVideoResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;		
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 视频回复实体
	 */
	private VideoResponse doGetVideoResponse(String key) {
		Assert.hasText(key);
		XStream xStream = new XStream();
		xStream.alias("xml", VideoResponse.class);
		String videoXmlFile = this.videoXmlFileProperties.getProperty(key);
		if (StringUtils.isEmpty(videoXmlFile)) {
			return getVideoResponse();
		}
		URL url = ClassLoader.getSystemResource(videoXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			return getVideoResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(videoXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		VideoResponse response = (VideoResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.VIDEO), String
				.format("video回复xml中MsgType有误： %s", response.getMsgType()));
		this.videoResponseMap.put(key, response);
		return response;
	}

	/*
	 * 取得默认音乐回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return 音乐回复实体
	 */
	MusicResponse getMusicResponse() {
		if (this.defaultMusicResponse==null) {
			XStream xStream = new XStream();
			xStream.alias("xml", MusicResponse.class);
			this.defaultMusicResponse = (MusicResponse) xStream.fromXML(DEFAULT_MUSIC_XML_STRING);			
		}
		return this.defaultMusicResponse;
	}
	
	/**
	 * 取得默认音乐回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 音乐回复实体
	 */
	public MusicResponse getMusicResponse(AbstractBaseEntity entity) {
		MusicResponse response = getMusicResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return response;
	}
	
	/**
	 * 取得音乐回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 音乐回复实体
	 */
	MusicResponse getMusicResponse(String key) {
		MusicResponse response;
		if (this.musicResponseMap.get(key) == null) {
			response = doGetMusicResponse(key);
		} else {
			response = this.musicResponseMap.get(key);
		}
		return response;
	}
	
	/**
	 * 取得音乐回复实体，根据请求实体设置fromUser和toUser
	 * @param key 回复实体键值
	 * @param request 请求实体
	 * @return 音乐回复实体
	 */
	public MusicResponse getMusicResponse(String key, AbstractBaseEntity request) {
		MusicResponse response = getMusicResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;		
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 *
	 * @param key 回复实体键值
	 * @return 音乐回复实体
	 */
	private MusicResponse doGetMusicResponse(String key) {
		Assert.hasText(key);		
		XStream xStream = new XStream();
		xStream.alias("xml", MusicResponse.class);
		String musicXmlFile = this.musicXmlFileProperties.getProperty(key);
		if (StringUtils.isEmpty(musicXmlFile)) {
			return getMusicResponse();
		}
		URL url = ClassLoader.getSystemResource(musicXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			return getMusicResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(musicXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		MusicResponse response = (MusicResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.MUSIC), String
				.format("music回复xml中MsgType有误： %s", response.getMsgType()));
		this.musicResponseMap.put(key, response);
		return response;
	}

	/*
	 * 取得默认图文回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * 
	 * @return 图文回复实体
	 */
	NewsResponse getNewsResponse() {
		if (this.defaultNewsResponse==null) {
			XStream xStream = new XStream();
			xStream.alias("xml", NewsResponse.class);
			xStream.alias("item", News.class);
			this.defaultNewsResponse = (NewsResponse) xStream.fromXML(DEFAULT_NEWS_XML_STRING);			
		}
		return this.defaultNewsResponse;
	}
	
	/**
	 * 取得默认图文回复实体，根据请求实体设置fromUser和toUser
	 * 
	 * @param requset
	 *            请求实体
	 * @return 图文回复实体
	 */
	public NewsResponse getNewsResponse(AbstractBaseEntity entity) {
		NewsResponse response = getNewsResponse();
		Assert.notNull(response);
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return response;
	}
	 
	/**
	 * 取得图文回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @param key
	 *            回复实体键值
	 * @return 图文回复实体
	 */
	NewsResponse getNewsResponse(String key) {
		NewsResponse response;
		if (this.newsResponseMap.get(key) == null) {
			response = doGetNewsResponse(key);
		} else {
			response = this.newsResponseMap.get(key);
		}
		return response;
	}
	
	/**
	 * 取得图文回复实体，根据请求实体设置fromUser和toUser
	 * @param key 回复实体键值
	 * @param request 请求实体
	 * @return 图文回复实体
	 */
	public NewsResponse getNewsResponse(String key, AbstractBaseEntity request) {
		NewsResponse response = getNewsResponse(key);
		Assert.notNull(response);
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		return response;		
	}
	

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 *
	 * @param key 回复实体键值
	 * @return 图文回复实体
	 */
	private NewsResponse doGetNewsResponse(String key) {
		Assert.hasText(key);		
		XStream xStream = new XStream();
		xStream.alias("xml", NewsResponse.class);
		xStream.alias("item", News.class);
		String newsXmlFile = this.newsXmlFileProperties.getProperty(key);
		if (StringUtils.isEmpty(newsXmlFile)) {
			return getNewsResponse();
		}
		URL url = ClassLoader.getSystemResource(newsXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			return getNewsResponse();
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(newsXmlFile)
				.getPath();
		File file = new File(xmlFilePath);

		// 根据名字和值对应生成对象
		NewsResponse response = (NewsResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.NEWS),
				String.format("news回复xml中MsgType有误： %s", response.getMsgType()));
		// 整理图文消息个数
		doRepairNewsCount(response);
		this.newsResponseMap.put(key, response);
		return response;
	}

	/**
	 * 整理图文消息个数
	 */
	private void doRepairNewsCount(NewsResponse response) {
		if (response.getArticles().size() > NewsResponse.NEWS_CHILD_MAX_SIZE) {
			int max = response.getArticles().size();
			int diff = max - NewsResponse.NEWS_CHILD_MAX_SIZE;
			response.setArticleCount(NewsResponse.NEWS_CHILD_MAX_SIZE);
			for (int i = 0; i < diff; i++) {
				response.getArticles()
						.remove(response.getArticles().size() - 1);
			}
		}
		if (response.getArticleCount() != response.getArticles().size()) {
			response.setArticleCount(response.getArticles().size());
		}
	}

	/**
	 * 回复实体转换为xml字符串
	 * 
	 * @param response
	 *            回复实体
	 * @return xml字符串
	 */
	public String responseToXml(AbstractBaseResponse response) {
		String msgType = response.getMsgType();
		// 转换属性加上CDATA[[]]
		XStream xStream = new XStream(new CdataXppDriver());
		String xmlStr;
		switch (msgType) {
		case ResponseType.TEXT:
			xStream.alias("xml", TextResponse.class);
			TextResponse textResponse = (TextResponse) response;
			xmlStr = xStream.toXML(textResponse);
			break;
		case ResponseType.IMAGE:
			xStream.alias("xml", ImageResponse.class);
			ImageResponse imageResponse = (ImageResponse) response;
			xmlStr = xStream.toXML(imageResponse);
			break;
		case ResponseType.VOICE:
			xStream.alias("xml", VoiceResponse.class);
			VoiceResponse voiceResponse = (VoiceResponse) response;
			xmlStr = xStream.toXML(voiceResponse);
			break;
		case ResponseType.VIDEO:
			xStream.alias("xml", VideoResponse.class);
			VideoResponse videoResponse = (VideoResponse) response;
			xmlStr = xStream.toXML(videoResponse);
			break;
		case ResponseType.MUSIC:
			xStream.alias("xml", MusicResponse.class);
			MusicResponse musicResponse = (MusicResponse) response;
			xmlStr = xStream.toXML(musicResponse);
			break;
		case ResponseType.NEWS:
			xStream.alias("xml", NewsResponse.class);
			xStream.alias("item", News.class);
			NewsResponse newsResponse = (NewsResponse) response;
			xmlStr = xStream.toXML(newsResponse);
			break;
		default:
			throw new IllegalStateException(String.format(
					"response消息类型错误，无法转换成xml: %s", msgType));
		}
		return xmlStr;
	}

}
