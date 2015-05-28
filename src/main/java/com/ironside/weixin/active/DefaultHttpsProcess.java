package com.ironside.weixin.active;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 默认实现https方式访问
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年5月28日
 */
public class DefaultHttpsProcess implements IHttpsProcess {

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
		}
		return this.restTemplate;
	}

	@Override
	public String processGet(String httpsUrl) {
		ResponseEntity<String> response = getRestTemplate().exchange(httpsUrl, HttpMethod.GET, null, String.class);
		return response.getBody();
	}
	
	@Override
	public String processPost(String httpsUrl, String json) {
		HttpEntity<String> httpEntity = new HttpEntity<String>(json);
		ResponseEntity<String> response = getRestTemplate().exchange(httpsUrl, HttpMethod.POST, httpEntity, String.class);
		return response.getBody();
	}

}
