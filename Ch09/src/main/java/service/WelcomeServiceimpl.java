package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServiceimpl implements CommonService{
	private static WelcomeServiceimpl instance = new WelcomeServiceimpl();
	public static WelcomeServiceimpl getInstance() {
		return instance;
	}
	

	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		return "/welcome.jsp";
	}
}
