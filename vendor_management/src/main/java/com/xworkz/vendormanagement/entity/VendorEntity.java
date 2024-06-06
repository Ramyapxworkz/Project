package com.xworkz.vendormanagement.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "vendor_management_table")
@NamedQuery(name = "countByEmail", query = "SELECT COUNT(entity) FROM VendorEntity entity WHERE entity.email = :email")
@NamedQuery(name="saveLoginOtpByemaild", query="UPDATE VendorEntity entity set entity.otp=:otp,entity.generateOtpTime=:generateOtpTime where entity.email=:email")
@NamedQuery(name="findAll",query="Select entity from VendorEntity entity")
@NamedQuery(name = "getloginOTPAndgenratedTime", query = "SELECT entity.otp ,entity.generateOtpTime FROM VendorEntity entity  WHERE entity.email = :email ORDER BY entity.generateOtpTime DESC")
@NamedQuery(name = "findImagePathByEmail", query = "SELECT entity.imagePath FROM VendorEntity entity WHERE entity.email = :email")
@NamedQuery(name="readByEmail",query="select entity from VendorEntity entity where entity.email=:email")
@NamedQuery(name = "isExistGstNoContactNoEmailWebsite", query = "Select ent from VendorEntity as ent where ent.gstNumber=:gn   or ent.contactNumber=:cn or ent.email=:vm or ent.website=:web")
@NamedQuery(name="getVendorIdByEmail",query="SELECT entity.id from VendorEntity entity where entity.email=:email")
public class VendorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 private String vendorName;
	 private String adress;
	 private String ownerName;
	 private Long contactNumber;
	 private Long alternativeNumber;
	 private String email;
	 private String gstNumber;
	 @Column(name="companyStartDate")
	 private String startDate;
	 private int pincode;
	 private String website;
	 private String otp;
	 private LocalDateTime generateOtpTime;
	 private String imagePath;
	 private String status;

}
