package com.xworkz.vendormanagement.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("This is getRootConfigClasses");

		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("This is getServletConfigClasses");
		return new Class[] {WebConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		log.info("This is getServletMappings");

		return new String[] {"/"};
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	

}
