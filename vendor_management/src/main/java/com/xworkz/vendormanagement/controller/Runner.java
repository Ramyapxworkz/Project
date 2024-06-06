package com.xworkz.vendormanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.service.ProductService;
import com.xworkz.vendormanagement.service.ProductServiceImpl;

public class Runner {
	public static void main(String[] args) {
		System.out.println("This is Main method");
		ProductService service=new ProductServiceImpl();
		List<ProductDto> dto=service.validateReadAll();
		System.out.println("This is read all method"+dto);
	}

}
