package bo.com.cognos.proyecto.security.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtTokenFilter extends GenericFilterBean{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public void doFilter(ServletRequest req, ServletResponse res, 
	         FilterChain chain) throws IOException, ServletException {
	  String token =   jwtTokenProvider.resolveToken((HttpServletRequest) req);
	  if (token != null && jwtTokenProvider.validateToken(token)) {
	      Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
	      SecurityContextHolder.getContext().setAuthentication(auth);
	  }
	  chain.doFilter(req, res);
	}

}
