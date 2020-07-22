package mx.com.qtx.config.sec.prov.ud.jpa;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "mx.com.qtx.config.sec.prov.ud.jpa",
		entityManagerFactoryRef = "seguridadEntityManagerFactory",
		transactionManagerRef = "seguridadTransactionManager"
)
public class ConfigRepositoriosJpaSeguridad {
	private static Logger bitacora = LoggerFactory.getLogger(ConfigRepositoriosJpaSeguridad.class);
	private static final String[] PACK_SCAN_ENT_JPA= {"mx.com.qtx.config.sec.entidades"};
	
	@Qualifier("bdSeguridad")
	@Autowired
	private DataSource dataSourceSec;
	
	@Bean(name = "seguridadEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBeanSec() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSourceSec);
		emfb.setPackagesToScan(PACK_SCAN_ENT_JPA);
		emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emfb.setPersistenceUnitName("usuarios");
		
		Properties props = new Properties();
		props.put("hibernate.hbm2ddl.auto", "validate");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		emfb.setJpaProperties(props);
		
		bitacora.info("Se ha finalizado la configuración del bean del EntityManagerFactory para seguridad");
		return emfb;		
	}
	
	@Bean(name = "seguridadTransactionManager")
	public PlatformTransactionManager getPlatformTransactionManagerBeanSec() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		EntityManagerFactory entityManagerFactory = this.getEntityManagerFactoryBeanSec()
				                                        .getObject();
	    transactionManager.setEntityManagerFactory(entityManagerFactory);
		bitacora.info("Se ha finalizado la configuración del TransactionManager para seguridad");
	    return transactionManager;
	}
}
