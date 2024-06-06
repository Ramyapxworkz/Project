package com.xworkz.vendormanagement.dto;

import java.time.LocalDateTime;

import javax.persistence.Id;

import lombok.Data;

@Data
public class EmailValidationDto {
@Id
	private int id;
	private String email;
	private String otp;
	private LocalDateTime createAt;
	
}
