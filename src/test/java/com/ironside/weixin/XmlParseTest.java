package com.ironside.weixin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ironside.weixin.response.AbstractXmlProperty;
import com.ironside.weixin.response.entity.AbstractBaseResponse;

public class XmlParseTest {
	
	private XmlParse xmlParse;
	public final String XML_STR = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>" +
			"<CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount>" +
			"<Articles><item><Title><![CDATA[title]]></Title><Description><![CDATA[description1]]></Description>" +
			"<PicUrl><![CDATA[picurl]]></PicUrl><Url><![CDATA[url]]></Url></item><item><Title><![CDATA[title]]></Title>" +
			"<Description><![CDATA[description]]></Description><PicUrl><![CDATA[picurl]]></PicUrl><Url><![CDATA[url]]></Url></item>" +
			"<item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description><PicUrl><![CDATA[picurl]]></PicUrl><Url><![CDATA[url]]></Url></item>" +
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
		AbstractXmlProperty xmlProperty = this.xmlParse.sparseString(XML_STR);
		Assert.assertEquals(xmlProperty.getChildSize(), 3);
		Assert.assertEquals(xmlProperty.getProperty(AbstractBaseResponse.MSG_TYPE), "news");
		for (int i=0; i<xmlProperty.getChildSize(); i++) {
			Assert.assertEquals(xmlProperty.getChild(i).getProperty(AbstractBaseResponse.TITLE), "title");
			Assert.assertEquals(xmlProperty.getChild(i).getProperty("PicUrl"), "picurl");			
		}
	}

}
