package com.example.dit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Deposit;

public class DepositDAO {

	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb"); 	
	
	public DepositDAO() {
		
	}
	
	public void persist(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(deposit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(deposit));
		em.getTransaction().commit();
		em.close();
	}
	
	public Deposit merge(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Deposit updatedDeposit = em.merge(deposit);
		em.getTransaction().commit();
		em.close();
		return updatedDeposit;
	}
}
