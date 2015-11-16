package com.lch.jpashoptoy;


import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		// 서비스, 레포지터리 컨텍스트 생성, 스프링 시큐리티 컨텍스트 생성
		appContext.setConfigLocations("classpath:appConfig.xml", "classpath:securityConfig.xml");
		servletContext.addListener(new ContextLoaderListener(appContext));

		// 서블릿 추가
		addDispatcherServlet(servletContext);
		addH2ConsoleServlet(servletContext);

		// 필터 추가
		addUtf8CharacterEncodingFilter(servletContext);
		addSpringSecurityFilter(servletContext);
	}


	/**
	 * Dispatcher Servlet 을 추가한다. true 로 한다.
	 * 
	 * @param servletContext
	 */
	private void addDispatcherServlet(ServletContext servletContext) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("classpath:webAppConfig.xml");

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
	
	/**
	 * h2 서블릿 추가한다.
	 * 
	 * @param servletContext
	 */

	private void addH2ConsoleServlet(ServletContext servletContext) {
		ServletRegistration.Dynamic h2Console = servletContext.addServlet("h2Console", new WebServlet());
		h2Console.setLoadOnStartup(2);
		h2Console.setInitParameter("-webAllowOthers", "true");
		h2Console.addMapping("/console/*");
	}

	/**
	 * UTF-8 캐릭터 인코딩 필터를 추가한다.
	 * 
	 * @param servletContext
	 */
	private void addUtf8CharacterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/*");
	}
	
	/**
	 * spring security 필터 추가
	 * 
	 * @param servletContext
	 */

	private void addSpringSecurityFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		filter.addMappingForUrlPatterns(null, false, "/*");
		
	}
}
