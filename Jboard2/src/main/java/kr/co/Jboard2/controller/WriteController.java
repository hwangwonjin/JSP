package kr.co.Jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.service.article.ArticleService;

import kr.co.Jboard2Vo.ArticleVo;

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
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String fname = req.getParameter("fname");
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();
		
		logger.info("1");
		
		ArticleVo ar  = new ArticleVo();
		ar.setTitle(title);
		ar.setContent(content);
		ar.setFname(fname);
		ar.setUid(uid);
		ar.setRegip(regip);
		logger.info("3");
		
		service.insertArticle(ar);
		logger.info("4");
		
		resp.sendRedirect("/Jboard2/list.do");
	}
}
