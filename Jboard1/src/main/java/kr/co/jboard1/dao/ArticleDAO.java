package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard1.bean.ArticleBean;
import kr.co.jboard1.bean.FileBean;
import kr.co.jboard1.db.DBCP;
import kr.co.jboard1.db.Sql;

//DAO(Data Access Object) : 데이터베이스 처리 클래스 
public class ArticleDAO {
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getinstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	//기본 CRUD
	//모든 게시글 조회
	public int insertArticle(ArticleBean article) {
		int parent = 0; 
		
		try{
			Connection conn = DBCP.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			Statement stmt = conn.createStatement();
			
			psmt.setString(1, article.getTitle());
			psmt.setString(2, article.getContent());
			psmt.setInt(3, article.getFname() == null ? 0 : 1);
			psmt.setString(4, article.getUid());
			psmt.setString(5, article.getRegip());
			
			psmt.executeUpdate();
			ResultSet rs =stmt.executeQuery(Sql.SELECT_MAX_NO);
		
			//작업확정
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
		}
		return parent;
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
	
	//모든 게시글 개수 조회
	public int selectCountTotal() {
		
		int total = 0;
		
		try {
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL);
			if(rs.next()){
				total = rs.getInt(1);
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return total;
	}
	
	//no 기준으로 조회하기
	public ArticleBean selectArticle(String no) {
		ArticleBean article = null;
		
		try{
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				article = new ArticleBean();
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
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return article;
	}
	
	
	//게시물이 10개 이상 존재시 10개만 조회
	public List<ArticleBean> selectsArticles(int limitStart) {
		List<ArticleBean> articles = new ArrayList<>();
		
		try{
   			Connection conn = DBCP.getConnection();
   			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
   			psmt.setInt(1, limitStart);
   			
   			ResultSet rs = psmt.executeQuery();
   			
   			while(rs.next()){
   				ArticleBean article = new ArticleBean();
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
   			
   			rs.close();
   			psmt.close();
   			conn.close();
   			
   		}catch(Exception e){
   			e.printStackTrace();
   		}
		return articles;
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
	
	public void updateArticle() {}
	
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
	
	public void deleteArticle() {}
	
	
	
	
}