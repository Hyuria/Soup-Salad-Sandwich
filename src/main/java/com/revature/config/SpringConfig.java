package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan(basePackages="com.revature")
@Configuration
public class SpringConfig implements WebMvcConfigurer {
	// this configuration file replaces what could also be an xml configuration
	// the xml configuration would typically include the following tags, which
	// are replaced by the above annotations:
	// <mvc:annotation-driven></mvc:annotation-driven>
	// <context:component-scan base-package="com.revature"></context:component-scan>
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(5606660);
		resolver.setMaxInMemorySize(56066600);
		resolver.setMaxUploadSizePerFile(5606660);
		return resolver;
	}
}
