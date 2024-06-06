package com.xworkz.vendormanagement.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.vendormanagement.entity.VendorEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class VendorRepoImpl implements VendorRepo {
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean saveVendorDetails(VendorEntity vendorEntity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(vendorEntity);
			et.commit();
		} catch (PersistenceException exception) {
			if (et.isActive()) {
				et.rollback();
			}
		} finally {
			em.close();
		}
		return true;
	}

	@Override
	public boolean findByEmail(String email) {
		EntityManager entityManager = emf.createEntityManager();
		System.err.println("findByEmail repo");
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("countByEmail");
			query.setParameter("email", email);
			Long count = (Long) query.getSingleResult();
			entityManager.getTransaction().commit();
			return count > 0;
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return false;
	}

	@Override
	public boolean saveLoginOtpByemaild(String email, String otp, LocalDateTime generateOtpTime) {

		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		System.out.println("connection is ok");
		try {
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("saveLoginOtpByemaild");
			query.setParameter("otp", otp);
			query.setParameter("generateOtpTime", generateOtpTime);
			query.setParameter("email", email);

			int rowsUpdated = query.executeUpdate();
			entityTransaction.commit();
			if (rowsUpdated == 0) {
				System.out.println("OTP not updated successfully");
				return false;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
				return false;
			}
		} finally {
			entityManager.close();
		}
		return true;
	}

	@Override
	public Object[] getloginOTPAndgenratedTime(String email) {
		System.err.println("findLatestOtpByEmail=======================");
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("getloginOTPAndgenratedTime");
			query.setParameter("email", email);
			return (Object[]) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public String imagePathByEmail(String email) {
			EntityManager entityManager = emf.createEntityManager();
			try {
				TypedQuery<String> query = entityManager.createNamedQuery("findImagePathByEmail", String.class);
				query.setParameter("email", email);
				return query.getSingleResult();
			} catch (NoResultException e) {

				return null;
			} catch (Exception e) {

				e.printStackTrace();
				return null;
			} finally {
				if (entityManager != null && entityManager.isOpen()) {
					entityManager.close();
				}
			}
	}
	
	@Override
	public VendorEntity readByEmail(String email) {
		VendorEntity entity = null;
		EntityManager entityManager = emf.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("readByEmail");
			query.setParameter("email", email);
			entity = (VendorEntity) query.getSingleResult();
		} catch (NoResultException e) {
			// Handle case where no entity is found for the given email
			System.out.println("No entity found for email: " + email);
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return entity;
	}
	
	@Override
	public boolean updateById(VendorEntity entity, int id) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			VendorEntity entity2 = entityManager.find(VendorEntity.class, id);
			if (entity2 != null) {
				entity2.setOwnerName(entity.getOwnerName());
				entity2.setEmail(entity.getEmail());
				entity2.setContactNumber(entity.getContactNumber());
				entity2.setAlternativeNumber(entity.getAlternativeNumber());
				entity2.setVendorName(entity.getVendorName());
				entity2.setGstNumber(entity.getGstNumber());
				entity2.setStartDate(entity.getStartDate());
				entity2.setWebsite(entity.getWebsite());
				entity2.setAdress(entity.getAdress());
				entity2.setPincode(entity.getPincode());
				entity2.setImagePath(entity.getImagePath());
				entityManager.merge(entity2);
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
	
	
	
	@Override
	public VendorEntity isExistGstNoContactNoEmailWebsite(String gstNumber, Long contactNumber, String email,
			String website) {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		VendorEntity entity = null;
		try {
			Query query = em.createNamedQuery("isExistGstNoContactNoEmailWebsite");
			query.setParameter("gn", gstNumber);
			query.setParameter("cn", contactNumber);
			query.setParameter("vm", email);
			query.setParameter("web", website);
			entity = (VendorEntity) query.getSingleResult();

		} catch (PersistenceException pe) {
			log.info("PersistenceException in save:" + pe.getMessage());

		} finally {
			log.info("Closing resources");
			em.close();
			log.info("Em closed");
		}
		return entity;
		
	}
	
	@Override
	public List<VendorEntity> findAll() {
		EntityManager em = emf.createEntityManager();
		log.info("Created EM");
		List<VendorEntity> list = new ArrayList<VendorEntity>();
		try {
			Query query = em.createNamedQuery("findAll");
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
	
	@Override
	public int getVendorIdByEmail(String email) {
		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("getVendorIdByEmail");
			query.setParameter("email", email);
			int  count = (int) query.getSingleResult();
			entityManager.getTransaction().commit();
			return count ;
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return 0;
	}
	
	@Override
	public boolean updateStatusById(String status, int id) {
	    EntityManager entityManager = emf.createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    try {
	        entityTransaction.begin();
	        VendorEntity entity = entityManager.find(VendorEntity.class, id);
	        if (entity != null) {
	            
	            entity.setStatus(status);
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
}