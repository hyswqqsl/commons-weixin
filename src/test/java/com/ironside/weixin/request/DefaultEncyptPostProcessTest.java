package com.ironside.weixin.request;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ironside.weixin.MyPostProcessor;
import com.ironside.weixin.response.ResponseManager;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * POST方式推送给微信公众账号的加密消息处理，具体实现消息加密、解密测试
 * @author 雪庭
 * @sine 1.0 at 2015年5月10日
 */
public class DefaultEncyptPostProcessTest {
	
	/** POST方式推送给微信公众账号的加密消息处理，具体实现消息加密、解密 */
	private DefaultEncyptPostProcess process;
	
	private String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
	private String token = "pamtest";
	private String timestamp = "1409304348";
	private String nonce = "xxxxxx";
	private String appId = "wxb11529c136998cb6";
	
	/** 解密用的模板 */
	private String postData = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt>zLkDmErXie/7bsbFrrKGAaJGlMbg5WCI56oq1g9LGzf+V5vXMH4B63l8Nb7j7CbF9Fwr8G5LNBrS+e2ujRNVR53q2V8YUX/SNg+zNeooEZPm6b+l6omO9uGIDrJjRua1P02FFevNkSsv6NX85BwpfpIMTd5Zo4ZTmIKgoHxRdrN2IoKPq0V3m/ItOk/hDXV4K6yjoRa0eoA7T/DtSdiOLcMR5OFzkqMKfu5HllRhxiX84eB+ToTYiWnv8BEzVrz4+djhgXAoVaLNaFzt1pXRR3bk6iVDUJEXFxFl7JW04zrjjjL7LQzWeNRQX4v1Cz+BdSuVCc6MOUtzFXVS2FaAv9qHlKbYW34mMvV42owVGiPTTOAenUzy/LVOuK8s5UK6U+eVHeGjo446Mab/b4i5CQQpnJ6B0pCG7CbG9O8E0/2DPTInBwxvrZnashl1XR5kFamdKpVHjDH+WVY+mnu3Ow==</Encrypt></xml>";

	private String signature = "546113175fa610c69679e690eba67c22c43c7b09";
	
	private String resultXml = "<xml><ToUserName><![CDATA[fromUser]]></ToUserName><FromUserName><![CDATA[toUser]]></FromUserName><CreateTime><![CDATA[12345678]]></CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";

	@Before
	public void setUp() throws Exception {
		// 消息处理器
		MyPostProcessor processor = new MyPostProcessor();
		// POST方式推送给微信公众账号的加密消息处理，具体实现消息加密、解密
		process = new DefaultEncyptPostProcess(this.token, this.encodingAesKey, this.encodingAesKey, this.appId);
		process.setProcessor(processor);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVideoPost() throws AesException, ParserConfigurationException, SAXException, IOException {
		String result = this.process.process(this.signature, this.timestamp, this.nonce, postData);
		// 验证
		WXBizMsgCrypt pc = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
		String xmlFormat = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(result);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList nodelist1 = root.getElementsByTagName("Encrypt");
		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

		String encrypt = nodelist1.item(0).getTextContent();
		String signature = nodelist2.item(0).getTextContent();
		String fromXML = String.format(xmlFormat, encrypt);
		// 解密
		String afterDecrpt = pc.decryptMsg(signature, this.timestamp, this.nonce, fromXML);
		afterDecrpt = afterDecrpt.trim().replaceAll(" ", "").replaceAll("\r","").replaceAll("\n","");
		Assert.assertEquals(afterDecrpt, resultXml);
	}

}
