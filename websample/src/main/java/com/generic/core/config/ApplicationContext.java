package com.generic.core.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Web_Platform/5/html/Hibernate_Core_Reference_Guide/configuration-optional.html
 * @author pkonwar
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.generic.rest.mvc","com.generic.filter","com.generic.core.services.serviceimpl", "com.generic.core.cache" })
@EnableTransactionManagement
@EnableWebMvc
@ImportResource("classpath:applicationContext.xml")
@PropertySource("classpath:application.properties")
public class ApplicationContext {
	
	@Resource
	private Environment environment;
	
	private static final String VIEW_RESOLVER_PREFIX = "/jsps/";
    private static final String VIEW_RESOLVER_SUFFIX = ".html";
	
	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
	private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	
	@Bean
    public DataSource dataSource() {
		
		URI dbUri;
		String username = null;
		String password = null;
		String dbUrl = null;
		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
			username = dbUri.getUserInfo().split(":")[0];
	        password = dbUri.getUserInfo().split(":")[1];
	        dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++ success");
		} catch (Exception e) {
			username = environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME);
	        password = environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD);
	        dbUrl = environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL);
		}
		System.out.println("*********************************");
		System.out.println("DB URL:"  + System.getenv("DATABASE_URL"));
		System.out.println("Username:" + username);
		System.out.println("password:" + password);
		System.out.println("Url:" + dbUrl);
		System.out.println("*********************************");
		
		BoneCPDataSource dataSource = new BoneCPDataSource();
    	dataSource.setDriverClass(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    	dataSource.setJdbcUrl(dbUrl);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		
		LocalContainerEntityManagerFactoryBean entiryManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		entiryManagerFactoryBean.setDataSource(dataSource());
		entiryManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		entiryManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
		
		Properties jpaProperties = new Properties();
		
		//The classname of a Hibernate org.hibernate.dialect.Dialect which allows Hibernate to generate SQL optimized for a particular relational database.
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		
		//Pretty print the SQL in the log and console.
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        
		//Automatically validates or exports schema DDL to the database when the SessionFactory is created. With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.
		//e.g. validate | update | create | create-drop
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        
        //Write all SQL statements to console.
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

		entiryManagerFactoryBean.setJpaProperties(jpaProperties);
		
		return entiryManagerFactoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		return transactionManager;
	}
	
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		messageSource.setBasename(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
		messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

		return messageSource;
	}
	
	
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		return viewResolver;
	}

}
