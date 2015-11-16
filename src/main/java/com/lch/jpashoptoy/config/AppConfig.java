package com.lch.jpashoptoy.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.lch.jpashoptoy.service", "com.lch.jpashoptoy.repository" })
public class AppConfig {
	
	@Bean
	public JpaTransactionManager jpaTransactionManager (){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.lch.jpashoptoy.domain");
        em.setJpaVendorAdapter(jpaVendorAdaper());
        em.setJpaPropertyMap(additionalProperties());
        em.afterPropertiesSet();
        return em;
	}
	
	@Bean
	public DataSource dataSource(){
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:jpashop");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	
	@Bean
    public static JpaVendorAdapter jpaVendorAdaper() {
        return new HibernateJpaVendorAdapter();
    }

    private static Map<String, Object> additionalProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.id.new_generator_mappings", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        // Second level cache configuration and so on.
        return properties;
    }
    
	
	// JPA 예외를 스프링 예외로 변환
	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor(); 
	}

}
