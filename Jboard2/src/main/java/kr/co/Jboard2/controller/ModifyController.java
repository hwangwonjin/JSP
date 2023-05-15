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

import kr.co.Jboard2.Vo.ArticleVo;
import kr.co.Jboard2.service.article.ArticleService;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		ArticleVo article = service.selectArticle(no);
		
		req.setAttribute("no", no);
		req.setAttribute("pg", pg);
		req.setAttribute("title", title);
		req.setAttribute("content", content);
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		req.setAttribute("no", no);
		req.setAttribute("title", title);
		req.setAttribute("content", content);
		
		service.updateArticle(no, title, content);
		
		resp.sendRedirect("/Jboard2/view.do?pg="+pg+"&no="+no+"result=201");
	}
}
