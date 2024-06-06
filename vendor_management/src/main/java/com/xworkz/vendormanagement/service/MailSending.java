package com.xworkz.vendormanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.configuration.MailConfiguration;

@Service
public class MailSending {
	
	 @Autowired
	   private MailConfiguration mailConfiguration;

	    public boolean emailVerficationOtp(String email, String otp) {
	        JavaMailSender mailSender = mailConfiguration.mailConfig();
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("rramyap920@gmail.com"); // Set your email here
	        message.setTo(email);
	        message.setSubject("Thank you for Registering");
	        message.setText("Dear user" + ",\n\n"
	        		+"Your OTP for email verificarion: "+ otp +"\n\n"
	                + "Please use this OTP to verify your mail adress\n\n"
	                + "Best regards,\n"
	                + "Vendor management Team");

	        try {
	            mailSender.send(message); // Use mailSender to send the email
	            System.out.println("Mail sent successfully");
	            return true;
	        } catch(Exception e) {
	            e.printStackTrace();
	            return false;
	        }
}

	    
	    public boolean vendorloginemailVerficationOtp(String email, String otp) {
	        JavaMailSender mailSender = mailConfiguration.mailConfig();
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("rramyap920@gmail.com"); // Set your email here
	        message.setTo(email);
	        message.setSubject("login otp");
	        message.setText("Dear user" + ",\n\n"
	        		+"Your OTP for email verificarion: "+ otp +"\n\n"
	                + "Please use this OTP to verify your mail adress\n\n"
	                + "Best regards,\n"
	                + "Vendor management Team");

	        try {
	            mailSender.send(message); // Use mailSender to send the email
	            System.out.println("Mail sent successfully");
	            return true;
	        } catch(Exception e) {
	            e.printStackTrace();
	            return false;
	        }
}

	    
	    
}
