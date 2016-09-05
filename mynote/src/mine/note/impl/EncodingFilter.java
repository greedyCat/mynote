package mine.note.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String encording;
	Map<String, String> params = new HashMap<String, String>();
	
	public void destroy() {
		// TODO Auto-generated method stub
		 
	      params=null;  
	      encording=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		   
		request.setCharacterEncoding(encording);
		response.setCharacterEncoding(encording);
		chain.doFilter(request, response);
	 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		 
		encording = filterConfig.getInitParameter("encoding");
		@SuppressWarnings("rawtypes")
		Enumeration e = filterConfig.getInitParameterNames();
		while(e.hasMoreElements()) {  
	            String name = (String) e.nextElement();  
	            String value = filterConfig.getInitParameter(name);  
	            params.put(name, value);  
	        }
	}

}
