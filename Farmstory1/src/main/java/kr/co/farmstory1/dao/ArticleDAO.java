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
import kr.co.farmstory1.bean.FileBean;
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
			
			if(rs.next()){
				parent = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return parent;
	}
	public void selectArticle() {}
	
	//최근 게시물
	public List<ArticleBean> selectLatests(String cate1, String cate2, String cate3) {
		
		List<ArticleBean> latests = new ArrayList<>();
		try {
			logger.info("selectLatests..");
			
			conn = getConnection();
			psmt= conn.prepareStatement(Sql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setTitle(rs.getString(2));
				ab.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(ab);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("latests size : " + latests.size());
		return latests;
	}
	
	public synchronized List<ArticleBean> selectLatests(String cate) {
		
		List<ArticleBean> latests = new ArrayList<>();
		try {
			logger.info("selectLatests(String)..");
			
			conn = getConnection();
			psmt= conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setTitle(rs.getString(2));
				ab.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(ab);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
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
	
	//no 기준으로 조회하기
		public ArticleBean selectArticle(String no) {
			ArticleBean ab = null;
			
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
				psmt.setString(1, no);
				
				ResultSet rs = psmt.executeQuery();
				
				if(rs.next()){
					ab = new ArticleBean();
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
					ab.setFno(rs.getInt(12));
					ab.setPno(rs.getInt(13));
					ab.setNewName(rs.getString(14));
					ab.setOriName(rs.getString(15));
					ab.setDownload(rs.getInt(16));
					
						
				}
				
				rs.close();
				psmt.close();
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return ab;
		}
	
		//글 수정하기
		public void updateArticle(String no, String title, String content ) {
			
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
				psmt.setString(1, title);
				psmt.setString(2, content);
				psmt.setString(3, no);
				
				psmt.executeUpdate();
				
				psmt.close();
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}

		}
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
		
		//조회수 증가
		public void updateArticleHit(String no) {
			
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
				psmt.setString(1, no);
				psmt.executeUpdate();
				psmt.close();
				conn.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
				
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
		
		//파일 데이터베이스에 등록된 정보 가져오기
		public FileBean selectFile(String parent) {
			FileBean fb = null;
			
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
				psmt.setString(1, parent);
				
				ResultSet rs = psmt.executeQuery();
				
				if(rs.next()){
					fb = new FileBean();
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
		
		//다운로드 횟수 증가
		public void updateFlieDownload(int fno) {
			try{
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
				psmt.setInt(1, fno);
				psmt.executeUpdate();
				psmt.close();
				conn.close();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		//댓글 입력하기
		public ArticleBean insertComment(ArticleBean comment) {
			
			ArticleBean article = null;
			int result = 0;
			
			try{
				Connection conn = DBCP.getConnection();
				
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
					article = new ArticleBean();
					article.setNo(rs.getInt(1));
					article.setParent(rs.getInt(2));
					article.setContent(rs.getString(6));
					article.setRdate(rs.getString(11).substring(2,10));
					article.setNick(rs.getString(12));
				}
				
				rs.close();
				stmt.close();
				psmt1.close();
				psmt2.close();
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return article;
		}
		
		
		
		//댓글 출력하기
		public List<ArticleBean> selectComments(String parent) {
			List<ArticleBean> comments = new ArrayList<>();
			try {
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
				psmt.setString(1, parent);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()){
	   				ArticleBean comment = new ArticleBean();
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
				// TODO: handle exception
				e.printStackTrace();
			}
			return comments;
			
		}
		
		//댓글 수정하기
		public int updateComment(String no, String content) {
			int result = 0;
			try {
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
				psmt.setString(1, content);
				psmt.setString(2, no);
				
				result = psmt.executeUpdate();
				psmt.close();
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		//댓글 삭제하기
		public int deleteComment(String no, String parent ) {
			int result = 0;
			try {
				Connection conn = DBCP.getConnection();
				PreparedStatement psmt1 = conn.prepareStatement(Sql.DELETE_COMMENT);
				PreparedStatement psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_COUNT_MINUS);
				psmt1.setString(1, no);
				psmt2.setString(1, parent);
				result = psmt1.executeUpdate();
				psmt2.executeUpdate();
				
				psmt1.close();
				psmt2.close();
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		//글 삭제하기
		public int deleteArticle(String no) {
			int result = 0;
			try {
			Connection conn =DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		//파일 삭제하기
		public String deleteFile(String no) {
			String newName = null;
			
			try {
			Connection conn =DBCP.getConnection();
			
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
				// TODO: handle exception
				e.printStackTrace();
			}
			return newName;
		}
		
		
}
