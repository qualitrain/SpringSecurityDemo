package mx.com.qtx.sec.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
public class SeguridadConfig_UserDetails extends WebSecurityConfigurerAdapter {
	private static Logger bitacora = LoggerFactory.getLogger(SeguridadConfig_UserDetails.class);
	
	@Autowired
	UserDetailsService udsQtx;
	
	@Autowired
	FiltroTokensJwt_SS filtroJWT;
	
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
		http
			.csrf()
		        .ignoringAntMatchers("/api/autenticacion")
		        .and()
		    .authorizeRequests()
				.antMatchers("/info","/css/*","/api/autenticacion").permitAll()
				.antMatchers("/api/**").hasRole("AGENTE")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/logistica/**").hasAnyRole("LOGISTICA","ADMIN")
				.antMatchers("/**").authenticated()
				.and()
			.formLogin();
		
		http.addFilterBefore(filtroJWT, UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
