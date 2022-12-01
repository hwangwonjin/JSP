package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.Vo.ArticleVo;
import kr.co.farmstory2.Vo.FileVo;
import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.Sql;



//DAO(Data Access Object) : 데이터베이스 처리 클래스
public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD
	public int insertArticle(ArticleVo article) {
		
		int parent = 0;
		
		try{
			Connection conn = getConnection();
			// 트랜젝션 시작
			conn.setAutoCommit(false);
			
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			Statement stmt = conn.createStatement();
			
			psmt.setString(1, article.getCate());
			psmt.setString(2, article.getTitle());
			psmt.setString(3, article.getContent());
			psmt.setInt(4, article.getFname() == null ? 0 : 1);
			psmt.setString(5, article.getUid());
			psmt.setString(6, article.getRegip());
			
			psmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			// 작업확정
			conn.commit();
			
			if(rs.next()){
				parent = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return parent;
	}
	
	public void insertFile(int parent, String newName, String fname) {
		try{
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fname);
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	public ArticleVo insertComment(ArticleVo comment) {
		
		ArticleVo article = null;
		int result = 0;
		
		try{
			Connection conn = getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt1 = conn.prepareStatement(Sql.INSERT_COMMENT);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_COUNT_PLUSE);
			Statement stmt = conn.createStatement();
			psmt1.setInt(1, comment.getParent());
			psmt1.setString(2, comment.getContent());
			psmt1.setString(3, comment.getUid());
			psmt1.setString(4, comment.getRegip());
			
			psmt2.setInt(1, comment.getParent());
			
			result = psmt1.executeUpdate();
			psmt2.executeUpdate();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_COMMENT_LATEST);
			
			conn.commit();
			
			if(rs.next()) {
				article = new ArticleVo();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setContent(rs.getString(6));
				article.setRdate(rs.getString(11).substring(2, 10));
				article.setNick(rs.getString(12));
			}
			
			rs.close();
			stmt.close();
			psmt1.close();
			psmt2.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return article;
	}
	
	public int selectCountTotal(String cate, String search) {
		
		int total = 0;
		
		try {
			logger.info("selectCountTotal");
			conn = getConnection();
		
			if(search == null) {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
				rs = psmt.executeQuery();
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
				psmt.setString(3, "%"+search+"%");
				rs = psmt.executeQuery();
			}
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("total : " + total);
		
		return total;
	}
	
	
	
	public ArticleVo selectArticle(String no) {
		
		ArticleVo article = null;
		
		try{
			logger.info("selectArticle...");
			Connection conn = getConnection();
			logger.info("conn : " +conn);
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			logger.info("2");
			psmt.setString(1, no);
			logger.info("3");
			
			ResultSet rs = psmt.executeQuery();
			logger.info("4");
			
			if(rs.next()){
				article = new ArticleVo();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setFno(rs.getInt(12));
				article.setPno(rs.getInt(13));
				article.setNewName(rs.getString(14));
				article.setOriName(rs.getString(15));
				article.setDownload(rs.getInt(16));
			}
			logger.info("5");
			close();
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("article : " + article);
		return article;
	}
	
	public List<ArticleVo> selectArticles(int limitStart, String cate) {
		
		List<ArticleVo> articles = new ArrayList<>();
		
		try{
			logger.info("selectArticles...");
			conn = getConnection();
			logger.info("conn : " +conn);
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			logger.info("3");
			psmt.setString(1, cate);
			logger.info("4");
			psmt.setInt(2, limitStart);
			logger.info("5");
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				ArticleVo article = new ArticleVo();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				
				articles.add(article);			
			}
			
			close();
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		logger.debug("articles size : " + articles.size());
		return articles;
	}

	public List<ArticleVo>  selectArticlesByKeyword(String cate, String keyword, int start) {
		
		List<ArticleVo> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticlesByKeyword..");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES_BY_KEYWORD);
			psmt.setString(1, cate);
			psmt.setString(2, "%"+keyword+"%");
			psmt.setString(3, "%"+keyword+"%");
			psmt.setInt(4, start);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				ArticleVo article = new ArticleVo();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				
				articles.add(article);			
			}
			
			close();
					
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return articles;
	}
	
	public FileVo selectFile(String parent) {
		FileVo fb = null;
		
		try{
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, parent);
		 	ResultSet rs = psmt.executeQuery();
		 	
		 	if(rs.next()){
		 		fb = new FileVo();
		 		fb.setFno(rs.getInt(1));
		 		fb.setParent(rs.getInt(2));
		 		fb.setNewName(rs.getString(3));
		 		fb.setOriName(rs.getString(4));
		 		fb.setDownload(rs.getInt(5));
		 	}
		 	rs.close();
		 	psmt.close();
		 	conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return fb;
	}
	
	public List<ArticleVo> selectComments(String parent) {
		
		List<ArticleVo> comments = new ArrayList<>();
		
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVo comment = new ArticleVo();
				comment.setNo(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setComment(rs.getInt(3));
				comment.setCate(rs.getString(4));
				comment.setTitle(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setFile(rs.getInt(7));
				comment.setHit(rs.getInt(8));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11));
				comment.setNick(rs.getString(12));
				
				comments.add(comment);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public void updateArticle(String no, String title, String content) {
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, no);
			
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateArticleHit(String no) {
		int result = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateFileDownload(int fno) {
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setInt(1, fno);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateComment(String no, String content) {
		int result = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			
			result = psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteArticle(String no) {
		int result = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String deleteFile(String no) {
		
		String newName = null;
		
		try {
			Connection conn = getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt1 = conn.prepareStatement(Sql.SELECT_FILE);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.DELETE_FILE);
			
			psmt1.setString(1, no);
			psmt2.setString(1, no);
			
			ResultSet rs = psmt1.executeQuery();
			psmt2.executeUpdate();
			
			conn.commit();
			
			if(rs.next()) {
				newName = rs.getString(3);
			}
			
			psmt1.close();
			psmt2.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return newName;
	}
	
	public int deleteComment(String no, String parent) {
		int result = 0;
		try {
			Connection conn = getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt1 = conn.prepareStatement(Sql.DELETE_COMMENT);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_COUNT_MINUS);
			psmt1.setString(1, no);
			psmt2.setString(1, parent);
			result = psmt1.executeUpdate();
			psmt2.executeUpdate();
			
			conn.commit();
			
			psmt1.close();
			psmt2.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	
}
