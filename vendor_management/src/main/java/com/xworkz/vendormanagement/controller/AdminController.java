package com.xworkz.vendormanagement.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.dto.VendorDto;
import com.xworkz.vendormanagement.service.AdminService;
import com.xworkz.vendormanagement.service.OrderService;
import com.xworkz.vendormanagement.service.ProductService;
import com.xworkz.vendormanagement.service.VendorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private VendorService vendorService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/adminlogin")
	public String adminloginmethod() {
		return "adminlogin";
	}

	@PostMapping("/loginadmin")
	public String loginAdmin(@RequestParam String email, String password, HttpSession session, Model model) {
		boolean login = adminService.validateAdminLogin(email, password);
		if (login) {
			return "adminloginpage";
		}
		return "adminloginpage";
	}

	@GetMapping("/viewproduct")
	public ResponseEntity<List<ProductDto>> viewAllAddProduct() {
		List<ProductDto> products = productService.validateReadAll();
		System.out.println("products===" + products);
		if (products != null && !products.isEmpty()) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/viewVendorDetails")
	@ResponseBody
	public ResponseEntity<List<VendorDto>> viewVendorDetails() {
		List<VendorDto> readAllVendorDetails = vendorService.readAllVendorDetails();
		System.out.println("products===" + readAllVendorDetails);
		if (readAllVendorDetails != null && !readAllVendorDetails.isEmpty()) {
			return ResponseEntity.ok(readAllVendorDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/display")
	public void displayUserImageByEmail(HttpServletResponse response, @RequestParam String imagePath)
			throws IOException {
		/* String imagePath = service.findImagePathByEmail(email); */
		System.err.println("imagePath========================" + imagePath);
		File file = new File("D:\\vendorImg\\" + imagePath);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();

	}

	/*----------------------------------APPROVE VENDOR LOGIN PERMISSION METHOD ------------------------------------ */
	@PostMapping("/updateVendorStatus")
	@ResponseBody
	public ResponseEntity<Object> updateVendorStatus(@RequestParam String id, String status, String vendorEmail) {
		int vendorID = Integer.parseInt(id);
		System.out.println("id=========" + vendorID);
		System.out.println("status=========" + status);
		System.out.println("vendorEmail=========" + vendorEmail);
		boolean update = vendorService.updateStatusById(status, vendorID);
		// return ResponseEntity.ok().body("updateVendorStatusSuccessfully");
		if (update) {
			return ResponseEntity.ok().body("updateVendorStatusSuccessfully");
		} else {
			return ResponseEntity.ok().body("!updateVendorStatusSuccessfully");
		}

	}

	@PostMapping("/saveorderDetails")
	public String orderDetailsSave(@ModelAttribute("orderDto") OrderDto dto, Model model) {
		System.out.println("ordersave==============================" + dto);
		boolean save = orderService.validateOrderDetails(dto);
		if (save) {
			return "adminloginpage";
		}
		return "adminloginpage";
	}
	
	
	
	
	@GetMapping("/orderHistory")
	@ResponseBody
	public ResponseEntity<List<OrderDto>> readOrder() {
		List<OrderDto> orderDetails=orderService.getOrderHistory();
		System.out.println("orderDetails this is controller----"+orderDetails);
		if(orderDetails!=null) {
		return ResponseEntity.ok(orderDetails);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@PostMapping("/updateOrderStatusinAdmin")
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
