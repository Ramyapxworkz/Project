package com.xworkz.vendormanagement.configuration;

import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
	public JavaMailSenderImpl mailConfig() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("rramyap920@gmail.com");
		mailSender.setPassword("wixa ckpn uyee cjsh");
		Properties properties=mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol","smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug","true");
		return mailSender;
	}
}
