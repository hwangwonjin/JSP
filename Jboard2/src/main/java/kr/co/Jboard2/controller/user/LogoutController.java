package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.service.user.UserService;
@WebServlet("/user/logout.do")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess =req.getSession();
		UserVo sessUser = (UserVo)sess.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		//세션삭제
		sess.removeAttribute("sessUser");
		sess.invalidate();
		
		//쿠키삭제
		Cookie cookie = new Cookie("SESSID", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		//데이터베이스 사용자 sessId update
		service.updateUserForSessionOut(uid);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=200");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
