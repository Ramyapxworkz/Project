package com.xworkz.vendormanagement.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.OrderEntity;
import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class OrderRepoImpl implements OrderRepo{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public boolean saveOrderDetails(OrderEntity orderEntity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(orderEntity);
			et.commit();
			return true;
		} catch (PersistenceException exception) {
			if (et.isActive()) {
				et.rollback();
			}
		} finally {
			em.close();
		}
		return false;
	}
	
	@Override
	public List<OrderEntity> getorderdeatilsByvendorId(int vendorId) {
		EntityManager entityManager=emf.createEntityManager();
		try {
			Query query=entityManager.createNamedQuery("getOrderDetailsByvendorID");
			query.setParameter("vendorId", vendorId);
			List<OrderEntity> read=query.getResultList();
			return read;
		} catch (Exception e) {
		}finally {
			
		}

		return null;
	}

	@Override
	public boolean updateOrderStatusById(String orderStatus, int id) {
		System.out.println("this is status method repo");
		 EntityManager entityManager = emf.createEntityManager();
		    EntityTransaction entityTransaction = entityManager.getTransaction();
		    try {
		        entityTransaction.begin();
		        OrderEntity entity = entityManager.find(OrderEntity.class, id);
		        if (entity != null) {
		            
		            entity.setOrderStatus(orderStatus);
		            entityManager.merge(entity); 
		            entityTransaction.commit(); 
		            return true;
		        } else {
		            
		            return false;
		        }
		    } catch (PersistenceException e) {
		        e.printStackTrace();
		        if (entityTransaction.isActive()) {
		            entityTransaction.rollback();
		        }
		        return false;
		    } finally {
		        entityManager.close();
		    }		
	}
	
	@Override
	public List<OrderEntity> getOrderHistroy() {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		List<OrderEntity> list = new ArrayList<OrderEntity>();
		try {
			Query query = em.createNamedQuery("getAllOrderHistory");
			list = query.getResultList();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());

		} finally {
			log.info("Closing resources");
			em.close();
			log.info("Em closed");
		}
		return list;
	}
}
