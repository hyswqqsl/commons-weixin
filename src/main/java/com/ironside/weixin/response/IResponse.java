package com.ironside.weixin.response;

import com.ironside.weixin.response.entity.TextResponse;

/**
 * 回复实体管理
 * @author ZXJ
 * @sine 1.0 at 2015年4月9日
 */
public interface IResponse {
	
	/** 取得文本回复实体 */
	TextResponse getTextResponse();
	
	/** 取得图片回复实体 */
	ImageResponse getImageResponse();
	
	/** 取得语音回复实体 */
	VoiceResponse getVoiceResponse();
	
	/** 取得视频回复实体 */
	VideoResponse getVideoResponse();
	
	/** 取得音乐回复实体 */
	MusicResponse getMusicResponse();
	
	/** 取得图文回复实体 */
	NewsResponse getNewsResponse();

}
