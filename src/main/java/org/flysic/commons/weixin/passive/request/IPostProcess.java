package org.flysic.commons.weixin.passive.request;

/**
 * POST方式推送给微信公众账号的消息处理，消息分为普通消息和事件消息两种。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public interface IPostProcess {
	
	/**
	 * 处理消息
	 * @param postData POST方式推送的数据
	 * @return 处理响应信息
	 */
	String process(String postData);

}
