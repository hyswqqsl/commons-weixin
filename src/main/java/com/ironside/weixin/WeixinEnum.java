package com.ironside.weixin;

/**
 * 微信错误枚举
 * @author 雪庭
 * @sine 1.0 at 2015年6月8日
 */
public enum WeixinEnum {

	UNKNOWN(-100, "未知错误"),
	BUSY(-1, "系统繁忙，此时请开发者稍候再试 "),
	OK(0, "请求成功"),
	APPSECRET_ERROR(40001, "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口"),
	ACCESS_TOKEN_TYPE_ERROR(40002, "不合法的凭证类型"),
	OPPENID_ERROR(40003, "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID"),
	MEDIA_TYPE_ERROR(40004, "不合法的媒体文件类型"),
	FILE_TYPE_ERROR(40005, "不合法的文件类型"),
	FILE_SIZE_ERROR(40006, "不合法的文件大小"),
	MEDIA_ID_ERROR(40007, "不合法的媒体文件id"),
	MESSAGE_TYPE_ERROR(40008, "不合法的消息类型"),
	PICTURE_SIZE_ERROR(40009,	"不合法的图片文件大小"),
	VOICE_SIZE_ERROR(40010, "不合法的语音文件大小"),
	VIDEO_SIZE_ERROR(40011, "不合法的视频文件大小"),
	THUMBNAIL_SIZE_ERROR(40012, "不合法的缩略图文件大小"),
	APPID_ERROR(40013, "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写"),
	ACCESS_TOKEN_ERROR(40014, "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
	MENU_TYPE_ERROR(40015, "不合法的菜单类型"),
	BUTTON_NUM_ERROR1(40016,	"不合法的按钮个数"),
	BUTTON_NUM_ERROR2(40017, "不合法的按钮个数"),
	BUTTON_NAME_LENGTH_ERROR(40018, "不合法的按钮名字长度"),
	BUTTON_KEY_LENGTH_ERROR(40019, "不合法的按钮KEY长度"),
	BUTTON_URL_LENGTH_ERROR(40020, "不合法的按钮URL长度"),
	MENU_VERSION_ERROR(40021, "不合法的菜单版本号"),
	MENU_LEVEL_ERROR(40022, "不合法的子菜单级数"),
	MENU_SUB_BUTTON_SIZE_ERROE(40023, "不合法的子菜单按钮个数"),
	MENU_SUB_BUTTON_TYPE_ERROE(40024, "不合法的子菜单按钮类型"),
	MENU_SUB_BUTTON_NAME_LENGTH_ERROE(40025, "不合法的子菜单按钮名字长度"),
	MENU_SUB_BUTTON_KEY_LENGTH_ERROE(40026,	"不合法的子菜单按钮KEY长度"),
	MENU_SUB_BUTTON_URL_LENGTH_ERROE(40027, "不合法的子菜单按钮URL长度"),
	MENU_DEFINE_USER_ERROR(40028, "不合法的自定义菜单使用用户"),
	OAUTH_CODE_ERROR(40029, "不合法的oauth_code"),
	REFRESH_TOKEN_ERROR(40030, "不合法的refresh_token");
	
	
	/** 错误编码 */
	private int code;
	/** 错误说明 */
	private String message;
	
	private WeixinEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 根据错误编号取得微信错误枚举，
	 * 如果错误编号未找到，返回未知错误
	 * @param code 错误编号
	 * @return 微信错误枚举
	 */
	public static WeixinEnum getWeixinEnum(int code) {
		WeixinEnum[] enums = WeixinEnum.values();
		WeixinEnum weixinEnum;
		for (int i=0; i<enums.length; i++) {
			weixinEnum = enums[i];
			if(code==weixinEnum.getCode()) {
				return weixinEnum; 
			}
		}
		return WeixinEnum.UNKNOWN;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		
}
