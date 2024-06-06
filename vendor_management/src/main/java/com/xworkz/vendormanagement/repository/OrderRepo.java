package com.xworkz.vendormanagement.repository;

import java.util.List;

import com.xworkz.vendormanagement.entity.OrderEntity;

public interface OrderRepo {
	boolean saveOrderDetails(OrderEntity orderEntity);
	List<OrderEntity> getorderdeatilsByvendorId(int vendorId);
	boolean updateOrderStatusById(String orderStatus,int id);
	List<OrderEntity> getOrderHistroy();

}
