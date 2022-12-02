package kr.co.farmstory2.Controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.co.farmstory2.Vo.UserVo;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/findPw.do")
public class FindPwController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;

	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher =req.getRequestDispatcher("/user/findpw.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String email = req.getParameter("email");
		
		UserVo vo = service.selectUserForFindPw(uid, email);
		
		JsonObject json = new JsonObject();
		if(vo != null) {
			json.addProperty("result", 1);
			
			HttpSession sess =req.getSession();
			sess.setAttribute("sessUserForPw", vo);
		}else {
			json.addProperty("result", 0);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
