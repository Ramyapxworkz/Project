package com.xworkz.vendormanagement.repository;

import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.EmailValidationEntity;
@Repository
public interface EmailRepo {
	boolean save(EmailValidationEntity emailValidationEntity);
	String findLatestOtpByEmail(String email);
	boolean deleteEmailVerificationDataByEmail(String email);


}
