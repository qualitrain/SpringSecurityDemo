package mx.com.qtx.sec.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SeguridadConfig_UserDetails extends WebSecurityConfigurerAdapter {
	private static Logger bitacora = LoggerFactory.getLogger(SeguridadConfig_UserDetails.class);
	
	@Autowired
	UserDetailsService udsQtx;
	
	//Configuracion de autenticación
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(udsQtx);
	}
	
	//Es requisito de Spring security tener configurado un codificador de passwords
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	//Configuracion de autorizaciones
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Debe describir las reglas en orden de los más restrictivo a lo menos 
		http.authorizeRequests()
				.antMatchers("/api/**").hasRole("AGENTE")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/logistica/**").hasAnyRole("LOGISTICA","ADMIN")
				.antMatchers("/info","/css/*").permitAll()
				.antMatchers("/**").authenticated()
				.and()
			.formLogin();
	}
}
