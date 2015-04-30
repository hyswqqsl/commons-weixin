package com.ironside.weixin.response.xsentity;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse extends AbstractBaseResponse {
	
	public List<NewsResponse.item> Articles;
	
	public String ArticleCount;
	
	public NewsResponse() {
		this.Articles = new ArrayList<NewsResponse.item>();
	}

	public class item {
		public String Title;
		public String Description;
		public String PicUrl;
		public String Url;
	}
}
