package com.xworkz.vendormanagement.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.dto.VendorDto;
import com.xworkz.vendormanagement.entity.VendorEntity;
import com.xworkz.vendormanagement.service.ProductService;
import com.xworkz.vendormanagement.service.EmailService;
import com.xworkz.vendormanagement.service.OrderService;
import com.xworkz.vendormanagement.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class VendorController {
	@Autowired
	private VendorService service;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ProductService addProductService;
	@Autowired
	private OrderService orderService;

	public VendorController() {
		log.info("This is Vendor controller");
	}

	@GetMapping("/index")
	public String indexMethod() {

		return "index";

	}


	@GetMapping("/display")
	public void displayUserImageByEmail(HttpServletResponse response, @RequestParam String email) throws IOException {
		String imagePath = service.findImagePathByEmail(email);
		System.err.println("imagePath========================" + imagePath);
		File file = new File("D:\\vendorImg\\" + imagePath);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();

	}

	@GetMapping("/registerPage")
	public String registerpage() {
		return "register";
	}

	@GetMapping("/register")
	public String savevendorDetails(@ModelAttribute VendorDto dto, Model model) {
		boolean save = service.validateAndSaveVendorDetails(dto);
		if (save) {
			log.info("User successfully signed up.");
			return "signIn";
		} else {
			log.error("Failed to save user data.");
			model.addAttribute("errorMessage", "Failed to save user data. Please try again.");
			return "redirect:/registerPage";
		}

	}

	@GetMapping(value = "/saveLoginOtp/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String email, Model model) {
		log.info("emailId=================" + email);
		boolean saveOTP = service.saveOtpByLoginEmailID(email);
		if (saveOTP) {
			model.addAttribute("message", "OTP sent To your register mail id");
			log.info("saveOTP===" + saveOTP);
			return "saveOTP";

		} else {
			return "OTP NOT SAVE";
		}
	}

	@GetMapping("profilepage")
	public String profilePage() {
		return "profile";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, String otp, Model model, HttpSession session) {

		System.out.println("this is login method");
		String login = service.validateLoginOTP(email, otp);
		if (login.equalsIgnoreCase("OTP validated")) {
			session.setAttribute("email", email);
			
			/*
			 * List<ProductDto> read = addProductService.validateReadAll(); if (read !=
			 * null) { session.setAttribute("products", read); } else {
			 * session.setAttribute("products", new ArrayList<ProductDto>()); }
			 */
			    
			return "redirect:/profilepage";
		} else if (login.equalsIgnoreCase("OTP expired")) {
			model.addAttribute("message1", "OTP expired");
			return "redirect:/signIn";
		}
		model.addAttribute("message1", "OTP invalid");
		return "signIn";
	}

	@GetMapping("/editprofilepage")
	public String editProfilePage(@RequestParam String email, Model model) {
		System.out.println("email=========================" + email);
		VendorDto dto = service.readByEmail(email);
		model.addAttribute("read", dto);
		return "editprofile";
	}

	@GetMapping("/successpage")
	public String successpage() {
		return "success";
	}

	@PostMapping("/editprofile")
	public String update(@ModelAttribute VendorDto dto, Model model, String email, HttpSession session) {
		System.out.println("" + email);
		System.err.println("dto=========================="+dto);
		System.out.println("dto============================" + dto.getEmail());
		session.setAttribute("email", email);
		System.out.println(email);
		if (email == null) {
			return "redirect:/login"; 
		}
		VendorDto update = service.validateAndUpdate(dto, dto.getEmail()); 
		if (update != null && update.getEmail() != null) { 
			VendorDto read = service.readByEmail(update.getEmail());
			model.addAttribute("readSave", read);
			model.addAttribute("message", "Data updated successfully");
			return "profile";
		} else {
			VendorDto read = service.readByEmail(email); 
			model.addAttribute("readSave", read);
			model.addAttribute("message", "Data not updated successfully");
			return "successpage";
		}
	}

	/*	order details
	*/	
	@GetMapping("/getOrderDetails")
	@ResponseBody
	public ResponseEntity<List<OrderDto>> readProduct(@RequestParam String email,Model model) {
		System.err.println("email============================"+email);
		List<OrderDto> orderDetails=orderService.getOrderDetailsByVendorEmail(email);
		System.out.println("orderDetails this is controller----"+orderDetails);
		if(orderDetails!=null) {
		return ResponseEntity.ok(orderDetails);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	
	
	
	@GetMapping("/getOrderHistory")
	@ResponseBody
	public ResponseEntity<List<OrderDto>> getOrderHistory(@RequestParam String email,Model model) {
		System.err.println("email============================"+email);
		List<OrderDto> orderDetails=orderService.getOrderHestoryByVendorEmail(email);
		System.out.println("orderDetails this is controller----"+orderDetails);
		if(orderDetails!=null) {
		return ResponseEntity.ok(orderDetails);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	
	
	@PostMapping("/updateOrderStatus")
	@ResponseBody
	public ResponseEntity<String> updateOrderStatus(@RequestParam int id, String status) {
	    System.out.println("Order ID: " + id);
	    System.out.println("New Status: " + status);

	    boolean update = orderService.getOrderStatusById(status, id);
	    if (update) {
	        System.out.println("Update Successful");
	        // Return a success response with HTTP status code 200 (OK)
	        return ResponseEntity.ok("Order status updated successfully");
	    } else {
	        // If update failed, return an error response with HTTP status code 500 (Internal Server Error)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update order status");
	    }
	}

	


	

	
	
	
}
