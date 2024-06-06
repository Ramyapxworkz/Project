package com.xworkz.vendormanagement.service;

import java.util.List;

import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.entity.ProductEntity;

public interface ProductService {
boolean validateSaveAddProduct(ProductDto dto);
List<ProductDto> validateReadAll();


List<ProductDto> getAllProductDetailsByVendorId(String email);

ProductDto getProductDetailsById(int id);

boolean validateUpdateProductDetails(ProductDto productDto,int id);
}
