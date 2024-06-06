package com.xworkz.vendormanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.OrderEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;
@Repository
public interface VendorRepo {
   boolean saveVendorDetails(VendorEntity vendorEntity);
   boolean findByEmail(String email);
   
   boolean saveLoginOtpByemaild(String email,String otp,LocalDateTime generateOtpTime);
   
   Object[] getloginOTPAndgenratedTime(String email);
   
	String imagePathByEmail(String email);
	
	VendorEntity readByEmail(String email);
	
	int getVendorIdByEmail(String email);
	
	boolean updateById( VendorEntity entity,int id);
	List<VendorEntity> findAll();
	
	
	///ajax back end validation/////
	public VendorEntity isExistGstNoContactNoEmailWebsite(String gstNumber, Long contactNumber, String email, String website);

	
	boolean updateStatusById(String Status,int id);
	/*
	 * String checkEmailforLogin();
	 */	
	
	
	
}
