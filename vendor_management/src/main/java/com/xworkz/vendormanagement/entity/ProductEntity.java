package com.xworkz.vendormanagement.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_details")
@NamedQuery(name="readAll",query="Select entity from ProductEntity entity ")
@NamedQuery(name="getAllProductDetailesByVendotId",query="Select entity from ProductEntity entity WHERE entity.vendor.id=:vendorId")
@NamedQuery(name="getProductDetalsById",query="SELECT entity from ProductEntity entity WHERE entity.id=:id")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	private String productName;
	private double productPrice;
	private double deliveryCharge;
	private String description;
	private String available;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="vendor_id")
	private VendorEntity vendor;
	
}
