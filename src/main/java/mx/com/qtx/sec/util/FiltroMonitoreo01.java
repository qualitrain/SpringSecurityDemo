package mx.com.qtx.sec.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FiltroMonitoreo01 extends OncePerRequestFilter {
	
	private static Logger bitacora = LoggerFactory.getLogger(FiltroMonitoreo01.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		bitacora.info("doFilterInternal ANTES DE CAD FILTROS (servletPath:" + request.getServletPath() 
		 								+ ", URI:" + request.getRequestURI()
		 								+ ", PathInfo:" + request.getPathInfo()
				+ ")");
		filterChain.doFilter(request, response);
		bitacora.info("doFilterInternal DESPUES DE CAD FILTROS (servletPath:" + request.getServletPath() 
			+ ", URI:" + request.getRequestURI()
			+ ", PathInfo:" + request.getPathInfo()
			+ ")");
	}


}
