package Validation;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

public class ValidationFilter extends HttpFilter implements Filter {
       
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String key=(String)request.getAttribute("key");
		String user=(String)request.getAttribute("user");
		Credentials cs=(Credentials)request.getAttribute("cred");
		
		if(cs.checkKey(key, user)) {
			
			chain.doFilter(request, response);	
		}
	}

	

}
