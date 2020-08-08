package mx.com.qtx.sec.servicios;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import mx.com.qtx.sec.entidades.Autenticacion;
import mx.com.qtx.sec.entidades.TokenJWT;
import mx.com.qtx.sec.persistencia.DataSourceSeguridad;
import mx.com.qtx.sec.rest.IResultadoOperacion;
import mx.com.qtx.sec.rest.IServicioAutenticacionJWT;

@Service
public class ServicioAutenticacionSS implements IServicioAutenticacionJWT {
	@Autowired
	private AuthenticationManager autenticadorSS;
	
	@Autowired
	private UserDetailsService gestorUsuariosSS;

	private static Logger bitacora = LoggerFactory.getLogger(ServicioAutenticacionSS.class);
	
	public ServicioAutenticacionSS() {
		super();
		bitacora.info("Bean de Servicio de Autenticacion Impl Spring Security instanciado");
	}

	public AuthenticationManager getAutenticadorSS() {
		return autenticadorSS;
	}

	public void setAutenticadorSS(AuthenticationManager autenticadorSS) {
		this.autenticadorSS = autenticadorSS;
	}

	public UserDetailsService getGestorUsuariosSS() {
		return gestorUsuariosSS;
	}

	public void setGestorUsuariosSS(UserDetailsService gestorUsuariosSS) {
		this.gestorUsuariosSS = gestorUsuariosSS;
	}

	@Override
	public IResultadoOperacion registrarAutenticación(Autenticacion aut) {
		IResultadoOperacion resultadoAutenticacion = new ResultadoAutenticacion();
		try {
			Authentication autSS = new UsernamePasswordAuthenticationToken(aut.getNombreUsuario(), aut.getPassword());
			autSS = autenticadorSS.authenticate(autSS);
			
			UserDetails udt = gestorUsuariosSS.loadUserByUsername(autSS.getName());
			
			TokensJwtUtil tokenUtil = new TokensJwtUtil();
			Map<String,Object> mapClaims = new HashMap<>();
			mapClaims.put("autoridades", udt.getAuthorities());
//			mapClaims.put("autoridades", autSS.getAuthorities());
			String tokenJWT = tokenUtil.generarToken(udt.getUsername(), mapClaims);
//			String tokenJWT = tokenUtil.generarToken(autSS.getName(), mapClaims);
			
			
			resultadoAutenticacion.setObjResultadoOk(new TokenJWT(tokenJWT));
		}
		catch(DisabledException dex) {
			//Cuenta deshabilitada
			resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_USUARIO_INHABILITADO, dex.getMessage());
		}
		catch (LockedException lex) {
			//Cuenta bloqueda
			resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_CTA_BLOQUEDA, lex.getMessage());
		}
		catch (BadCredentialsException bcex) {
			resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_CREDENCIALES_EQUIVOCADAS, bcex.getMessage());			
		}
		catch (Throwable ex) {
			resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_GENERICO, ex.getMessage());
			while(ex.getCause() != null) {
				ex = ex.getCause();
				resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_GENERICO, ex.getClass().getName()
						                     + ":" + ex.getMessage());
			}
			bitacora.info(resultadoAutenticacion.getResumenErrores());
		}
		return resultadoAutenticacion;
	}

}
