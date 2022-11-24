package kr.co.Jboard2.service.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2Vo.ArticleVo;

public enum ArticleService {

	instance;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleDAO art;
	
	private ArticleService() {
		art = new ArticleDAO();
	}
	
	public void insertArticle(ArticleVo ar) {
		art.insertArticle(ar);
	};
	
}
