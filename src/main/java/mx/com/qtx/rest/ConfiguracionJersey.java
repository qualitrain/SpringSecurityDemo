package mx.com.qtx.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionJersey extends ResourceConfig {
	public ConfiguracionJersey() {
		this.register(CatalogosRest.class);
		this.register(PersonasRest.class);
		this.register(CorsFilter.class);
	}
}
