package kr.co.Jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.Jboard2.Vo.ArticleVo;
import kr.co.Jboard2.service.article.ArticleService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
		logger.info("0");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일업로드
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/file");
		
		MultipartRequest mr  = service.uploadFile(req, path);
		
		//multipart 폼 데이터 수신
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String uid = mr.getParameter("uid");
		String fname = mr.getFilesystemName("fname");
		String regip = req.getRemoteAddr();
		
		ArticleVo article = new ArticleVo();
		article.setTitle(title);
		article.setContent(content);
		article.setUid(uid);
		article.setFname(fname);
		article.setRegip(regip);
		
		
		
		
		//글 등록
		int parent = service.insertArticle(article);
			
		// 파일을 첨부했으면
		if(fname != null){
			//파일명 수정
			String newName = service.renameFile(fname, uid, path);
			
			//파일 테이블 insert
			
			service.insertFile(parent, newName, fname);
		}
		
		
		//리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
