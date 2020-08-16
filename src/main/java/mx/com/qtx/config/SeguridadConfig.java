package mx.com.qtx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String usuarios[] = {
							"alex",
							"david",
							"tavo",
							"benito"
							};
		String passwords[] = {
							"tekamachalko",
							"tekolutla",
							"tlatelolko",
							"bodoque"
							};
		String roles[][] = { 
				   		{ "USER", "AGENTE", "ADMIN"},
				   		{ "AGENTE"},
				   		{ "LOGISTICA"},
				   		{ "CLIENTE"}
		 		 };
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> autMem = auth.inMemoryAuthentication();
		for(int i=0; i<usuarios.length; i++) {
			autMem.withUser(usuarios[i]).password(passwords[i]).roles(roles[i]);
		}
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
