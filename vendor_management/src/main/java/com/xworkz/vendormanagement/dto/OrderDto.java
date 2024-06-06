package com.xworkz.vendormanagement.dto;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;

import lombok.Data;

@Data
public class OrderDto {
@Id
private int id;
private int vendorId;
private int productId;
private String productName;
private double productPrice;
private double deliveryCharge;
private String descriptionAboutProduct;
private int orderQuantity;
private LocalDateTime orderDate;
private String delivaryDate;
private String deliveryAddress;
private String message;
private String available;
private String orderStatus;
private ProductEntity product;
private VendorEntity vendor;
private double orderAmount;
private double amountPaid;
private String paymentStatus;
private double totalAmountPaid;
private double balanceAmount;
}
