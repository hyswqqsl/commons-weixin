package com.ironside.weixin.push;

import java.util.Map.Entry;

import org.springframework.util.Assert;

import com.ironside.weixin.push.entity.EntityEnum;

/**
 * POST方式推送给微信公众账号的消息处理，具体实现消息解析、处理实体。
 * @author 雪庭
 * @since 1.0
 */
public class DefaultPostProcess extends AbstractPostProcess {

	@Override
	protected Entry<EntityEnum, Object> analyze(String postData) {
		Assert.hasText(postData, "postData 参数不能为空");
		return null;
	}

	@Override
	protected String process(Entry<EntityEnum, Object> entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
