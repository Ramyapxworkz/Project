package com.xworkz.vendormanagement.dto;

import javax.persistence.Id;

import com.xworkz.vendormanagement.entity.VendorEntity;

import lombok.Data;

@Data
public class ProductDto {
	@Id
	private int id;
	//private int vendorId;
	private String category;
	private String productName;
	private double productPrice;
	private double deliveryCharge;
	private String description;
	private String available;
	private String email;
	private VendorEntity vendor;

	

}
