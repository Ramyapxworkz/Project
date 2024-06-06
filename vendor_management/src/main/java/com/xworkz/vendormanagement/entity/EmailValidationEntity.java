package com.xworkz.vendormanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="emailvalidate")
@NamedQuery(name = "findLatestOtpByEmail", query = "SELECT e.otp FROM EmailValidationEntity e WHERE e.email = :email ORDER BY e.createAt DESC")
@NamedQuery(name = "deleteEmailVerificationDataByEmail", query = "DELETE FROM EmailValidationEntity entity WHERE entity.email = :email")
public class EmailValidationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String otp;
	private LocalDateTime createAt;
	
	

}
