package mx.com.qtx.config.sec.prov.ud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import mx.com.qtx.config.sec.entidades.Autoridad;
import mx.com.qtx.config.sec.entidades.Usuario;

public class QtxUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	
	public QtxUserDetails(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autoridades = new ArrayList<>();
		for(Autoridad autoridadI:this.usuario.getAutoridades())
			autoridades.add(  new SimpleGrantedAuthority(autoridadI.getNombre()) );
		return autoridades;
	}

	@Override
	public String getPassword() {
		return this.usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return this.usuario.getNombre();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.usuario.estaHabilitado();
	}

}
