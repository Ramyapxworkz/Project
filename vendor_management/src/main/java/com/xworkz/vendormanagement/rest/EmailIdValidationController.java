package com.xworkz.vendormanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vendormanagement.service.EmailService;

@RestController
@RequestMapping("/")
@EnableWebMvc
public class EmailIdValidationController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/emailVerification")
	public ResponseEntity<String> checkEmailInDatabase(@RequestParam String email) {
		boolean emailExistsInDatabase = emailService.saveOtpByEmail(email);
		if (emailExistsInDatabase) {
			return ResponseEntity.ok("Exists");
		} else {
			return ResponseEntity.ok("not exists");
		}
	}

	@PostMapping("/validateEmailVerificationOTP")
	public ResponseEntity<String> validateOTP(@RequestParam String email, @RequestParam String otp) {
		boolean validate = emailService.validateEmailVerificationOTP(email, otp);
		if (validate) {
			return ResponseEntity.ok("OTPvalid");
		} else {
			return ResponseEntity.ok("OTP not valid");
		}
	}

	

}
