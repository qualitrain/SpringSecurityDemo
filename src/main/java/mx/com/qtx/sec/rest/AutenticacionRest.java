package mx.com.qtx.sec.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.qtx.rest.PersonasRest;
import mx.com.qtx.sec.entidades.Autenticacion;
import mx.com.qtx.sec.entidades.TokenJWT;

@Path("autenticacion")
public class AutenticacionRest {
	@Autowired
	private IServicioAutenticacion servAutenticacion;
	
	private static Logger bitacora = LoggerFactory.getLogger(AutenticacionRest.class);
	
	public AutenticacionRest() {
		bitacora.info("Se ha instanciado el EndPoint de Autenticación");
	}
	public IServicioAutenticacion getServAutenticacion() {
		return servAutenticacion;
	}
	public void setServAutenticacion(IServicioAutenticacion servAutenticacion) {
		this.servAutenticacion = servAutenticacion;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TokenJWT registrarAutenticacion(Autenticacion aut) {
		bitacora.info("registrarAutenticacion(" + aut + ")");
		IResultadoOperacion resultado = this.servAutenticacion.registrarAutenticación(aut);
		if(resultado.todoOk())
			return (TokenJWT) resultado.getObjResultadoOk();
		
		throw ErrorRest.getError(resultado.getResumenErrores(), 
				                 ErrorRest.ERR_AUTENTICACION_FALLIDA, 
				                 Response.Status.UNAUTHORIZED);

	}

	
}
