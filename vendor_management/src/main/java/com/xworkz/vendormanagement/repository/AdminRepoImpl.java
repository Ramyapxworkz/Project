package com.xworkz.vendormanagement.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.AdminEntity;

@Repository
public class AdminRepoImpl implements AdminRepo{
@Autowired
private EntityManagerFactory emf;
	
	@Override
	public AdminEntity adminLogin(String email) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		AdminEntity entity = null;
		try {
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("findByEamil");
			query.setParameter("email", email);
			entity = (AdminEntity) query.getSingleResult();
			entityTransaction.commit();
			return entity;
		} catch (PersistenceException e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		} finally {
			entityManager.close();
//			entityManagerFactory.close();
		}
		return null;
	}
}
