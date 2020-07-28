package mx.com.qtx.sec.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 *  Autenticacion por medio de jdbc 
 */
//@EnableWebSecurity
public class SeguridadConfig_jdbc extends WebSecurityConfigurerAdapter {
	private static Logger bitacora = LoggerFactory.getLogger(SeguridadConfig_jdbc.class);
	
	@Qualifier("bdSeguridad")
	@Autowired
	DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String queryUsuarios = "SELECT usr_nombre, usr_paswd, usr_habilitado "
								+ "FROM usuario WHERE usr_nombre = ?";
		
		//Los roles deben estar escritos en los registro de la base de datos con el prefijo "ROLE_"
		//Por ejemplo, ROLE_AGENTE o ROLE_LOGISTICA
		String queryRoles ="SELECT usr_nombre, aut_nombre "
						+ "FROM usuario, autoridad "
						+ "WHERE usr_nombre = ? "
						+ "AND usr_nombre = aut_nombre_usr";
		
		
		auth.jdbcAuthentication()
		       .dataSource(dataSource)
		       .usersByUsernameQuery(queryUsuarios)
		       .authoritiesByUsernameQuery(queryRoles);
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
				.and()
			.formLogin();
	}
}
