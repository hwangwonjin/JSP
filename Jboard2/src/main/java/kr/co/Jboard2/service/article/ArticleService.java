package kr.co.Jboard2.service.article;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2Vo.ArticleVo;

public enum ArticleService {

	instance;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleDAO dao;
	
	private ArticleService() {
		dao = new ArticleDAO();
	}
	
	public int insertArticle(ArticleVo article) {
		return dao.insertArticle(article);
	}
	
	public void insertFile(int parent, String newName, String fname) {
		dao.insertFile(parent, newName, fname);
	}
	
	public int selectCountTotal() {
	  	return dao.selectCountTotal();
	}
	
	public List<ArticleVo> selectArticles(int limitStart) {
		return dao.selectArticles(limitStart);
	}
	
	//추가적인 서비스 로직
		public MultipartRequest uploadFile(HttpServletRequest req, String path) throws IOException {
			
			int maxSize = 1024 * 1024 *10;
			return new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
		}
		
		
		public String renameFile(String fname, String uid, String path) {
			//파일명 수정 
			int i = fname.lastIndexOf(".");
			String ext = fname.substring(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
			String now = sdf.format(new Date());
			String newName = now+uid+ext; //20221026160417_j101.txt
			
			File f1 = new File(path+"/"+fname);
			File f2 = new File(path+"/"+newName);
			
			f1.renameTo(f2);
			
			return newName;
		}
		
		public int getLastPageNum(int total) {
			int lastPageNum = 0;
			
			if(total % 10 == 0){
	   			lastPageNum = total / 10;
	   		}else{
	   			lastPageNum = total / 10;
	   		}
			
			return lastPageNum;
		}
	
	public int[] getpageGroupNum(int currentPage, int lastPageNum) {
		int pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
   		int pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
   		int pageGroupEnd = pageGroupCurrent * 10;
   		
   		if(pageGroupEnd > lastPageNum){
   			pageGroupEnd = lastPageNum;
   		}
   		
   		int[] result = {pageGroupStart, pageGroupEnd};
   		
   		return result;
	}
	
	public int getPageStartNum(int total, int currentPage) {
		int strat = (currentPage - 1) * 10;
		return total - strat;
	}
	
	public int getCurrentPage(String pg) {
		
		int currentPage = 1;
		
		if(pg != null){
   			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	public int getStartNum(int currentPage) {
		return(currentPage -1 ) * 10;
	}
	
	public int updateArticleHit(String no) {
		//조회수 +1
		return dao.updateArticleHit(no);
	}
	
	public ArticleVo selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleVo> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
}
