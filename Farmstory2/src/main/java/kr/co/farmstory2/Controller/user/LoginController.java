package kr.co.farmstory2.Controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.Vo.UserVo;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sussess = req.getParameter("success");
		req.setAttribute("success", sussess);
		
		RequestDispatcher dispatcher =req.getRequestDispatcher("/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String auto = req.getParameter("auto");
		
		UserVo vo = service.selectUser(uid, pass);
		
		if(vo != null) {
			//회원이 맞는경우
			HttpSession sess = req.getSession();
			sess.setAttribute("sessUser", vo);
			
			if(auto != null) {
				String sessId = sess.getId();
				
				Cookie cookie = new Cookie("SESSID", sessId);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*3);
				resp.addCookie(cookie);
				
				service.updateUserForSession(uid, sessId);
			}
			
			resp.sendRedirect("/Farmstory2/");
		}else {
			resp.sendRedirect("/Farmstory2/user/login.do?success=100");
		}
	}
}
