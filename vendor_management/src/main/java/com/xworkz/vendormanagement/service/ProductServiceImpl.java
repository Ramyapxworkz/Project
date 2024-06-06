package com.xworkz.vendormanagement.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.dto.ProductDto;
import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;
import com.xworkz.vendormanagement.repository.ProductRepo;
import com.xworkz.vendormanagement.repository.VendorRepo;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private VendorRepo vendorRepo;

	@Override
	public boolean validateSaveAddProduct(ProductDto dto) {
		ProductEntity productEntity = new ProductEntity();
		VendorEntity vendorEntity = vendorRepo.readByEmail(dto.getEmail());
		/*
		 * productEntity.setCategory(dto.getCategory());
		 * productEntity.setProductName(dto.getProductName());
		 * productEntity.setProductPrice(dto.getProductPrice());
		 * productEntity.setDeliveryCharge(dto.getDeliveryCharge());
		 * productEntity.setDescription(dto.getDescription());
		 * productEntity.setAvailable(dto.getAvailable());
		 */
		dto.setVendor(vendorEntity);
		BeanUtils.copyProperties(dto, productEntity);
		boolean save = productRepo.saveAddProductDetails(productEntity);
		if (save) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProductDto> validateReadAll() {
		List<ProductEntity> entity = productRepo.readAllProductDetails();
		if (entity == null) {
			System.out.println("entity is null");
		}
		List<ProductDto> readAll = new ArrayList<ProductDto>();

		if (entity != null) {
			for (ProductEntity addProductEntity : entity) {
				ProductDto dto = new ProductDto();
				dto.setId(addProductEntity.getId());
				dto.setVendor(addProductEntity.getVendor());
				dto.setCategory(addProductEntity.getCategory());
				dto.setProductName(addProductEntity.getProductName());
				dto.setProductPrice(addProductEntity.getProductPrice());
				dto.setDescription(addProductEntity.getDescription());
				dto.setDeliveryCharge(addProductEntity.getDeliveryCharge());
				dto.setAvailable(addProductEntity.getAvailable());
				/*
				 * dto.setVendor(addProductEntity.getVendor());
				 */				
				readAll.add(dto);
			}
		}

		return readAll;
	}

	@Override
	public List<ProductDto> getAllProductDetailsByVendorId(String email) {

		int vendorId = vendorRepo.getVendorIdByEmail(email);

		List<ProductEntity> readAll = productRepo.getAllProductDetailsByVendorId(vendorId);
		List<ProductDto> readAllProducts = new ArrayList<ProductDto>();
		for (ProductEntity productEntity : readAll) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(productEntity, productDto);
			readAllProducts.add(productDto);

		}

		return readAllProducts;
	}

	@Override
	public ProductDto getProductDetailsById(int id) {
		ProductEntity productEntity = productRepo.getProductDetailsById(id);
		System.err.println("productEntity======================="+productEntity);
		ProductDto productDto = new ProductDto();
		if (productEntity != null) {
			productDto.setId(productEntity.getId());
			productDto.setProductName(productEntity.getProductName());
			productDto.setProductPrice(productEntity.getProductPrice());
			productDto.setCategory(productEntity.getCategory());
			productDto.setDeliveryCharge(productEntity.getDeliveryCharge());
			productDto.setDescription(productEntity.getDescription());
			productDto.setAvailable(productEntity.getAvailable());
			return productDto;
		}
		return null;
	}

	@Override
	public boolean validateUpdateProductDetails(ProductDto productDto, int id) {
	    if (productDto == null) {
	        throw new IllegalArgumentException("ProductDto cannot be null");
	    }

	    ProductEntity productEntity = productRepo.getProductDetailsById(id);
	    if (productEntity != null) {
	      
	        productEntity.setCategory(productDto.getCategory());
	        productEntity.setProductName(productDto.getProductName());
	        productEntity.setProductPrice(productDto.getProductPrice());
	        productEntity.setDeliveryCharge(productDto.getDeliveryCharge());
	        productEntity.setDescription(productDto.getDescription());
	        productEntity.setAvailable(productDto.getAvailable());

	        boolean update = productRepo.updateProductDetailsById(productEntity, productEntity.getId());
	        if (update) {
	            System.out.println("Updated successfully");
	            return true;
	            // Optionally return the updated DTO
	        } else {
	            throw new RuntimeException("Update failed");
	        }
	    } else {
	        throw new EntityNotFoundException("ProductEntity not found for ID: " + id);
	    }
	}

}
