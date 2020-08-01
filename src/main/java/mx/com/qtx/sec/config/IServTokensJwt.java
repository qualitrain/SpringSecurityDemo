package mx.com.qtx.sec.config;

import javax.servlet.http.HttpServletRequest;

public interface IServTokensJwt {
	public boolean peticionTieneTokenValido(HttpServletRequest request);
	public String getNombreUsuario(HttpServletRequest request);
}
