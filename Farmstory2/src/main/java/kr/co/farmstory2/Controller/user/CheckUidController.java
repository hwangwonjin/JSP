package kr.co.farmstory2.Controller.user;

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

import kr.co.farmstory2.service.UserService;

@WebServlet("/user/checkUid.do")
public class CheckUidController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("checkUid");
		
		String uid = req.getParameter("uid");
		logger.info("1");
		int result = service.selectCountUid(uid);
		logger.info("2");
		
		JsonObject json = new JsonObject();
		logger.info("3");
		json.addProperty("result", result);
		logger.debug("result : " + result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
