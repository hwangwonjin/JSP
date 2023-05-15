package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.service.user.UserService;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher =req.getRequestDispatcher("/user/myInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("myInfo doPost...");
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		UserVo vo = new UserVo();
		vo.setUid(uid);
		vo.setName(name);
		vo.setNick(nick);
		vo.setEmail(email);
		vo.setHp(hp);
		vo.setZip(zip);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		
		service.updateUser(vo);
		
	}
}
