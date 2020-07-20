package mx.com.qtx.config.sec;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class GestorDatosSecRepositoryJPA implements IGestorDatosSec {
	
	@Autowired
	private IUsuarioRepository repoUsuarios;

	private static Logger bitacora = LoggerFactory.getLogger(GestorDatosSecRepositoryJPA.class);
 
	public GestorDatosSecRepositoryJPA() {
		bitacora.info("Instanciando GestoDatosSecRepositorioJPA ");
	}

	public IUsuarioRepository getRepoUsuarios() {
		return repoUsuarios;
	}

	public void setRepoUsuarios(IUsuarioRepository repoUsuarios) {
		this.repoUsuarios = repoUsuarios;
	}

	@Override
	public Usuario getUsuarioXID(String nombreUsuario) {
		bitacora.info("getUsuarioXID( " + nombreUsuario + " )");
		Optional<Usuario> usuario =  this.repoUsuarios.findById(nombreUsuario);
		if (usuario.isPresent())
			return usuario.get();
		return null;
	}

}
