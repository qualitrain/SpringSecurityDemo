package mx.com.qtx.sec.config;

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
public class FiltroTokensJwt_SS extends OncePerRequestFilter {
	@Autowired
    private IServTokensJwt servTokens;

	@Autowired
	private UserDetailsService gestorUsuariosSS;
	
	private static Logger bitacora = LoggerFactory.getLogger(FiltroTokensJwt_SS.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		bitacora.info("doFilterInternal(servletPath:" + request.getServletPath() 
		 								+ ", URI:" + request.getRequestURI()
		 								+ ", PathInfo:" + request.getPathInfo()
				+ ")");
		procesarToken(request);
		filterChain.doFilter(request, response);
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String servletPath = request.getServletPath();
		String pathAutenticaion = servletPath + request.getPathInfo();
		if(pathAutenticaion.equals("/api/autenticacion"))
			return true;
		if(servletPath.startsWith("/api") == false)
			return true;
			
		return false;
	}
	private void procesarToken(HttpServletRequest request) {
		
		if (servTokens.peticionTieneTokenValido(request) == false)
			return;
		
		String nombreUsuario = servTokens.getNombreUsuario(request);
		if(nombreUsuario == null)
			return;
		completarAutenticacionSS(nombreUsuario, request);
		
	}

	private void completarAutenticacionSS(String nombreUsuario, HttpServletRequest request) {
		 SecurityContext ctxSeguridad = SecurityContextHolder.getContext();
		 if(ctxSeguridad.getAuthentication() != null) {
			 bitacora.info("YA HAY un principal en la petición (no debería ser así): "
			 		+ "nombrePrincipal:" + ctxSeguridad.getAuthentication().getName());
			 return; // Ya hay un token de Autenticación en el contexto de seguridad
		 }
		 
		 WebAuthenticationDetailsSource wads = new WebAuthenticationDetailsSource();
		 WebAuthenticationDetails webDetails = wads.buildDetails(request);
		 
		 UserDetails usuarioUd = this.gestorUsuariosSS.loadUserByUsername(nombreUsuario);
		 UsernamePasswordAuthenticationToken tknAutenticacion = null;
		 tknAutenticacion = new UsernamePasswordAuthenticationToken(usuarioUd, usuarioUd.getPassword(), 
				                                                              usuarioUd.getAuthorities());
		 tknAutenticacion.setDetails(webDetails); // Se agregan datos relacionados con la petición
		 ctxSeguridad.setAuthentication(tknAutenticacion); //Se agrega token de autenticación de este usuario al contexto
		 
		 bitacora.info("Se ha agregado token de autenticación a peticion c/Token JWT:" + tknAutenticacion);
		
	}


}
