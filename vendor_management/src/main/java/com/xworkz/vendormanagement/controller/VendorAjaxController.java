package com.xworkz.vendormanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vendormanagement.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@EnableWebMvc
public class VendorAjaxController {
	@Autowired
	private VendorService service;

	public VendorAjaxController() {
		log.info("Invoking VendorAjaxController");
	}
	
	@GetMapping(value="/gstNoAjax/{gstNumber}")
	public String gstNoAjax(@PathVariable String gstNumber) {
		String byGstNo=this.service.isExistByGstNoAjax(gstNumber);
		log.info("Running ajax controller for gstNo:" +gstNumber);
		return byGstNo;
	}
	
	@GetMapping(value = "/contactNoAjax/{contactNumber}")
	public String contactNoAjax(@PathVariable Long contactNumber) {
		String byContactNo = this.service.isExistByContactNoAjax(contactNumber);
		log.info("Running ajax controller for Contact number : " + contactNumber);
		return byContactNo;
	}
	
	@GetMapping(value="/alternativeNoAjax/{alternativeNumber}")
	public String alternativeNoAjax(@PathVariable Long alternativeNo) {
		String byAlternativeNo=this.service.isExistByAlternativeNoAjax(alternativeNo);
		log.info("Running ajax controller for alternativeNo:" +alternativeNo);
		return byAlternativeNo;
	}
	@GetMapping(value = "/emailAjax/{email:.+}")
	public String emailAjax(@PathVariable String email) {
		String byEmail = this.service.isExistByEmailAjax(email);
		log.info("Running ajax controller for email: " + email);
		return byEmail;
	}
	
	@GetMapping(value = "/websiteAjax/{website:.+}")
	public String websiteAjax(@PathVariable String website) {
		String byWebsite = this.service.isExistByWebsiteAjax(website);
		log.info("Running ajax controller for website: " + website);
		return byWebsite;
	}
}