package com.msg.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.msg.interceptor.RecIdInterceptor;
import com.msg.service.MyShiroRealm;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.msg.**")
@EnableJpaRepositories(basePackages = { "com.msg.**" })
@ImportResource("classpath:rabbitmq.xml")
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setContentType("text/html; charset=utf-8");
		resolver.setCache(true);
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean(name="dataSource")
	public DataSource dataSource() throws Exception {
		return DruidDataSourceFactory.createDataSource(configProperties());
	}
	
	@Bean(name="propertiesMap")
	public Properties configProperties() throws IOException{
		return PropertiesLoaderUtils.loadProperties(new ClassPathResource("config.properties"));
	}
	
	@Bean(name = "entityManagerFactory")
	@Qualifier("propertiesMap")
	@Resource
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(Properties propertiesMap, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean
				.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean
				.setPackagesToScan("com.msg.**");
		entityManagerFactoryBean.setJpaProperties(propertiesMap);
		return entityManagerFactoryBean;
	}
	
	@Bean(name="openSessionFactoryBean")
	@Resource
	public EntityManagerFactory openSessionFactoryBean(LocalContainerEntityManagerFactoryBean entityManagerFactory){
		return entityManagerFactory.getObject();
	}

	@Bean(name = "transactionManager")
	@Resource
	public JpaTransactionManager configureJpaTransactionManager(
			LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
	
	@Bean
	@Qualifier("propertiesMap")
	public DefaultKaptcha configCaptchaProducer(Properties propertiesMap) {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(new Config(propertiesMap));
		return defaultKaptcha;
	}

//	i18n
	private static MessageSource messageSource = null;
	private static final Object locked = new Object();
	public static MessageSource getMessageSource(){
		synchronized(locked){
			if(messageSource==null){
				ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
				ms.setBasenames("system","message");
				ms.setUseCodeAsDefaultMessage(true);
				messageSource=ms;
			}
		}
		return messageSource;
	}
	
	@Bean(name="messageSource")
	public MessageSource i18nMessageSource(){
		return getMessageSource();
	}
	
	@Bean(name="localeResolver")
	public SessionLocaleResolver sessionLocaleResolver(){
		return new SessionLocaleResolver();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
		registry.addInterceptor(new RecIdInterceptor());
	}

	
	
//validator
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		validator.setValidationMessageSource(i18nMessageSource());
		return validator;
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		return new MethodValidationPostProcessor();
	} 
//Shiro	
	
		@Bean
		public MyShiroRealm configMyShiroRealm(){
			return new MyShiroRealm();
		}
		
		@Bean(name="sessionIdCookie")
		public SimpleCookie sessionIdCookie(){
			SimpleCookie sc = new SimpleCookie("sid");
			sc.setHttpOnly(true);
			sc.setMaxAge(-1);
			return sc;
		}
		
		@Bean(name="rememberMeManager")
		public CookieRememberMeManager rememberMeManager(){
			CookieRememberMeManager rm=new CookieRememberMeManager();
			//rm.setCipherKey("#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}".getBytes());
			SimpleCookie sc = new SimpleCookie("rememberMe");
			sc.setHttpOnly(true);
			sc.setMaxAge(2592000);
			rm.setCookie(sc);
			return rm;
		}

		@Bean
		@Resource
		public DefaultWebSecurityManager configDefaultWebSecurityManager(MyShiroRealm myShiroRealm,
				CookieRememberMeManager rememberMeManager){
			MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
			DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
			securityManager.setRealm(myShiroRealm);
			myShiroRealm.setCacheManager(cacheManager);
			securityManager.setCacheManager(cacheManager);
			securityManager.setRememberMeManager(rememberMeManager);
			return securityManager;
		}
		
		@Bean(name="shiroFilter")
		@Resource
		public ShiroFilterFactoryBean configShiroFilterFactoryBean(
				DefaultWebSecurityManager securityManager){
					
			ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
			shiroFilter.setSecurityManager(securityManager);
			shiroFilter.setLoginUrl("/");
			shiroFilter.setSuccessUrl("/");
			shiroFilter.setUnauthorizedUrl("/");
			shiroFilter.setFilterChainDefinitionMap(MyShiroRealm.shiroMap);
			return shiroFilter;
		}

		
		@Bean
		@Resource
		public AuthorizationAttributeSourceAdvisor configAuthorizationAttributeSourceAdvisor(
				DefaultWebSecurityManager securityManager){
			AuthorizationAttributeSourceAdvisor author = new AuthorizationAttributeSourceAdvisor();
			author.setSecurityManager(securityManager);
			return author;
		}
//File upload 
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(4194304);
		return cmr;
	}
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImplBean() throws IOException{
		Properties properties=configProperties();
		JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
		javaMail.setProtocol(properties.getProperty("mail.smtp.protocol"));
		javaMail.setUsername(properties.getProperty("mail.smtp.username"));
		javaMail.setPassword(properties.getProperty("mail.smtp.password"));
		javaMail.setHost(properties.getProperty("mail.smtp.host"));
		javaMail.setPort(Integer.parseInt(properties.getProperty("mail.smtp.port")));
		javaMail.setJavaMailProperties(properties);
		return javaMail;
	}
	
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutorBean(){
		ThreadPoolTaskExecutor taskManager = new ThreadPoolTaskExecutor();
		taskManager.setCorePoolSize(1);
		taskManager.setMaxPoolSize(30);
		return taskManager;
	}
	
}
