package kr.co.farmstory2.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.Vo.UserVo;




public class LoginCheckFilter implements Filter{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private List<String> uriList;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//필터를 동작할 요청주소 리스트 구성
		uriList = new ArrayList<>();
		uriList.add("/Farmstory2/list.do");
		uriList.add("/Farmstory2/write.do");
		uriList.add("/Farmstory2/modify.do");
		uriList.add("/Farmstory2/view.do");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("LoginCheckFilter..");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		
		HttpSession sess = req.getSession();
		UserVo sessUser = (UserVo)sess.getAttribute("sessUser");
		
		if(uriList.contains(uri)) {
			
			
			//로그인을 하지 않았을 경우
			if(sessUser == null) {
				((HttpServletResponse)response).sendRedirect("/Farmstory2/user/login.do");
				return;
			}
		}else if(uri.contains("/user/login.do")){
			
			if(sessUser != null) {
				((HttpServletResponse)response).sendRedirect("/Farmstory2/user/login.do");
				return;
			}
			
		}
		
		
		//다음 필터 수행
		chain.doFilter(request, response);
	}

}
