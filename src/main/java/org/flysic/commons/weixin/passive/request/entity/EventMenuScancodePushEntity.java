package org.flysic.commons.weixin.passive.request.entity;

/**
 * 自定义菜单-扫码推事件的事件消息实体
 * @author 雪庭
 * @sine 1.0 at 2015年6月13日
 */
public class EventMenuScancodePushEntity extends EventMenuClickEntity {
	
	/**	扫描信息 */
	private ScanCodeInfo ScanCodeInfo;
	
	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo ScanCodeInfo) {
		this.ScanCodeInfo = ScanCodeInfo;
	}

	/**	扫描信息 */
	public class ScanCodeInfo {
		
		/** 扫描类型，一般是qrcode */
		private String ScanType;
		/**	扫描结果，即二维码对应的字符串信息 */
		private String ScanResult;
		
		public String getScanType() {
			return ScanType;
		}
		
		public void setScanType(String scanType) {
			ScanType = scanType;
		}
		
		public String getScanResult() {
			return ScanResult;
		}
		
		public void setScanResult(String scanResult) {
			ScanResult = scanResult;
		}
		
	}
}
