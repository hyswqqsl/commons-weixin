package com.ironside.weixin.active;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * https方式请求实现
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年5月28日
 */
public class HttpsRequest {

	private static HttpsRequest instance;

	/**
	 * RestTemplate是Spring提供的用于访问Rest服务的客户端，
	 * RestTemplate提供了多种便捷访问远程Http服务的方法，能够大大提高客户端的编写效率
	 */
	private RestTemplate restTemplate;

	/**
	 * 取得Rest服务的客户端，先在缓存中找客户端，如果没有，先生成再返回
	 * 
	 * @return Rest服务的客户端
	 */
	private RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			this.restTemplate = new RestTemplate(requestFactory);
			setRestTemplateCharSet(this.restTemplate, StandardCharsets.UTF_8);
		}
		return this.restTemplate;
	}

	/*
	 * 设置RestTemplate的编码，RestTemplate默认添加HttpMessageConverter的编码是ISO-8859-1，
	 * 如果用其他编码，需要移除原有的StringHttpMessageConverter，添加指定字符集的StringHttpMessageConvert
	 */
	private void setRestTemplateCharSet(RestTemplate restTemplate, Charset charset) {
		List<HttpMessageConverter<?>> converterList = restTemplate	.getMessageConverters();
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (item.getClass() == StringHttpMessageConverter.class) {
				converterTarget = item;
				break;
			}
		}
		if (converterTarget != null) {
			converterList.remove(converterTarget);
		}
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(charset);
		converterList.add(converter);
	}

	/**
	 * https方式访问，get方法
	 * 
	 * @param httpsUrl
	 *            https地址
	 * @return 微信服务器返回的json串
	 */
	public String processGet(String httpsUrl) {
		ResponseEntity<String> response = getRestTemplate().exchange(httpsUrl,	HttpMethod.GET, null, String.class);
		return response.getBody();
	}

	/**
	 * https方式访问，post方法
	 * 
	 * @param httpsUrl
	 *            https地址
	 * @param json
	 *            post方式发送的json串
	 * @return 微信服务器返回的json串
	 */
	public String processPost(String httpsUrl, String json) {
		HttpEntity<String> httpEntity = new HttpEntity<String>(json);
		ResponseEntity<String> response = getRestTemplate().exchange(httpsUrl,
				HttpMethod.POST, httpEntity, String.class);
		return response.getBody();
	}
	
	/**
	 * 单例模式
	 * 
	 * @return https方式对象
	 */
	public static HttpsRequest getInstance() {
		if (instance == null) {
			instance = new HttpsRequest();
		}
		return instance;
	}

}
