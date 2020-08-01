package mx.com.qtx.sec.servicios;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.qtx.sec.config.IServTokensJwt;


@Service
public class ServicioTokensWebJwt_JJwt implements IServTokensJwt{
	TokensJwtUtil jwtUtil = new TokensJwtUtil();
	private static Logger bitacora = LoggerFactory.getLogger(ServicioTokensWebJwt_JJwt.class);
	
	public ServicioTokensWebJwt_JJwt() {
		super();
		bitacora.info("Se ha instanciado ServicioTokensJwt");
	}
		
	public boolean peticionTieneTokenValido(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null)
			return false;
		if(authorizationHeader.startsWith("Bearer ") == false)
			return false;

		String tokenJWT = authorizationHeader.substring(7);
		if(tokenJWT.isEmpty())
			return false;
		
		try {
			if(jwtUtil.tokenExpirado(tokenJWT))
				return false;
		}
		catch(Exception ex) {
			return false;
		}
		return true;
	}

	public String getNombreUsuario(HttpServletRequest request) {
		if(this.peticionTieneTokenValido(request) == false)
			return null;
		String token = request.getHeader("Authorization")
							  .substring(7);
		try {
			String nombreUsuario = this.jwtUtil.extraerUsuario(token);
			return nombreUsuario;
		}
		catch(Exception ex) {
			return null;		
		}
	}
	
}
