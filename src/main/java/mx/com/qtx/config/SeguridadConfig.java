package mx.com.qtx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	super.configure(auth);
		auth.inMemoryAuthentication()
			.withUser("alex").password("tekamachalko").roles("AGENTE","ADMIN")
		    .and()
		    .withUser("david").password("tekolutla").roles("AGENTE")
		    .and()
		    .withUser("tavo").password("tlatelolko").roles("LOGISTICA");
	}
	
	//Es requisito de Spring security tener configurado un codificador de passwords
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Debe describir las reglas en orden de los más restrictivo a lo menos 
		http.authorizeRequests()
				.antMatchers("/api/**").hasRole("AGENTE")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/logistica/**").hasAnyRole("LOGISTICA","ADMIN")
				.antMatchers("/info","/css/*").permitAll()
				.antMatchers("/**").authenticated()
				.and().formLogin();
	
	}
}
