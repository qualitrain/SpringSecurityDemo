package mx.com.qtx.config.sec.prov.ud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.com.qtx.config.sec.entidades.Usuario;

@Service
public class ServicioUserDetail implements UserDetailsService {

	@Autowired
	IGestorDatosSec gestorDatos;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.gestorDatos.getUsuarioXID(username);
		return new QtxUserDetails(usuario);
	}

}
