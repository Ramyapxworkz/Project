package com.xworkz.vendormanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendormanagement.dto.OrderDto;
import com.xworkz.vendormanagement.dto.VendorDto;
import com.xworkz.vendormanagement.entity.OrderEntity;
import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;
import com.xworkz.vendormanagement.repository.OrderRepo;
import com.xworkz.vendormanagement.repository.ProductRepo;
import com.xworkz.vendormanagement.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
@Autowired
private ProductRepo productRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private VendorRepo vendorRepo;
	@Override
	public boolean validateOrderDetails(OrderDto orderDto) {
	 //   System.out.println("orderDto service method=========" + orderDto);
	    
	    ProductEntity productEntity = productRepo.getProductDetailsById(orderDto.getProductId());
	    System.out.println("productEntity=======" + productEntity);
	    
	    if (productEntity != null) {
	        OrderEntity orderEntity = new OrderEntity();
	       	        
	        // Set properties in OrderDto as well if needed
	        orderDto.setProductName(productEntity.getProductName());
	        orderDto.setProductPrice(productEntity.getProductPrice());
	        orderDto.setDeliveryCharge(productEntity.getDeliveryCharge());
	        orderDto.setDescriptionAboutProduct(productEntity.getDescription());
	        orderDto.setOrderDate(LocalDateTime.now());
	        orderDto.setVendor(productEntity.getVendor());
	        orderDto.setProduct(productEntity);
	        
	        BeanUtils.copyProperties(orderDto, orderEntity);
	        
	        boolean save = orderRepo.saveOrderDetails(orderEntity);
	        if (save) {
	            log.info("Data saved Successfully");
	            return true;
	        }
	    } else {
	        log.error("Product details not found for productId: " + orderDto.getProductId());
	    }
	    
	    return false;
	}
	
	@Override
		public List<OrderDto> getOrderDetailsByVendorEmail(String email) {
		int vendorid=vendorRepo.getVendorIdByEmail(email);
	 List<OrderEntity> orderDetails=orderRepo.getorderdeatilsByvendorId(vendorid);
	 List<OrderDto> readAll=new ArrayList<OrderDto>();
		for (OrderEntity orderEntity : orderDetails) {
			OrderDto orderDto=new OrderDto();
			if(orderEntity.getOrderStatus().equalsIgnoreCase("order")) {
			BeanUtils.copyProperties(orderEntity, orderDto);
			readAll.add(orderDto);
			}
			
		}
		//System.out.println("service======="+readAll);
			return readAll;
		}
	
	@Override
		public boolean getOrderStatusById(String orderStatus, int id) {
		boolean update = orderRepo.updateOrderStatusById(orderStatus, id);
		System.out.println("update=======================");
		if (update) {
			
			System.out.println("updated Successfully==============================");
			return true;
		}
			return false;
		}
	
	
	@Override
		public List<OrderDto> getOrderHestoryByVendorEmail(String email) {
		int vendorId=vendorRepo.getVendorIdByEmail(email);
		 List<OrderEntity> orderDetails=orderRepo.getorderdeatilsByvendorId(vendorId);
		 List<OrderDto> readAll=new ArrayList<OrderDto>();
			for (OrderEntity orderEntity : orderDetails) {
				OrderDto orderDto=new OrderDto();
				BeanUtils.copyProperties(orderEntity, orderDto);
				readAll.add(orderDto);			
			}
				return readAll;		
		}

	@Override
	public List<OrderDto> getOrderHistory() {
		List<OrderEntity> orderDetails = orderRepo.getOrderHistroy();
		List<OrderDto> readAll = new ArrayList<OrderDto>();
		for (OrderEntity orderEntity : orderDetails) {
			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(orderEntity, orderDto);
			readAll.add(orderDto);
			
		}
		return readAll;
	}

}
