package mine.note.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mine.note.vo.UserVo;


public class LoginFilter implements Filter{

	String loginPage = null;
	//String centerPage = null;
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("----------化登录拦截结束----------");
		loginPage = null;
		//centerPage = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("----------执行登录拦截----------");
	 
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
	    String contextPath =  req.getServletPath();
        String uri = contextPath.substring(contextPath.lastIndexOf("/"));
	   
        HttpSession session = req.getSession();
		UserVo userVo = (UserVo)session.getAttribute("sessionUser");
        if(userVo!=null || uri.equals(loginPage)){
        	
        	chain.doFilter(request, response);
        
        }else{
		
        	req.getRequestDispatcher(loginPage).forward(req, resp);

        }
		 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	    System.out.println("----------初始化登录拦截----------");
		loginPage = filterConfig.getInitParameter("loginPage");
		//centerPage = filterConfig.getInitParameter("centerPage");
	}
	
}
