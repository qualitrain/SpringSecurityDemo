package mx.com.qtx.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.qtx.entidades.Persona;

@Path("personas")
public class PersonasRest {

	@Autowired
	private IServicioPersonas servicioPersonas;
	private static Logger bitacora = LoggerFactory.getLogger(PersonasRest.class);

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Persona> getPersonas() {
		bitacora.info("getPersonas()");
		List<Persona> personas = this.servicioPersonas.getPersonasTodas();
		return personas;
	}
	@Path("{id}")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Persona getPersona(
			@PathParam("id")
			Long id) {
		bitacora.info("getPersona("+ id + ")");
		
		Persona persona = this.servicioPersonas.getPersona(id);
		return persona;
	}

}
