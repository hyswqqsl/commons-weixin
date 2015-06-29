package org.flysic.commons.weixin.passive.response.entity;

/**
 * 消息转发到多客服
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年6月16日
 */
public class TransferCustomerResponse extends AbstractBaseResponse {
	
	/** 指定会话接入的客服账号 */
	private TransInfo TransInfo;
	
	public TransInfo getTransInfo() {
		return TransInfo;
	}

	public void setTransInfo(TransInfo transInfo) {
		TransInfo = transInfo;
	}

	public class TransInfo {

		/** 指定会话接入的客服账号 */
		private String KfAccount;

		public String getKfAccount() {
			return KfAccount;
		}

		public void setKfAccount(String kfAccount) {
			KfAccount = kfAccount;
		}
		
	}

}
