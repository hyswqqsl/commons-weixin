package com.ironside.weixin.response;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.request.entity.AbstractBaseEntity;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.MusicResponse;
import com.ironside.weixin.response.entity.NewsResponse;
import com.ironside.weixin.response.entity.NewsResponse.News;
import com.ironside.weixin.response.entity.ResponseType;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VideoResponse;
import com.ironside.weixin.response.entity.VoiceResponse;
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
	// String textXmlFile;
	Properties textXmlFileProperties;
	/** 图片类型回复xml文件 */
	String imageXmlFile;
	/** 语音类型回复xml文件 */
	String voiceXmlFile;
	/** 视频类型回复xml文件 */
	String videoXmlFile;
	/** 音乐类型回复xml文件 */
	String musicXmlFile;
	/** 图文类型回复xml文件 */
	String newsXmlFile;

	/** 文本回复消息缓冲 */
	TextResponse defauleTextResponse;
	Map<String, TextResponse> textResponseMap;
	/** 图片回复消息缓冲 */
	ImageResponse imageResponse;
	/** 语音回复消息缓冲 */
	VoiceResponse voiceResponse;
	/** 视频回复消息缓冲 */
	VideoResponse videoResponse;
	/** 音乐回复消息缓冲 */
	MusicResponse musicResponse;
	/** 图文回复消息缓冲 */
	NewsResponse newsResponse;
	
	/** 用于替换的键对值 */
	private Properties properties;

	public ResponseManager() {
		this.textXmlFileProperties = new Properties();
		this.textResponseMap = new HashMap<String, TextResponse>();
	}
	
	/**
	 * 设置文本类型回复xml文件
	 * 
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
	 * @param textXmlFile
	 *            图片类型回复xml文件
	 */
	public void setImageXmlFile(String imageXmlFile) {
		Assert.hasText(imageXmlFile);
		this.imageXmlFile = imageXmlFile;
		// 清空缓存
		this.imageResponse = null;
	}

	/**
	 * 设置语音类型回复xml文件
	 * 
	 * @param voiceXmlFile
	 *            语音类型回复xml文件
	 */
	public void setVoiceXmlFile(String voiceXmlFile) {
		Assert.hasText(voiceXmlFile);
		this.voiceXmlFile = voiceXmlFile;
		// 清空缓存
		this.voiceResponse = null;
	}

	/**
	 * 设置视频类型回复xml文件
	 * 
	 * @param videoXmlFile
	 *            视频类型回复xml文件
	 */
	public void setVideoXmlFile(String videoXmlFile) {
		Assert.hasText(videoXmlFile);
		this.videoXmlFile = videoXmlFile;
		// 清空缓存
		this.videoResponse = null;
	}

	/**
	 * 设置音乐类型回复xml文件
	 * 
	 * @param videoXmlFile
	 *            音乐类型回复xml文件
	 */
	public void setMusicXmlFile(String musicXmlFile) {
		Assert.hasText(musicXmlFile);
		this.musicXmlFile = musicXmlFile;
		// 清空缓存
		this.musicResponse = null;
	}

	/**
	 * 设置图文类型回复xml文件
	 * 
	 * @param newsXmlFile
	 *            图文类型回复xml文件
	 */
	public void setNewsXmlFile(String newsXmlFile) {
		Assert.hasText(newsXmlFile);
		this.newsXmlFile = newsXmlFile;
		// 清空缓存
		this.newsResponse = null;
	}
	
	/**
	 * 取得默认文本回复实体</br> 如果缓冲中有实体，直接返回；否则从解析字符串
	 * @return
	 */
	TextResponse getTextResponse() {
		if (this.defauleTextResponse==null) {
			XStream xStream = new XStream();
			xStream.alias("xml", TextResponse.class);
			return (TextResponse) xStream.fromXML(DEFAULT_TEXT_XML_STRING);			
		}
		return this.defauleTextResponse;
	}
	
	/**
	 * 取得默认文本回复实体，根据请求实体设置fromUser和toUser
	 * @param requset 请求实体 
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
	 * @param key 回复实体键值
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
	 * @param key 回复实体键值
	 * @param request 请求实体
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
	 * @param str 字符串
	 * @return 替换过的字符串
	 */
	private String replace(String str) {
		if (this.properties==null) {
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
	 * @param properties 用于替换的键对值
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析，
	 * 同时将解析后的文本回复实体，加入到缓冲池中
	 * 
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
		String xmlFilePath = ClassLoader.getSystemResource(textXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 根据名字和值对应生成对象
		TextResponse response =  (TextResponse) xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.TEXT), String.format("text回复xml中MsgType有误： %s", response.getMsgType()));
		this.textResponseMap.put(key, response);
		return response;		
	}
	
	/*
	 * 取得图片回复实体
	 * 
	 * @return 图片回复实体
	 */
	ImageResponse getImageResponse() {
		if (this.imageResponse == null) {
			this.imageResponse = doGetImageResponse();
		}
		return this.imageResponse;
	}
	
	public ImageResponse getImageResponse(AbstractBaseEntity entity) {
		ImageResponse response = getImageResponse();
		response.setFromUserName(entity.getToUserName());
		response.setToUserName(entity.getFromUserName());
		return this.imageResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 图片回复实体
	 */
	private ImageResponse doGetImageResponse() {
		XStream xStream = new XStream();
		xStream.alias("xml", ImageResponse.class);
		if (StringUtils.isEmpty(this.imageXmlFile)) {
			return (ImageResponse)xStream.fromXML(DEFAULT_IMAGE_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.imageXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.imageXmlFile = null;
			return (ImageResponse)xStream.fromXML(DEFAULT_IMAGE_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(imageXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.imageXmlFile= null;
		// 根据名字和值对应生成对象
		ImageResponse response =  (ImageResponse)xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.IMAGE),
				String.format("image回复xml中MsgType有误： %s", response.getMsgType()));
		return response;		
	}

	/**
	 * 取得语音回复实体
	 * 
	 * @return 语音回复实体
	 */
	VoiceResponse getVoiceResponse() {
		if (this.voiceResponse == null) {
			this.voiceResponse = doGetVoiceResponse();
		}
		return this.voiceResponse;
	}
	
	public VoiceResponse getVoiceResponse(AbstractBaseEntity entity) {
		VoiceResponse response = getVoiceResponse();
		imageResponse.setFromUserName(entity.getToUserName());
		imageResponse.setToUserName(entity.getFromUserName());
		return response;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 语音回复实体
	 */
	private VoiceResponse doGetVoiceResponse() {
		XStream xStream = new XStream();
		xStream.alias("xml", VoiceResponse.class);
		if (StringUtils.isEmpty(this.voiceXmlFile)) {
			return (VoiceResponse)xStream.fromXML(DEFAULT_VOICE_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.voiceXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.voiceXmlFile = null;
			return (VoiceResponse)xStream.fromXML(DEFAULT_VOICE_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(voiceXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.voiceXmlFile= null;
		// 根据名字和值对应生成对象
		VoiceResponse response =  (VoiceResponse)xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.VOICE),
				String.format("voice回复xml中MsgType有误： %s", response.getMsgType()));
		return response;	
	}
	
	/**
	 * 取得视频回复实体
	 * 
	 * @return 视频回复实体
	 */
	public VideoResponse getVideoResponse() {
		if (this.videoResponse == null) {
			this.videoResponse = doGetVideoResponse();
		}
		return this.videoResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 视频回复实体
	 */
	private VideoResponse doGetVideoResponse() {
		XStream xStream = new XStream();
		xStream.alias("xml", VideoResponse.class);
		if (StringUtils.isEmpty(this.videoXmlFile)) {
			return (VideoResponse)xStream.fromXML(DEFAULT_VIDEO_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.videoXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.videoXmlFile = null;
			return (VideoResponse)xStream.fromXML(DEFAULT_VIDEO_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(videoXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.videoXmlFile= null;
		// 根据名字和值对应生成对象
		VideoResponse response =  (VideoResponse)xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.VIDEO),
				String.format("video回复xml中MsgType有误： %s", response.getMsgType()));
		return response;			
	}

	/**
	 * 取得音乐回复实体
	 * 
	 * @return 音乐回复实体
	 */
	public MusicResponse getMusicResponse() {
		if (this.musicResponse == null) {
			this.musicResponse = doGetMusicResponse();
		}
		return this.musicResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 音乐回复实体
	 */
	private MusicResponse doGetMusicResponse() {
		XStream xStream = new XStream();
		xStream.alias("xml", MusicResponse.class);
		if (StringUtils.isEmpty(this.musicXmlFile)) {
			return (MusicResponse)xStream.fromXML(DEFAULT_MUSIC_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.musicXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.musicXmlFile = null;
			return (MusicResponse)xStream.fromXML(DEFAULT_MUSIC_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(musicXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.musicXmlFile= null;
		// 根据名字和值对应生成对象
		MusicResponse response =  (MusicResponse)xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.MUSIC),
				String.format("music回复xml中MsgType有误： %s", response.getMsgType()));
		return response;	
	}

	
	/**
	 * 取得图文回复实体
	 * 
	 * @return 图文回复实体
	 */
	public NewsResponse getNewsResponse() {
		if (this.newsResponse == null) {
			this.newsResponse = doGetNewsResponse();
		}
		return this.newsResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 图文回复实体
	 */
	private NewsResponse doGetNewsResponse() {
		XStream xStream = new XStream();
		xStream.alias("xml", NewsResponse.class);
		xStream.alias("item", News.class);
		if (StringUtils.isEmpty(this.newsXmlFile)) {
			return (NewsResponse)xStream.fromXML(DEFAULT_NEWS_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.newsXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.newsXmlFile = null;
			return (NewsResponse)xStream.fromXML(DEFAULT_NEWS_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(newsXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.newsXmlFile= null;
		// 根据名字和值对应生成对象
		NewsResponse response =  (NewsResponse)xStream.fromXML(file);
		Assert.isTrue(response.getMsgType().equals(ResponseType.NEWS),
				String.format("news回复xml中MsgType有误： %s", response.getMsgType()));
		
		//整理图文消息个数
		doRepairNewsCount(response);
		return response;	
	}
	
	/**
	 * 整理图文消息个数
	 */
	private void doRepairNewsCount(NewsResponse response) {
		if (response.getArticles().size()>NewsResponse.NEWS_CHILD_MAX_SIZE) {
			int max = response.getArticles().size();
			int diff = max -NewsResponse.NEWS_CHILD_MAX_SIZE;
			response.setArticleCount(NewsResponse.NEWS_CHILD_MAX_SIZE);
			for(int i=0; i<diff; i++) {
				response.getArticles().remove(response.getArticles().size()-1);
			}
		}
		if (response.getArticleCount()!=response.getArticles().size()) {
			response.setArticleCount(response.getArticles().size());
		}
	}
	
	/**
	 * 回复实体转换为xml字符串
	 * @param response 回复实体
	 * @return xml字符串
	 */
	public String responseToXml(AbstractBaseResponse response) {
		String msgType = response.getMsgType();
		// 转换属性加上CDATA[[]]
		XStream xStream = new XStream(new CdataXppDriver());
		String xmlStr;
		switch(msgType) {
		case ResponseType.TEXT:
			xStream.alias("xml", TextResponse.class);
			TextResponse textResponse = (TextResponse)response;
			xmlStr = xStream.toXML(textResponse);
			break;
		case ResponseType.IMAGE:
			xStream.alias("xml", ImageResponse.class);
			ImageResponse imageResponse = (ImageResponse)response;
			xmlStr = xStream.toXML(imageResponse);
			break;
		case ResponseType.VOICE:
			xStream.alias("xml", VoiceResponse.class);
			VoiceResponse voiceResponse = (VoiceResponse)response;
			xmlStr = xStream.toXML(voiceResponse);
			break;
		case ResponseType.VIDEO:
			xStream.alias("xml", VideoResponse.class);
			VideoResponse videoResponse = (VideoResponse)response;
			xmlStr = xStream.toXML(videoResponse);
			break;
		case ResponseType.MUSIC:
			xStream.alias("xml", MusicResponse.class);
			MusicResponse musicResponse = (MusicResponse)response;
			xmlStr = xStream.toXML(musicResponse);
			break;
		case ResponseType.NEWS:
			xStream.alias("xml", NewsResponse.class);
			xStream.alias("item", News.class);
			NewsResponse newsResponse = (NewsResponse)response;
			xmlStr = xStream.toXML(newsResponse);
			break;	
		default:
			throw new IllegalStateException(String.format("response消息类型错误，无法转换成xml: %s", msgType));
		}
		return xmlStr;
	}

}
