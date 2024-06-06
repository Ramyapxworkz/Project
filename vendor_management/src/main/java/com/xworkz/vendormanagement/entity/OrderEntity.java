package com.xworkz.vendormanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

@Entity
@Data
@Table(name = "order_details")
@NamedQuery(name="getOrderDetailsByvendorID",query="SELECT entity from OrderEntity entity where entity.vendor.id=:vendorId")
@NamedQuery(name="getAllOrderHistory",query="SELECT entity FROM OrderEntity entity")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id")
	private VendorEntity vendor;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	private String productName;
	private double productPrice;
	private double deliveryCharge;
	@Column(name="discriptionAboutProduct")
	private String descriptionAboutProduct;
	private int orderQuantity;
	private LocalDateTime orderDate;
	private String orderStatus;
	@Column(name="deliveryDate")
	private String delivaryDate;
	private String deliveryAddress;
	private String message;
	private double orderAmount;
	private String paymentStatus;

}
