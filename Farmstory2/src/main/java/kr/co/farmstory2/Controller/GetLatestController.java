package kr.co.farmstory2.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.farmstory2.Vo.ArticleVo;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/getLatest.do")
public class GetLatestController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cate = req.getParameter("cate");
		
		List<ArticleVo> latests = service.selectLatests(cate);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(latests);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
