package com.ironside.weixin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.AbstractXmlProperty;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.ImageResponse;

public class XmlParseTest {
	
	private XmlParse xmlParse;

	public final String XML_STR_NOCHILD = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
	
	public final String XML_STR_CHILD = "<xml>	<ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>12345678</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[media_id]]></MediaId></Image></xml>";
	
	public final String XML_STR_CHILDS = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount>" +
			"<Articles><item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description>" +
			"<PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item><item><Title><![CDATA[title]]></Title>" +
			"<Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item>" +
			"<item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item>" +
			"</Articles></xml>";
	
	@Before
	public void setUp() throws Exception {
		this.xmlParse = new XmlParse();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() {
		// *** 测试没有子节点的xml ***
		AbstractXmlProperty xmlProperty = this.xmlParse.parseString(XML_STR_NOCHILD);
		Assert.assertEquals(xmlProperty.getChildSize(), 0);
		Assert.assertEquals(xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE), "text");
		Assert.assertEquals(xmlProperty.getName(), null);
		// *** 测试有一个子节点的xml ***
		xmlProperty = this.xmlParse.parseString(XML_STR_CHILD);
		Assert.assertEquals(xmlProperty.getChildSize(), 1);
		Assert.assertEquals(xmlProperty.getName(), "Image");
		Assert.assertEquals(xmlProperty.getChild(0).getName(), "Image");
		Assert.assertEquals(xmlProperty.getChild(0).getProperty(ImageResponse.MEDIA_ID), "media_id");
		// *** 测试有多个子节点的xml ***
		xmlProperty = this.xmlParse.parseString(XML_STR_CHILDS);
		Assert.assertEquals(xmlProperty.getChildSize(), 3);
		Assert.assertEquals(xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE), "news");
		Assert.assertEquals(xmlProperty.getName(), "Articles");
		for (int i=0; i<xmlProperty.getChildSize(); i++) {
			Assert.assertEquals(xmlProperty.getChild(i).getName(), "item");
			Assert.assertEquals(xmlProperty.getChild(i).getProperty(AbstractBaseResponse.TITLE), "title");
			Assert.assertEquals(xmlProperty.getChild(i).getProperty("PicUrl"), "picUrl");
			Assert.assertEquals(xmlProperty.getChild(i).getProperty("Description"), "description");			
		}
	}

}
