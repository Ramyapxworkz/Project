package com.xworkz.vendormanagement.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class AdminDto {
	@Id
	private int id;
	private String adminName;
	private String email;
	private String password;
	
	
	

}
