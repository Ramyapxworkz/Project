package com.xworkz.vendormanagement.repository;

import java.util.List;

import com.xworkz.vendormanagement.entity.ProductEntity;


public interface ProductRepo {
   boolean  saveAddProductDetails(ProductEntity entity);
   List<ProductEntity> readAllProductDetails();
	

   List<ProductEntity> getAllProductDetailsByVendorId(int vendorId);
   
   
   ProductEntity getProductDetailsById(int id);
   
   boolean updateProductDetailsById(ProductEntity entity,int id);
}
