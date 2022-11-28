package kr.co.Jboard2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.service.user.UserService;


public class AutoLoginFilter implements Filter{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.instance;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("AutoLoginFilter...");
		
		//현재 로그인 상태 확인
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sess = req.getSession();
		
		UserVo sessUser = (UserVo) sess.getAttribute("sessUser");
		
		if(sessUser != null) {
			//로그인 상태인 경우
			//다음 필터 실행
			chain.doFilter(request, response);
		}else {
			//로그인 상태가 아닐경우
		
			//자동 로그인 여부에 따라 로그인 처리
			Cookie[] cookies = req.getCookies();
			
				if(cookies != null) {
					
					for(Cookie cookie : cookies) {
						
						if(cookie.getName().equals("SESSID")) {
							
							String sessId = cookie.getValue();
							UserVo vo =service.selectUserBySessId(sessId);
							
							if(vo != null) {
								
								//로그인 처리
								sess.setAttribute("sessUser", vo);
								
								// 쿠키 만료일 연장
								cookie.setMaxAge(60*60*24*3);
								((HttpServletResponse)response).addCookie(cookie);
								
								//데이터베이스 sessId 만료일 연장
								service.updateUserForsessLimitDate(sessId);
							}
						}
					}
				}
			
			//다음 필터 실행
			chain.doFilter(request, response);
		}
	}
}
