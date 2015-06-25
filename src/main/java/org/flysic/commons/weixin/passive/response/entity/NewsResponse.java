package org.flysic.commons.weixin.passive.response.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * 图文回复实体类
 * @author ZXJ
 * @sine 1.0 at 2015年4月29日
 */
public class NewsResponse extends AbstractBaseResponse {	
	
	/** 图文子节点最大值 */
	public final static int NEWS_CHILD_MAX_SIZE = 10; 	
	
	private int ArticleCount;
	private List<News> Articles;
		
	public NewsResponse() {
		this.Articles = new ArrayList<NewsResponse.News>();
	}	
	
	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}

	/**
	 * 取得图文消息个数
	 * @return 图文消息个数
	 */
	public int getArticleCount() {
		return ArticleCount;
	}

	/**
	 * 设置图文消息个数
	 * @param articleCount 图文消息个数
	 */
	public void setArticleCount(int articleCount) {
		this.ArticleCount = articleCount;
	}

	public class News {
		
		private String Title;
		private String Description;
		private String PicUrl;
		private String Url;
		
		/**
		 * 取得图文消息标题
		 * @return 图文消息标题
		 */
		public String getTitle() {
			return Title;
		}
		
		/**
		 * 设置图文消息标题
		 * @param title 图文消息标题
		 */
		public void setTitle(String title) {
			this.Title = title;
		}
		
		/**
		 * 取得图文消息描述
		 * @return 图文消息描述
		 */
		public String getDescription() {
			return Description;
		}
		
		/**
		 * 设置图文消息描述
		 * @param description 图文消息描述
		 */
		public void setDescription(String description) {
			this.Description = description;
		}
		
		/**
		 * 取得图片链接
		 * @return 图片链接
		 */
		public String getPicUrl() {
			return PicUrl;
		}
		
		/**
		 * 设置图片链接
		 * @param picUrl 图片链接
		 */
		public void setPicUrl(String picUrl) {
			this.PicUrl = picUrl;
		}
		
		/**
		 * 取得点击图文消息跳转链接 
		 * @return 点击图文消息跳转链接 
		 */
		public String getUrl() {
			return Url;
		}
		
		/**
		 * 设置点击图文消息跳转链接 
		 * @param url 点击图文消息跳转链接 
		 */
		public void setUrl(String url) {
			this.Url = url;
		}	
		
	}
}
