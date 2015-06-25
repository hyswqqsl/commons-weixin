package org.flysic.commons.weixin.passive;

import org.springframework.util.Assert;

/**
 * 模拟数据库服务
 * 
 * @author 雪庭
 * @sine 1.0 at 2015年5月8日
 */
public class MyServiceImpl {
	
	/**
	 * 模拟数据查找
	 * 
	 * @param flower 欲查找的花名
	 * @return 找到的花介绍
	 */
	public static String findFlower(String flower) {
		Assert.hasText(flower);
		String resule = null;
		switch (flower) {
		case MyMessage.QUERY_MU:
			resule = MyMessage.MU_INFO;
			break;
		case MyMessage.QUERY_LO:
			resule = MyMessage.LO_INFO;
			break;
		case MyMessage.QUERY_OS:
			resule = MyMessage.OS_INFO;
			break;
		default:
			resule = MyMessage.MU_INFO;
		}
		return resule;
	}

}
