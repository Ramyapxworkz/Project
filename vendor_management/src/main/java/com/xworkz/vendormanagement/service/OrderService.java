package com.xworkz.vendormanagement.service;

import java.util.List;

import com.xworkz.vendormanagement.dto.OrderDto;

public interface OrderService {
	boolean validateOrderDetails(OrderDto orderDto);
	List<OrderDto> getOrderDetailsByVendorEmail(String email);
	boolean getOrderStatusById(String orderStatus,int id);
	List<OrderDto> getOrderHestoryByVendorEmail(String email);
	List<OrderDto> getOrderHistory();
	

}
