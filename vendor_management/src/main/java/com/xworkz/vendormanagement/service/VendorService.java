package com.xworkz.vendormanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.VendorDto;

@Service
public interface VendorService {
	boolean validateAndSaveVendorDetails(VendorDto vendorDto);

	boolean findByEmail(String email);

	List<VendorDto> readAllVendorDetails();

	///// Login page////
	boolean saveOtpByLoginEmailID(String email);

	String validateLoginOTP(String email, String otp);

	String findImagePathByEmail(String email);

	//// update///
	VendorDto readByEmail(String email);

	VendorDto validateAndUpdate(VendorDto dto, String email);

////ajax validation/////
	String isExistByGstNoAjax(String gstNumber);

	String isExistByContactNoAjax(Long contactNumber);

	String isExistByAlternativeNoAjax(Long alternativeNumber);

	String isExistByEmailAjax(String email);

	String isExistByWebsiteAjax(String website);

	boolean updateStatusById(String status, int id);

	String checkLoginEmail(String email);

}
