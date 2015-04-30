package com.ironside.weixin.response.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * 图文回复实体类
 * @author ZXJ
 * @sine 1.0 at 2015年4月29日
 */
public class NewsResponse extends AbstractBaseResponse {
	
	/** 对应xml中定义的'图文消息内容'标识 */
	public final static String NEWS = "News";
	/** 对应xml中定义的'图文消息个数消息内容'标识 */
	public final static String ARTICLE_COUNT = "ArticleCount";
	/** 对应xml中定义的'图片链接消息内容'标识 */
	public final static String PIC_URL = "PicUrl";
	/** 对应xml中定义的'点击图文消息跳转链接消息内容'标识 */
	public final static String URL = "Url";
	
	/** 图文子节点最大值 */
	public final static int NEWS_CHILD_MAX_SIZE = 10; 	
	
	private List<News> articles;
	private int articleCount;
		
	public NewsResponse() {
		this.articles = new ArrayList<NewsResponse.News>();
		this.setMsgEnum(ResponseEnum.NEWS);
	}
	@Override
	public Object getObject(int index) {
		if (index >= articles.size()) {
			return null;
		}
		return articles.get(index);
	}

	@Override
	public void addObject(Object obj) {
		Assert.isTrue(obj instanceof News);
		//图文消息个数，限制为10条以内
		if (this.articles.size()>=10) {
			return;
		}
		articles.add((News)obj);
	}

	@Override
	public int getObjectCount() {
		return articles.size();
	}

	/**
	 * 取得图文消息个数
	 * @return 图文消息个数
	 */
	public int getArticleCount() {
		return articleCount;
	}

	/**
	 * 设置图文消息个数
	 * @param articleCount 图文消息个数
	 */
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public class News {
		
		private String title;
		private String description;
		private String picUrl;
		private String url;
		
		/**
		 * 取得图文消息标题
		 * @return 图文消息标题
		 */
		public String getTitle() {
			return title;
		}
		
		/**
		 * 设置图文消息标题
		 * @param title 图文消息标题
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * 取得图文消息描述
		 * @return 图文消息描述
		 */
		public String getDescription() {
			return description;
		}
		
		/**
		 * 设置图文消息描述
		 * @param description 图文消息描述
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * 取得图片链接
		 * @return 图片链接
		 */
		public String getPicUrl() {
			return picUrl;
		}
		
		/**
		 * 设置图片链接
		 * @param picUrl 图片链接
		 */
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		
		/**
		 * 取得点击图文消息跳转链接 
		 * @return 点击图文消息跳转链接 
		 */
		public String getUrl() {
			return url;
		}
		
		/**
		 * 设置点击图文消息跳转链接 
		 * @param url 点击图文消息跳转链接 
		 */
		public void setUrl(String url) {
			this.url = url;
		}	
		
	}
}
