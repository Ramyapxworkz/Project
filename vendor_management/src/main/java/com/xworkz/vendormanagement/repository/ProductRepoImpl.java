package com.xworkz.vendormanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.ProductEntity;
import com.xworkz.vendormanagement.entity.VendorEntity;
@Repository
public class ProductRepoImpl implements ProductRepo{
@Autowired
private EntityManagerFactory emf;
	@Override
	public boolean saveAddProductDetails(ProductEntity entity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(entity);
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
	public List<ProductEntity> readAllProductDetails() {
		System.out.println("this is read all method");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		List<ProductEntity> read = null;
		try {
			et.begin();
			Query query = em.createNamedQuery("readAll");
			read = query.getResultList();
			et.commit();
			return read;

		} catch (PersistenceException e) {
			if (et.isActive()) {
				et.rollback();
			}
		} finally {
			em.close();
		}
		return null;
	}
	@Override
	public List<ProductEntity> getAllProductDetailsByVendorId(int vendorId) {
		EntityManager entityManager=emf.createEntityManager();
		try {
			Query query=entityManager.createNamedQuery("getAllProductDetailesByVendotId");
			query.setParameter("vendorId", vendorId);
			List<ProductEntity> read=query.getResultList();
			return read;
		} catch (Exception e) {
		}finally {
			
		}
		return null;
	}

	
	@Override
	public ProductEntity getProductDetailsById(int id) {
	    ProductEntity entity = null;
	    EntityManager entityManager = emf.createEntityManager();
	    try {
	        Query query = entityManager.createNamedQuery("getProductDetalsById");
	        query.setParameter("id", id);
	        entity = (ProductEntity) query.getSingleResult();
	    } catch (NoResultException e) {
	        // Handle case where no entity is found for the given id
	        System.out.println("No entity found for this id: " + id);
	    } finally {
	        if (entityManager != null && entityManager.isOpen()) {
	            entityManager.close();
	        }
	    }
	    return entity;
	}
	
	@Override
		public boolean updateProductDetailsById(ProductEntity entity, int id) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
			if (productEntity != null) {
				
				productEntity.setId(entity.getId());
				productEntity.setCategory(entity.getCategory());
				productEntity.setProductName(entity.getProductName());
				productEntity.setProductPrice(entity.getProductPrice());
				productEntity.setDeliveryCharge(entity.getDeliveryCharge());
				productEntity.setDescription(entity.getDescription());
				productEntity.setAvailable(entity.getAvailable());
				entityManager.merge(productEntity);
				entityTransaction.commit();
				System.out.println("Data updated successfully");
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
	

}
