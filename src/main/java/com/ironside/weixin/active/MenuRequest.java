package com.ironside.weixin.active;

import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.Menu;

/**
 * 与菜单相关的处理
 * @author 雪庭
 * @sine 1.0 at 2015年6月12日
 */
public class MenuRequest {

	/** 单例 */
	private static MenuRequest instance;
	
	/**
	 * 隐藏构造函数
	 */
	private MenuRequest() {
	}
	
	/**
	 * 单例模式
	 * @return MenuRequest单例
	 */
	public static MenuRequest getInstance() {
		if (instance==null) {
			instance = new MenuRequest();
		}
		return instance;
	}
	
	/** 自定义菜单创建url */
	private final String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/**  */
	
	/**
	 * 新建菜单
	 * @param accessToken 公众号的全局凭证
	 * @param menu 菜单
	 */
	public void createMenu(AccessToken accessToken, Menu menu) {
		String url = create_menu_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String createMenuJson = JsonObjectConvert.getInstance().ObjectToJson(Menu.class, menu);
		createMenuJson = createMenuJson.replaceAll("\\{\"menu\":", "");
		createMenuJson = createMenuJson.substring(0, createMenuJson.length()-1);
		createMenuJson = createMenuJson.replaceAll(",\"sub_button\":\\[\"\"\\]", "");
		String json = HttpsRequest.getInstance().processPost(url, createMenuJson);
		JsonObjectConvert.getInstance().validateJsonException(json);
	}
	
	public Menu queryMenu(AccessToken accessToken) {
		retrun null;
	}

}
