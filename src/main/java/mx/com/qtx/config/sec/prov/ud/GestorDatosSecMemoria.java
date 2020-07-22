package mx.com.qtx.config.sec.prov.ud;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import mx.com.qtx.config.sec.entidades.Autoridad;
import mx.com.qtx.config.sec.entidades.Usuario;

//@Primary
@Repository
public class GestorDatosSecMemoria implements IGestorDatosSec {
	private Map<String,Usuario> usuarios;

	public GestorDatosSecMemoria() {
		this.usuarios = new HashMap<>();
		this.cargarUsuarios();
	}

	private void cargarUsuarios() {
		Usuario usuario = new Usuario("alex","tekamachalko",true);
		Set<Autoridad> autoridades = new HashSet<>();
		autoridades.add(new Autoridad(1,"ROLE_ADMIN",usuario));
		autoridades.add(new Autoridad(2,"ROLE_LOGISTICA",usuario));
		usuario.setAutoridades(autoridades);
		
		this.usuarios.put(usuario.getNombre(), usuario);
		
		Usuario usuario2 = new Usuario("david","tekolutla",true);
		Set<Autoridad> autoridades2 = new HashSet<>();
		autoridades2.add(new Autoridad(3,"ROLE_AGENTE",usuario2));
		usuario2.setAutoridades(autoridades2);
		
		this.usuarios.put(usuario2.getNombre(), usuario2);
		
		Usuario usuario3 = new Usuario("tavo","tlatelolko",true);
		Set<Autoridad> autoridades3 = new HashSet<>();
		autoridades3.add(new Autoridad(4,"ROLE_LOGISTICA",usuario3));
		usuario3.setAutoridades(autoridades3);
		
		this.usuarios.put(usuario3.getNombre(), usuario3);
		
	}

	@Override
	public Usuario getUsuarioXID(String nombreUsuario) {
		return this.usuarios.get(nombreUsuario);
	}

}
