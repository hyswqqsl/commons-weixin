package org.flysic.commons.weixin.passive;

import org.flysic.commons.weixin.passive.request.entity.EventLocationEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuClickEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuLocationSelectEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicPhotoOrAlbumEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicSysphotoEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuPicWeixinEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuScancodePushEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuScancodeWaitmsgEntity;
import org.flysic.commons.weixin.passive.request.entity.EventMenuViewEntity;
import org.flysic.commons.weixin.passive.request.entity.EventScanEntity;
import org.flysic.commons.weixin.passive.request.entity.EventScanSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.EventSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.EventUnSubscribeEntity;
import org.flysic.commons.weixin.passive.request.entity.ImageEntity;
import org.flysic.commons.weixin.passive.request.entity.LinkEntity;
import org.flysic.commons.weixin.passive.request.entity.LocationEntity;
import org.flysic.commons.weixin.passive.request.entity.ShortVideoEntity;
import org.flysic.commons.weixin.passive.request.entity.TextEntity;
import org.flysic.commons.weixin.passive.request.entity.VideoEntity;
import org.flysic.commons.weixin.passive.request.entity.VoiceEntity;
import org.flysic.commons.weixin.passive.response.ResponseManager;

/**
 * POST处理器缺省适配模式
 * @author 雪庭(flysic) QQ: 119238122 微信: flysic github: https://github.com/flysic
 * @sine 1.0 at 2015年4月9日
 */
public class PostProcessorAdapter implements IPostProcessor {
	
	/** 默认回复消息 */
	protected final String result = "success";
	/** 回复实体管理 */
	protected ResponseManager responseManager;
	
	public PostProcessorAdapter() {
		this.responseManager = new ResponseManager();
	}

	public String postProcessText(TextEntity entity) {
		return result;
	}

	public String postProcessImage(ImageEntity entity) {
		return result;
	}

	public String postProcessVoice(VoiceEntity entity) {
		return result;
	}

	public String postProcessVideo(VideoEntity entity) {
		return result;
	}

	public String postProcessShortVideo(ShortVideoEntity entity) {
		return result;
	}

	public String postProcessLocation(LocationEntity entity) {
		return result;
	}

	public String postProcessLink(LinkEntity entity) {
		return result;
	}

	public String postProcessEventSubscribe(EventSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventUnSubscribe(EventUnSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventScanSubscribe(EventScanSubscribeEntity entity) {
		return result;
	}

	public String postProcessEventScan(EventScanEntity entity) {
		return result;
	}

	public String postProcessEventLocation(EventLocationEntity entity) {
		return result;
	}

	public String postProcessEventMenuClick(EventMenuClickEntity entity) {
		return result;
	}

	public String postProcessEventMenuView(EventMenuViewEntity entity) {
		return result;
	}

	@Override
	public String postProcessEventMenuScancodePush(
			EventMenuScancodePushEntity entity) {
		return result;
	}

	@Override
	public String postProcessEventMenuScancodeWaitmsg(
			EventMenuScancodeWaitmsgEntity entity) {
		return result;		
	}

	@Override
	public String postProcessEventMenuPicSysphoto(
			EventMenuPicSysphotoEntity entity) {
		return result;
	}

	@Override
	public String postProcessEventMenuPicPhotoOrAlbum(
			EventMenuPicPhotoOrAlbumEntity entity) {
		return result;
	}

	@Override
	public String postProcessEventMenuPicWeixin(EventMenuPicWeixinEntity entity) {
		return result;		
	}

	@Override
	public String postProcessEventMenuLocationSelect(
			EventMenuLocationSelectEntity entity) {
		return result;				
	}

}
