package com.ironside.weixin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.xsentity.ImageResponse;
import com.ironside.weixin.response.xsentity.NewsResponse;
import com.ironside.weixin.response.xsentity.TextResponse;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.thoughtworks.xstream.XStream;

public class XSTreamParstTest {

	private XStream xStream;

	public final String XML_STR_NOCHILD = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";

	public final String XML_STR_CHILD = "<xml>	<ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[media_id]]></MediaId></Image></xml>";

	public final String XML_STR_CHILDS = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount>"
			+ "<Articles><item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description>"
			+ "<PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item><item><Title><![CDATA[title]]></Title>"
			+ "<Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item>"
			+ "<item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item>"
			+ "</Articles></xml>";

	@Before
	public void setUp() throws Exception {
		xStream = new XStream();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFromNoChildXml() {
		TextResponse textResponse;
		xStream.alias("xml", TextResponse.class);
		textResponse = (TextResponse) xStream.fromXML(XML_STR_NOCHILD);
		Assert.assertEquals(textResponse.getFromUserName(), "fromUser");
		Assert.assertEquals(textResponse.getMsgType(), "text");
	}

	@Test
	public void testFromChildXml() {
		ImageResponse imageResponse;
		xStream.alias("xml", ImageResponse.class);
		imageResponse = (ImageResponse) xStream.fromXML(XML_STR_CHILD);
		Assert.assertEquals(imageResponse.getMsgType(), "image");
		Assert.assertEquals(imageResponse.Image.MediaId, "media_id");
	}

	@Test
	public void testFromChildsXml() {
		NewsResponse newsResponse;
		xStream.alias("xml", NewsResponse.class);
		xStream.alias("Articles", List.class);
		xStream.alias("item", NewsResponse.item.class);
		newsResponse = (NewsResponse)xStream.fromXML(XML_STR_CHILDS);
		Assert.assertEquals(newsResponse.getMsgType(), "news");
		java.util.List<NewsResponse.item> newses = newsResponse.Articles;
		Assert.assertEquals(newses.size(), 3);
		for (int i = 0; i < newses.size(); i++) {
			Assert.assertEquals(newses.get(i).Title, "title");
		}
	}
}
