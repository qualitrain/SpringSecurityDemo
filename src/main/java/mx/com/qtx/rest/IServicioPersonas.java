package mx.com.qtx.rest;

import java.util.List;

import mx.com.qtx.entidades.Persona;

public interface IServicioPersonas {
	public List<Persona> getPersonasTodas();
	public Persona getPersona(long id);
}
