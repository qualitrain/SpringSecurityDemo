
  package mx.com.qtx.sec.config;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
  
  import mx.com.qtx.sec.util.FiltroMonitoreo02;
  
  @EnableWebSecurity 
  @Order(2) 
  public class SeguridadConfig_02mvc extends WebSecurityConfigurerAdapter { 
	  private static Logger bitacora =  LoggerFactory.getLogger(SeguridadConfig_02mvc.class);
  
  @Autowired 
  UserDetailsService udsQtx;
  
  @Autowired 
  FiltroMonitoreo02 filtroMonitoreo02; //Configuracion de autenticación
  
  @Override 
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(udsQtx); 
  }
  
  
  public SeguridadConfig_02mvc() { 
	  super();
	  bitacora.info("Instanciando WebSecurityConfigurerAdapter (2)"); 
  }
  
  //Configuracion de cadena de filtrado de Spring Security
  
  @Override 
  protected void configure(HttpSecurity http) throws Exception {
	  //Debe describir las reglas en orden de los más restrictivo a lo menos 
	  http
	  	.antMatcher("/**") // Define que patrones de URL´s atiende esta cadena de filtrado. Si hay varias, debemos tener 
	                     // distintas configuraciones (clases que extienden de WebSecurityConfigurerAdapter).
	                     // Cada una debe anotar el orden de aplicación con @Order(numero). Las primeras DEBEN SER las más específicas.
	  
	  	.authorizeRequests() //Define las reglas de autorización a aplicar sobre el universo de URLs que atiende ESTA cadena de filtrado
		  .antMatchers("/info","/css/*","/login").permitAll()
		  .antMatchers("/admin/**").hasRole("ADMIN")
		  .antMatchers("/logistica/**").hasAnyRole("LOGISTICA","ADMIN")
		  .antMatchers("/**","/").authenticated() 
  		  .and()
  		.formLogin() 
  		  .and() 
        .logout().invalidateHttpSession(true) 
     	  .and()
        .addFilterBefore(filtroMonitoreo02, WebAsyncManagerIntegrationFilter.class);
  
	  http.sessionManagement().maximumSessions(1); 
	 }
  
  }
 