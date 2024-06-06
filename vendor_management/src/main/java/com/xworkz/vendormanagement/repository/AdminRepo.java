package com.xworkz.vendormanagement.repository;

import com.xworkz.vendormanagement.entity.AdminEntity;

public interface AdminRepo {
	AdminEntity adminLogin(String email);
	

}
