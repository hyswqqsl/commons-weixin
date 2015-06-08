package com.ironside.weixin.active;

import com.ironside.weixin.WeixinException;
import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.Group;
import com.ironside.weixin.active.entity.UserInfo;
import com.ironside.weixin.active.entity.UserList;

public class UserRequest {
	
	/** 单例 */
	private static UserRequest instance;
	
	/**
	 * 隐藏默认构造函数
	 */
	private UserRequest() {
	}
	
	/**
	 * 单例模式
	 * @return UserRequest单例
	 */
	public static UserRequest getInstance() {
		if (instance==null) {
			instance = new UserRequest();
		}
		return instance;
	}

	/** 获取用户信息请求url */
	private final String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/** 获取帐号的关注者列表url */
	private final String user_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	/** 设置用户备注名url */
	private final String user_remark_url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	/** 创建分组 */
	private final String user_create_group_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取用户基本信息(unionId机制)，在关注者与公众号产生消息交互后，
	 * 公众号可获得关注者的openid（加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同）。
	 * 公众号可通过本接口来根据openid获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间
	 * @param accessToken 公众号的全局凭证
	 * @param openid 用户公众号
	 * @return 用户
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	public UserInfo getUserInfo(AccessToken accessToken, String openid) throws WeixinException {
		String url = user_info_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken()).replaceAll("OPENID", openid);
		String json = HttpsRequest.getInstance().processGet(url);
		JsonObjectConvert.getInstance().validateJsonException(json);		
		UserInfo userInfo = JsonObjectConvert.getInstance().jsonToObject(json, UserInfo.class);
		return userInfo;
	}
	
	/**
	 * 获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
	 * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求 
	 * @param accessToken 公众号的全局凭证
	 * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return 关注者列表由
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	public UserList getUserList(AccessToken accessToken, String nextOpenid) throws WeixinException {
		if (nextOpenid==null) {
			nextOpenid = "";
		}
		String url = user_list_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken()).replaceAll("NEXT_OPENID", nextOpenid);
		String json = HttpsRequest.getInstance().processGet(url);
		JsonObjectConvert.getInstance().validateJsonException(json);		
		UserList userList = JsonObjectConvert.getInstance().jsonToObjectSubList(json, UserList.class, "data", UserList.UserListData.class, "openid");
		return userList;
	}
	
	/**
	 * 设置用户备注
	 * @param accessToken 公众号的全局凭证
	 * @param userInfo 用户信息
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	public void setUserRemark(AccessToken accessToken, UserInfo userInfo) throws WeixinException {
		String url = user_remark_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String userRemarkJson = JsonObjectConvert.getInstance().ObjectToJson(UserInfo.class, userInfo, false);
		String json = HttpsRequest.getInstance().processPost(url, userRemarkJson);
		JsonObjectConvert.getInstance().validateJsonException(json);
	}
	
	/**
	 * 创建分组
	 * @param accessToken 公众号的全局凭证
	 * @param group 用户组
	 * @return 返回的用户组
	 * @throws WeixinException 失败时抛出WeixinException异常
	 */
	public Group createGroup(AccessToken accessToken, Group group) throws WeixinException {
		String url = user_create_group_url.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());
		String userGroupJson = JsonObjectConvert.getInstance().ObjectToJson(Group.class, group, true);
		String json = HttpsRequest.getInstance().processPost(url, userGroupJson);
		JsonObjectConvert.getInstance().validateJsonException(json);
		Group resultGroup = JsonObjectConvert.getInstance().jsonToObject(json, Group.class, "group");
		return resultGroup;
	}
	
}