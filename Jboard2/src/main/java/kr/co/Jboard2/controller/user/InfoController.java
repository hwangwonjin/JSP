package kr.co.Jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.service.user.UserService;

@WebServlet("/user/info.do")
public class InfoController extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher =req.getRequestDispatcher("/user/info.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		logger.info("uid :" +uid);
		logger.info("pass :" +pass);
		
		UserVo vo = service.selectUserForFindPass(uid, pass);
		
		logger.info("vo : " +vo);
		
		JsonObject json = new JsonObject();
		if(vo != null) {
			json.addProperty("result", 1);
			
			HttpSession sess = req.getSession();
			sess.setAttribute("sessUserForPass", vo);
			
		}else {
			json.addProperty("result", 0);
		}
		
		logger.info("1");
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
