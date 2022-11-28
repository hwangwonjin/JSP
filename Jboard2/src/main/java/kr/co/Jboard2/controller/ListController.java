package kr.co.Jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.Vo.ArticleVo;
import kr.co.Jboard2.service.article.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
   		
		//현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
   		//전체 게시물 갯수 구하기
   		int total = service.selectCountTotal(search);
   		
   		//페이지 마지막 번호 계산
   		int lastPageNum = service.getLastPageNum(total);
   		
   		//페이지 그룹 start, end 계산
   		int[] result =  service.getpageGroupNum(currentPage, lastPageNum);
   		
   		//페이지 시작 번호
   		int pageStartNum = service.getPageStartNum(total, currentPage);
   		
   		//시작 인덱스
   		int start = service.getStartNum(currentPage);
   		
		//글 가져오기
		List<ArticleVo> articles = null;
		
		if(search == null) {
			articles = service.selectArticles(start);
		}else {
			articles = service.selectArticlesByKeyword(search, start);
		}
		
		
		req.setAttribute("articles", articles);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("search", search);
		req.setAttribute("pg", pg);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
