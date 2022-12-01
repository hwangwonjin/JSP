package kr.co.farmstory2.Controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/commentDelete.do")
public class CommentDelete extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public void init() throws ServletException {
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		
		int result = service.deleteComment(no, parent);
		
		logger.debug("result : "+result);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		logger.debug("result1 : "+result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		logger.debug("writer : "+writer);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
