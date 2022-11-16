package kr.co.farmstory1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory1.bean.ArticleBean;
import kr.co.farmstory1.db.DBCP;
import kr.co.farmstory1.db.DBHelper;
import kr.co.farmstory1.db.Sql;




public class ArticleDAO extends DBHelper{
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getinstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	//글작성
	public int insertArticle(ArticleBean ab) {
		
		int parent = 0;
		
		try {
			logger.info("insertArticle");
			
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, ab.getCate());
			psmt.setString(2, ab.getTitle());
			psmt.setString(3, ab.getContent());
			psmt.setInt(4, ab.getFname() == null ? 0 : 1);
			psmt.setString(5, ab.getUid());
			psmt.setString(6, ab.getRegip());
			psmt.executeUpdate();
			rs =stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			conn.commit();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return parent;
	}
	public void selectArticle() {}
	
	//리스트 조회
	public List<ArticleBean> selectArticles(String cate, int start) {
		
		List<ArticleBean> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticles..");
			
			conn = getConnection();
			logger.info("1");
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			logger.info("2");
			psmt.setString(1, cate);
			logger.info("3");
			psmt.setInt(2, start);
			logger.info("4");
			rs = psmt.executeQuery();
			logger.info("5");
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setNick(rs.getString(12));
				
				articles.add(ab);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("articles size : " + articles.size());
		
		return articles;
	}
	
	public void updateArticle() {}
	public void deleteArticle() {}
	
	//모든 게시글 개수 조회
		public int selectCountTotal(String cate) {
			
			int total = 0;
			
			try {
				Connection conn = DBCP.getConnection();
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
				
				rs = psmt.executeQuery();
				
				if(rs.next()){
					total = rs.getInt(1);
					
				}
				
				close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return total;
		}
		
		//파일 추가
		public void insertFile(int parent, String newName, String fname) {
			
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
				psmt.setInt(1, parent);
				psmt.setString(2, newName);
				psmt.setString(3, fname);
				
				psmt.executeUpdate();
				
				psmt.close();
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
}
