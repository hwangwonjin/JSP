package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreetingServiceimpl implements CommonService{
	
	private static GreetingServiceimpl instance = new GreetingServiceimpl();
	public static GreetingServiceimpl getInstance() {
		return instance;
	}
	private GreetingServiceimpl() {}
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		return "/greeting.jsp";
	}
}
