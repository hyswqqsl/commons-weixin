package org.flysic.commons.weixin.active;

import org.flysic.commons.weixin.active.entity.AccessToken;
import org.flysic.commons.weixin.active.entity.CustomerServerInfo;

/**
 * 与客服相关的处理
 * @author 雪庭
 * @sine 1.0 at 2015年6月16日
 */
public class CustomerServerRequest {

	/** 单例 */
	private static CustomerServerRequest instance;
	
	/**
	 * 隐藏构造函数
	 */
	private CustomerServerRequest() {
	}
	
	/**
	 * 单例模式
	 * @return 单例
	 */
	public static CustomerServerRequest getInstance() {
		if (instance==null) {
			instance = new CustomerServerRequest();
		}
		return instance;
	}
	
	/** 获取客服基本信息url */
	private final String customer_server_info_url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
	/** 添加客服账号 */
	private final String add_customer_server_url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	
	public String getCustomerServeres(AccessToken accessToken) {
		String url = customer_server_info_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String json = HttpsRequest.getInstance().processGet(url);
		return json;
	}
	
	public String addCustomerServer(AccessToken accessToken, CustomerServerInfo customerServerInfo) {
		String url = add_customer_server_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String addCustomerServerJson = JsonObjectConvert.getInstance().ObjectToJsonNoClassName(CustomerServerInfo.class, customerServerInfo);
		String json = HttpsRequest.getInstance().processPost(url, addCustomerServerJson);
		return json;
	}

}
