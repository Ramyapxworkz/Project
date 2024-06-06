package com.xworkz.vendormanagement.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data

public class VendorDto {
	 private int id;
	 private String vendorName;
	 private String adress;
	 private String ownerName;
	 private Long contactNumber;
	 private Long alternativeNumber;
	 private String email;
	 private String gstNumber;
	 private String startDate;
	 private int pincode;
	 private String website;
	 private String otp;
	 private String imagePath;
	 private MultipartFile imageFile;
	 private String status;

}
