package kr.co.Jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.service.user.UserService;

@WebServlet("/user/withdraw.do")
public class WithdrawController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		
		int result = service.updateUserWithdraw(uid);
		
		resp.setContentType("application/json;charset=UTF-8");
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
