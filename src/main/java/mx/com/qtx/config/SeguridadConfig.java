package mx.com.qtx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
			.withUser("alex").password("tekamachalko").roles("USER","AGENTE","ADMIN")
		    .and()
		    .withUser("david").password("tekolutla").roles("AGENTE")
		    .and()
		    .withUser("tavo").password("tlatelolko").roles("LOGISTICA");
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
