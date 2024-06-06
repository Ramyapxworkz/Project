package com.xworkz.vendormanagement.entity;

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
@Table(name="admin_table")
@NamedQuery(name="findByEamil",query="SELECT entity FROM AdminEntity entity WHERE entity.email=:email")
public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="admin_name")
	private String adminName;
	@Column(name="admin_email")
	private String email;
	@Column(name="admin_password")
	private String password;

}
