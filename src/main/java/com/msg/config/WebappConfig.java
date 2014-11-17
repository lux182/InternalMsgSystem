package com.msg.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

public class WebappConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.setProperty("webroot", servletContext.getRealPath("/"));
		
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(SpringMVCConfig.class);
		servletContext.addListener(new ContextLoaderListener(mvcContext));
		FilterRegistration.Dynamic httpMethodFilter = servletContext.addFilter(
				"httpMethodFilter", new HiddenHttpMethodFilter());
		httpMethodFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "SpringMVC");
		
		ServletRegistration.Dynamic mvcDispatcher = servletContext.addServlet(
		        "SpringMVC", new DispatcherServlet(mvcContext));
		mvcDispatcher.addMapping("/");
		mvcDispatcher.setLoadOnStartup(1);
		
		ServletRegistration.Dynamic druidDispatcher = servletContext.addServlet(
		        "DruidStatView", new StatViewServlet());
		druidDispatcher.addMapping("/druid/*");
		
		FilterRegistration.Dynamic druidFilter = servletContext.addFilter(
				"DruidWebStatFilter", new WebStatFilter());
		druidFilter.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		druidFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
		
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
				"encodingFilter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
		OpenEntityManagerInViewFilter entityManagerFactory = new OpenEntityManagerInViewFilter();
		entityManagerFactory.setEntityManagerFactoryBeanName("openSessionFactoryBean");
		FilterRegistration.Dynamic openSession = servletContext.addFilter(
				"openSession",entityManagerFactory);
		openSession.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
	}

}
