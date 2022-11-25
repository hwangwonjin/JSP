package kr.co.Jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.Jboard2.service.article.ArticleService;
import kr.co.Jboard2Vo.ArticleVo;

@WebServlet("/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		
		
		//조회수 +1
		int hit = service.updateArticleHit(no);
		
		
		//글 가져오기
		 ArticleVo article = service.selectArticle(no);
		
		//댓글 가져오기
		 List<ArticleVo> comments = service.selectComments(no);
		
		req.setAttribute("hit", hit);
		req.setAttribute("article", article);
		req.setAttribute("commetns", comments);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
