package com.watook.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.watook")
@PropertySource(value = { "classpath:ApplicationResources.properties", "classpath:jdbc.properties",
		"classpath:log4j.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	/*
	 * @Bean public ViewResolver getViewResolver(){ InternalResourceViewResolver
	 * resolver = new InternalResourceViewResolver();
	 * resolver.setPrefix("/WEB-INF/views/"); resolver.setSuffix(".jsp"); return
	 * resolver; }
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("watook.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("watook.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("watook.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("watook.jdbc.password"));

		return dataSource;
	}

}