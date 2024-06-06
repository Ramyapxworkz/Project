package com.xworkz.vendormanagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.dto.VendorDto;
import com.xworkz.vendormanagement.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService   productService;
	
	@PostMapping("/productpage")
	public String productPage(ProductDto productDto,Model model) {
		System.out.println("Add product");
		boolean save=productService.validateSaveAddProduct(productDto);
		if(save) {
			return "profile";
		}
		return "profile";
	}
	
	@GetMapping("/getproductDetails")
	@ResponseBody
	public List<ProductDto> getProductDetailsByEmail(@RequestParam String email) {
		List<ProductDto> ProductDetails = productService.getAllProductDetailsByVendorId(email);
		System.err.println(ProductDetails);
		return ProductDetails;
	}
	
	/*
	 * @PostMapping("/getproductDetails")
	 * 
	 * @ResponseBody public ResponseEntity<List<ProductDto>>
	 * readProduct(@RequestParam String email,Model model) {
	 * System.err.println("email============================"+email);
	 * List<ProductDto>
	 * productDetails=productService.getAllProductDetailsByVendorId(email);
	 * //model.addAttribute("products", productDetails); return
	 * ResponseEntity.ok(productDetails);
	 * 
	 * }
	 */
	
	/*
	 * @GetMapping("/editProductDetails") public String
	 * updateProductDetails(@RequestParam int id,Model model) { // int
	 * productId=Integer.parseInt(id);
	 * System.out.println("update:-----------------"+id); ProductDto
	 * productDto=productService.getProductDetailsById(id);
	 * System.out.println("product==========="+productDto);
	 * model.addAttribute("productdto", productDto); return "productupdate"; }
	 */
	
	@GetMapping("/editProductDetails")
	@ResponseBody
	public ProductDto updateProductDetails(@RequestParam int id) {
	    ProductDto productDto = productService.getProductDetailsById(id);
	    return productDto;
	}
	
	@PostMapping("/editproductdetailspage")
	public String update(ProductDto productDto, Model model, int id, HttpSession session) {
		System.out.println("" + id);
		System.out.println("dto============================" + productDto.getId());
		session.setAttribute("id", id);
		System.out.println(id);
		
		boolean update = productService.validateUpdateProductDetails(productDto, productDto.getId()); 
		if (update) { 
			ProductDto read = productService.getProductDetailsById(id);
			model.addAttribute("products", read);
			model.addAttribute("message", "Data updated successfully");
			return "profile";
		} else {
			ProductDto read = productService.getProductDetailsById(id); 
			model.addAttribute("products", read);
			model.addAttribute("message", "Data not updated successfully");
			return "productupdate";
		}
	}
	
	
	
	
}
