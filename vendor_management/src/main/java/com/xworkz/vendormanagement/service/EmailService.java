package com.xworkz.vendormanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
       boolean saveOtpByEmail(String email);
   	boolean validateEmailVerificationOTP(String email, String otp);

}
