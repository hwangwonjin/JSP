package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServiceimpl implements CommonService{

	private static HelloServiceimpl instance = new HelloServiceimpl();
	public static HelloServiceimpl getInstance() {
		return instance;
	}
	private HelloServiceimpl() {}
	
	
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		return "/hello.jsp";
	}
	
	
}
