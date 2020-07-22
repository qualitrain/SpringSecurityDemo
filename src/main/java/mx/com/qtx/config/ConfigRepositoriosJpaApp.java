package mx.com.qtx.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "mx.com.qtx.persistencia",
		entityManagerFactoryRef = "appEntityManagerFactory",
		transactionManagerRef = "appTransactionManager"
)

public class ConfigRepositoriosJpaApp {
	private static Logger bitacora = LoggerFactory.getLogger(ConfigRepositoriosJpaApp.class);
	private static final String[] PACK_SCAN_ENT_JPA= {"mx.com.qtx.entidades"};

	@Qualifier("bdApp")
	@Autowired
	private DataSource dataSourceApp;
	
	
	/*
	 * Your Spring Boot application implicitly activates Spring Web Mvc via @WebMvcAutoConfiguration which is triggered by having, among others, Servlet.class in your class path. 
	 * This @WebMvcAutoConfiguration requires a bean of type EntityManagerFactory. However, you have registered two such beans appEntityManagerFactory and seguridadEntityManagerFactory. 
	 * So you have to tell Spring Web Mvc which is your preferred one via @Primary on top of the respective @Bean method. 
	 * 
	 * O bien deshabilitar esta característica con
	 * spring.jpa.open-in-view=false      en application.properties
	 */
	//@Primary
	@Bean(name = "appEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBeanApp() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSourceApp);
		emfb.setPackagesToScan(PACK_SCAN_ENT_JPA);
		emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emfb.setPersistenceUnitName("appQtx");
		
		Properties props = new Properties();
		props.put("hibernate.hbm2ddl.auto", "validate");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		emfb.setJpaProperties(props);
		
		bitacora.info("Se ha finalizado la configuración del bean del EntityManagerFactory para la aplicación");
		return emfb;		
	}
	
	@Bean(name = "appTransactionManager")
	public PlatformTransactionManager getPlatformTransactionManagerBeanSec() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		EntityManagerFactory entityManagerFactory = this.getEntityManagerFactoryBeanApp()
				                                        .getObject();
	    transactionManager.setEntityManagerFactory(entityManagerFactory);
		bitacora.info("Se ha finalizado la configuración del TransactionManager para la aplicación");
	    return transactionManager;
	}
	
}
