package mx.com.qtx.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceBean {
	private static final String NOMBRE_BD = "restBD";
	private static final String CMD_INIC_BD = "createDatabaseIfNotExist=true";
	private static final String URL_BD = "jdbc:mysql://localhost:3306/" 
	                                     + NOMBRE_BD 
	                                     + "?serverTimezone=UTC&" 
	                                     + CMD_INIC_BD; 
	private static final String USER_BD = "root"; 
	private static final String PASSWD_BD = "root"; 
	
	private static Logger bitacora = LoggerFactory.getLogger(DataSourceBean.class);
	
	
	public DataSourceBean() {
		System.out.println("********* DataSourceBean() *********");
		bitacora.info("Se ha instanciado data source mysql que apunta a BD" + NOMBRE_BD);
	}
	
	@ConfigurationProperties(prefix = "qtx.datasource")
	@Bean
	@Primary
	public DataSource getDataSource() {
		 return DataSourceBuilder
				 	.create()
				 	.url(URL_BD)
		 			.username(USER_BD)
		 			.password(PASSWD_BD)
		 			.build();
	}

}
