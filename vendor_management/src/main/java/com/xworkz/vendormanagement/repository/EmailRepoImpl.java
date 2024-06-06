package com.xworkz.vendormanagement.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.EmailValidationEntity;

@Repository
public class EmailRepoImpl implements EmailRepo {
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean save(EmailValidationEntity emailValidationEntity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(emailValidationEntity);
			et.commit();
			return true;
		} catch (PersistenceException exception) {
			if (et.isActive())
				et.rollback();

		} finally {
			em.close();
		}
		return false;
	}

	@Override
	public String findLatestOtpByEmail(String email) {
		System.err.println("findLatestOtpByEmail=======================");
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("findLatestOtpByEmail");
			query.setParameter("email", email);
			query.setMaxResults(1);
			return (String) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteEmailVerificationDataByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createNamedQuery("deleteEmailVerificationDataByEmail");
			query.setParameter("email", email);

			int deletedRows = query.executeUpdate();

			if (deletedRows > 0) {
				et.commit();
				return true;
			} else {
				return false;
			}

		} catch (PersistenceException exception) {
			if (et.isActive())
				et.rollback();

		} finally {
			em.close();
		}

		return false;
	}

}
