package mx.com.qtx.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceBean {
	private static final String URL_BD = "jdbc:mysql://localhost:3306/restBD?serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	private static final String USER_BD = "root"; 
	private static final String PASSWD_BD = "root"; 
	
	public DataSourceBean() {
		System.out.println("********* DataSourceBean() *********");
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
