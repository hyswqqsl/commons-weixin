package com.ironside.weixin.push;

import com.ironside.weixin.push.entity.TextEntity;

/**
 * 由用户实现的POST处理器，由POST处理程序调用。
 * @author 雪庭
 * @since 1.0 at 2015年4月3日
 */
public interface IPostProcessor {
	String postProcessText(TextEntity entity);
}
