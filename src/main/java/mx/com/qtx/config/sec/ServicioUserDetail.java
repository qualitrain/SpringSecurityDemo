package mx.com.qtx.config.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
