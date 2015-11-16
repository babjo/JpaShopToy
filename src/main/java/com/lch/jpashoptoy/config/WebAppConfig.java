package com.lch.jpashoptoy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.lch.jpashoptoy.web" })
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

	@Bean
	public ViewResolver viewResolver() {
		// Example: the 'info' view logical name is mapped to the file
		// '/WEB-INF/jsp/info.jsp'
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
	}
	
	@Bean
	public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor(){
		OpenEntityManagerInViewInterceptor bean = new OpenEntityManagerInViewInterceptor();
		bean.setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
		return bean;
	}
	
}
