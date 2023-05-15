package kr.co.Jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.service.article.ArticleService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		
		service.deleteArticle(no);
		
		resp.sendRedirect("/Jboard2/list.do?result=202");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
