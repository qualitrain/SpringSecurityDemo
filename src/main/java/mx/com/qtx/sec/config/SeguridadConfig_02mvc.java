package mx.com.qtx.sec.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Order(2)
public class SeguridadConfig_02mvc extends WebSecurityConfigurerAdapter {
	private static Logger bitacora = LoggerFactory.getLogger(SeguridadConfig_02mvc.class);
	@Autowired
	UserDetailsService udsQtx;
	
	//Configuracion de autenticación

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(udsQtx);
	}
	
	public SeguridadConfig_02mvc() {
		super();
		bitacora.info("Instanciando WebSecurityConfigurerAdapter (2)");
	}

	//Configuracion de autorizaciones
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Debe describir las reglas en orden de los más restrictivo a lo menos 
		http
//		.csrf()
//	        .ignoringAntMatchers("/api/autenticacion")
//	        .and()
	    .authorizeRequests()
			.antMatchers("/info","/css/*").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/logistica/**").hasAnyRole("LOGISTICA","ADMIN")
			.antMatchers("/**").authenticated()
			.and()
		.formLogin();
	}

}
