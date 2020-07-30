package mx.com.qtx.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import mx.com.qtx.rest.CatalogosRest;
import mx.com.qtx.rest.PersonasRest;
import mx.com.qtx.sec.rest.AutenticacionRest;

@Configuration
public class ConfiguracionJersey extends ResourceConfig {
	public ConfiguracionJersey() {
		this.register(CatalogosRest.class)
		    .register(PersonasRest.class)
		    .register(AutenticacionRest.class)
		    .register(CorsFilter.class);
	}
}
