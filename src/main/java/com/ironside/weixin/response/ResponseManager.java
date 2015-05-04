package com.ironside.weixin.response;

import java.io.File;
import java.net.URL;
import java.util.Date;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.XmlParse;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.ImageResponse.Image;
import com.ironside.weixin.response.entity.MusicResponse;
import com.ironside.weixin.response.entity.NewsResponse;
import com.ironside.weixin.response.entity.NewsResponse.News;
import com.ironside.weixin.response.entity.ResponseEnum;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VideoResponse;
import com.ironside.weixin.response.entity.VoiceResponse;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
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
	String textXmlFile;
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
	TextResponse textResponse;
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

	/** xml解析对象 */
	private XmlParse xmlParse;
	private XStream xStream;

	public ResponseManager() {
		this.xStream = new XStream();
	}
	
	/**
	 * 设置文本类型回复xml文件
	 * 
	 * @param textXmlFile
	 *            文本类型回复xml文件
	 */
	public void setTextXmlFile(String textXmlFile) {
		Assert.hasText(textXmlFile);
		this.textXmlFile = textXmlFile;
		// 清空缓存
		this.textResponse = null;
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
	 * 取得xml解析对象
	 * 
	 * @return xml解析对象
	 */
	public XmlParse getXmlParse() {
		return xmlParse;
	}

	/**
	 * 设置xml解析对象
	 * 
	 * @param xmlParse
	 *            xml解析对象
	 */
	public void setXmlParse(XmlParse xmlParse) {
		Assert.notNull(xmlParse);
		this.xmlParse = xmlParse;
	}

	/**
	 * 取得文本回复实体</br> 如果缓冲中有实体，直接返回；否则从xml文件中解析实体
	 * 
	 * @return 文本回复实体
	 */
	public TextResponse getTextResponse() {
		if (this.textResponse == null) {
			this.textResponse = doGetTextResponse();
		}
		return this.textResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 文本回复实体
	 */
	private TextResponse doGetTextResponse() {
		xStream.alias("xml", TextResponse.class);
		if (StringUtils.isEmpty(this.textXmlFile)) {
			return (TextResponse) xStream.fromXML(DEFAULT_TEXT_XML_STRING);
		}
		URL url = ClassLoader.getSystemResource(this.textXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.textXmlFile = null;
			return (TextResponse) xStream.fromXML(DEFAULT_TEXT_XML_STRING);
		}
		// 取得文件绝对路径
		String xmlFilePath = ClassLoader.getSystemResource(textXmlFile).getPath();
		File file = new File(xmlFilePath);
		
		// 用完清除xml文件，防止再次解析
		this.textXmlFile = null;
		// 根据名字和值对应生成对象
		TextResponse textResponse =  (TextResponse) xStream.fromXML(file);
		Assert.isTrue(textResponse.getMsgType().equals(ResponseEnum.TEXT.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", textResponse.getMsgType()));
		return textResponse;		
	}		

	/*
	 * 取得图片回复实体
	 * 
	 * @return 图片回复实体
	 */
	public ImageResponse getImageResponse() {
		if (this.imageResponse == null) {
			this.imageResponse = doGetImageResponse();
		}
		return this.imageResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 图片回复实体
	 */
	private ImageResponse doGetImageResponse() {
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
		ImageResponse imageResponse =  (ImageResponse)xStream.fromXML(file);
		Assert.isTrue(imageResponse.getMsgType().equals(ResponseEnum.IMAGE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", imageResponse.getMsgType()));
		return imageResponse;		
	}

	/**
	 * 取得语音回复实体
	 * 
	 * @return 语音回复实体
	 */
	public VoiceResponse getVoiceResponse() {
		if (this.voiceResponse == null) {
			this.voiceResponse = doGetVoiceResponse();
		}
		return this.voiceResponse;
	}

	/**
	 * 从xml文件中解析实体</br> 如果设置了xml文件，从xml文件中解析；否则从默认xml文件中解析
	 * 
	 * @return 语音回复实体
	 */
	private VoiceResponse doGetVoiceResponse() {
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
		VoiceResponse voiceResponse =  (VoiceResponse)xStream.fromXML(file);
		Assert.isTrue(voiceResponse.getMsgType().equals(ResponseEnum.VOICE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", voiceResponse.getMsgType()));
		return voiceResponse;	
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
		VideoResponse videoResponse =  (VideoResponse)xStream.fromXML(file);
		Assert.isTrue(videoResponse.getMsgType().equals(ResponseEnum.VIDEO.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", videoResponse.getMsgType()));
		return videoResponse;			
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
		MusicResponse musicResponse =  (MusicResponse)xStream.fromXML(file);
		Assert.isTrue(musicResponse.getMsgType().equals(ResponseEnum.MUSIC.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", musicResponse.getMsgType()));
		return musicResponse;	
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
		NewsResponse newsResponse =  (NewsResponse)xStream.fromXML(file);
		Assert.isTrue(newsResponse.getMsgType().equals(ResponseEnum.NEWS.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", newsResponse.getMsgType()));	
		
		//整理图文消息个数
		doRepairNewsCount(newsResponse);
		return newsResponse;	
	}
	
	/**
	 * 整理图文消息个数
	 */
	private void doRepairNewsCount(NewsResponse newsResponse) {
		if (newsResponse.getArticles().size()>NewsResponse.NEWS_CHILD_MAX_SIZE) {
			int max = newsResponse.getArticles().size();
			int diff = max -NewsResponse.NEWS_CHILD_MAX_SIZE;
			newsResponse.setArticleCount(NewsResponse.NEWS_CHILD_MAX_SIZE);
			for(int i=0; i<diff; i++) {
				newsResponse.getArticles().remove(newsResponse.getArticles().size()-1);
			}
		}
		if (newsResponse.getArticleCount()!=newsResponse.getArticles().size()) {
			newsResponse.setArticleCount(newsResponse.getArticles().size());
		}
	}

}
