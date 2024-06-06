package com.xworkz.vendormanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.entity.AdminEntity;
import com.xworkz.vendormanagement.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {
 
	@Autowired
	private AdminRepo adminRepo;
	@Override
	public boolean validateAdminLogin(String email, String password) {
		AdminEntity adminEntity=adminRepo.adminLogin(email);
		if(adminEntity!=null) {
			if (adminEntity.getEmail().equals(email) && adminEntity.getPassword().equals(password)) {
			return true;
			}
			
		}
		return false;
	}

}
