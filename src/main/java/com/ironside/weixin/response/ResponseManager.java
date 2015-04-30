package com.ironside.weixin.response;

import java.net.URL;
import java.util.Date;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ironside.weixin.XmlParse;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;
import com.ironside.weixin.response.entity.MusicResponse;
import com.ironside.weixin.response.entity.NewsResponse;
import com.ironside.weixin.response.entity.ResponseEnum;
import com.ironside.weixin.response.entity.TextResponse;
import com.ironside.weixin.response.entity.VideoResponse;
import com.ironside.weixin.response.entity.VoiceResponse;

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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.textXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_TEXT_XML_STRING);
			return doTextResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.textXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.textXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_TEXT_XML_STRING);
			return doTextResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.textXmlFile);
		// 用完清除xml文件，防止再次解析
		this.textXmlFile = null;
		// 根据名字和值对应生成对象
		return doTextResponse(xmlProperty);
	}

	/**
	 * 基础解析
	 * 
	 * @param xmlProperty
	 *            解析后的xmlProperty
	 * @param entity
	 *            用于基础解析的实体
	 */
	private void doBaseAnalyze(AbstractXmlProperty xmlProperty,
			AbstractBaseResponse entity) {
		String toUserName = xmlProperty.getProperty(TextResponse.TO_USER_NAME);
		Assert.hasText(toUserName);
		String fromUserName = xmlProperty
				.getProperty(TextResponse.FORM_USER_NAME);
		Assert.hasText(fromUserName);
		String createTimeStr = xmlProperty
				.getProperty(TextResponse.CREATE_TIME);
		Assert.hasText(createTimeStr);
		// 将时间整形转换为对象
		Date createTime = new Date(Long.parseLong(createTimeStr));
		Assert.notNull(createTime);

		entity.setToUserName(toUserName);
		entity.setFromUserName(fromUserName);
		entity.setCreateTime(createTime);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 文本回复实体
	 */
	private TextResponse doTextResponse(AbstractXmlProperty xmlProperty) {
		TextResponse entity = new TextResponse();
		doBaseAnalyze(xmlProperty, entity);
		String content = xmlProperty.getProperty(TextResponse.CONTENT);
		Assert.hasText(content);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.TEXT.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		entity.setContent(content);
		return entity;
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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.imageXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_IMAGE_XML_STRING);
			return doImageResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.imageXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.imageXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_IMAGE_XML_STRING);
			return doImageResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.imageXmlFile);
		// 用完清除xml文件，防止再次解析
		this.imageXmlFile = null;
		// 根据名字和值对应生成对象
		return doImageResponse(xmlProperty);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 图片回复实体
	 */
	private ImageResponse doImageResponse(AbstractXmlProperty xmlProperty) {
		ImageResponse entity = new ImageResponse();
		doBaseAnalyze(xmlProperty, entity);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.IMAGE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		String mediaId = xmlProperty.getChild(0).getProperty(
				ImageResponse.MEDIA_ID);
		Assert.hasText(mediaId);
		ImageResponse.Image image = entity.new Image();
		image.setMediaId(mediaId);

		entity.addObject(image);
		return entity;
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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.voiceXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_VOICE_XML_STRING);
			return doVoiceResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.voiceXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.voiceXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_VOICE_XML_STRING);
			return doVoiceResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.voiceXmlFile);
		// 用完清除xml文件，防止再次解析
		this.voiceXmlFile = null;
		// 根据名字和值对应生成对象
		return doVoiceResponse(xmlProperty);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 语音回复实体
	 */
	private VoiceResponse doVoiceResponse(AbstractXmlProperty xmlProperty) {
		VoiceResponse entity = new VoiceResponse();
		doBaseAnalyze(xmlProperty, entity);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.VOICE.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		String mediaId = xmlProperty.getChild(0).getProperty(
				VoiceResponse.MEDIA_ID);
		Assert.hasText(mediaId);
		VoiceResponse.Voice voice = entity.new Voice();
		voice.setMediaId(mediaId);

		entity.addObject(voice);
		return entity;
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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.videoXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_VIDEO_XML_STRING);
			return doVideoResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.videoXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.videoXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_VIDEO_XML_STRING);
			return doVideoResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.videoXmlFile);
		// 用完清除xml文件，防止再次解析
		this.videoXmlFile = null;
		// 根据名字和值对应生成对象
		return doVideoResponse(xmlProperty);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 视频回复实体
	 */
	private VideoResponse doVideoResponse(AbstractXmlProperty xmlProperty) {
		VideoResponse entity = new VideoResponse();
		doBaseAnalyze(xmlProperty, entity);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.VIDEO.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		// 取得子节点
		AbstractXmlProperty childXmlProperty = xmlProperty.getChild(0);
		String mediaId = childXmlProperty.getProperty(VideoResponse.MEDIA_ID);
		Assert.hasText(mediaId);
		String title = childXmlProperty.getProperty(VideoResponse.TITLE);
		String description = childXmlProperty
				.getProperty(VideoResponse.DESCRIPTION);
		VideoResponse.Video video = entity.new Video();
		video.setMediaId(mediaId);
		video.setTitle(title);
		video.setDescription(description);

		entity.addObject(video);
		return entity;
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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.musicXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_MUSIC_XML_STRING);
			return doMusicResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.musicXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.musicXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_MUSIC_XML_STRING);
			return doMusicResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.musicXmlFile);
		// 用完清除xml文件，防止再次解析
		this.musicXmlFile = null;
		// 根据名字和值对应生成对象
		return doMusicResponse(xmlProperty);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 音乐回复实体
	 */
	private MusicResponse doMusicResponse(AbstractXmlProperty xmlProperty) {
		MusicResponse entity = new MusicResponse();
		doBaseAnalyze(xmlProperty, entity);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.MUSIC.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		// 取得子节点
		AbstractXmlProperty childXmlProperty = xmlProperty.getChild(0);
		String thumbMediaId = childXmlProperty
				.getProperty(MusicResponse.THUMB_MEDIA_ID);
		Assert.hasText(thumbMediaId);
		String title = childXmlProperty.getProperty(MusicResponse.TITLE);
		String description = childXmlProperty
				.getProperty(MusicResponse.DESCRIPTION);
		String musicUrl = childXmlProperty.getProperty(MusicResponse.MUSIC_URL);
		String hQMusicUrl = childXmlProperty
				.getProperty(MusicResponse.H_Q_MUSIC_URL);
		MusicResponse.Music music = entity.new Music();
		music.setThumbMediaId(thumbMediaId);
		music.setTitle(title);
		music.setDescription(description);
		music.setMusicUrl(musicUrl);
		music.setHQMusicUrl(hQMusicUrl);

		entity.addObject(music);
		return entity;
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
		AbstractXmlProperty xmlProperty;
		if (StringUtils.isEmpty(this.newsXmlFile)) {
			xmlProperty = xmlParse.parseString(DEFAULT_NEWS_XML_STRING);
			return doNewsResponse(xmlProperty);
		}
		URL url = ClassLoader.getSystemResource(this.newsXmlFile);
		// 如果xml文件不存在，使用默认xml文件，同时将xml文件置空
		if (url == null) {
			this.newsXmlFile = null;
			xmlProperty = xmlParse.parseString(DEFAULT_NEWS_XML_STRING);
			return doNewsResponse(xmlProperty);
		}
		// 取得名字和值信息
		xmlProperty = xmlParse.parseXmlFile(this.newsXmlFile);
		// 用完清除xml文件，防止再次解析
		this.newsXmlFile = null;
		// 根据名字和值对应生成对象
		return doNewsResponse(xmlProperty);
	}

	/**
	 * 根据名字和值对应生成对象
	 * 
	 * @param xmlProperty
	 *            xml名字和值对应
	 * @return 图文回复实体
	 */
	private NewsResponse doNewsResponse(AbstractXmlProperty xmlProperty) {
		NewsResponse entity = new NewsResponse();
		doBaseAnalyze(xmlProperty, entity);
		String articleCount = xmlProperty
				.getProperty(NewsResponse.ARTICLE_COUNT);
		Assert.hasText(articleCount);
		// 以子节点数量作为articleCount
		// 如果子节点数量超过限制，将数量设为最大值
		int childSize = xmlProperty.getChildSize();
		if (xmlProperty.getChildSize() > NewsResponse.NEWS_CHILD_MAX_SIZE) {
			childSize = NewsResponse.NEWS_CHILD_MAX_SIZE;
		}
		entity.setArticleCount(childSize);
		String msgType = xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE);
		Assert.hasText(msgType);
		Assert.isTrue(msgType.equals(ResponseEnum.NEWS.getMsgType()),
				String.format("文本回复xml中MsgType有误： %s", msgType));

		// 取得子节点
		AbstractXmlProperty childXmlProperty;
		NewsResponse.News news;
		String title;
		String description;
		String picUrl;
		String url;
		for (int i = 0; i < childSize; i++) {
			childXmlProperty = xmlProperty.getChild(i);
			news = entity.new News();
			title = childXmlProperty.getProperty(NewsResponse.TITLE);
			description = childXmlProperty
					.getProperty(NewsResponse.DESCRIPTION);
			picUrl = childXmlProperty.getProperty(NewsResponse.PIC_URL);
			url = childXmlProperty.getProperty(NewsResponse.URL);
			news.setTitle(title);
			news.setDescription(description);
			news.setPicUrl(picUrl);
			news.setUrl(url);
			entity.addObject(news);
		}

		return entity;
	}

}
