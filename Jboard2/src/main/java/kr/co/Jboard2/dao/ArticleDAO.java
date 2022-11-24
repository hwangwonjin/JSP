package kr.co.Jboard2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.Sql;
import kr.co.Jboard2Vo.ArticleVo;

public class ArticleDAO extends DBHelper{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertArticle(ArticleVo ar) {
		try {
			logger.info("insertArticle");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, ar.getTitle());
			psmt.setString(2, ar.getContent());
			psmt.setString(3, ar.getFname());
			psmt.setString(4, ar.getUid());
			psmt.setString(5, ar.getRegip());
			psmt.executeUpdate();
			
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	public void selectArticle() {}
	public void selectArticles() {}
	public void updateArticle() {}
	public void deleteArticle() {}
}
