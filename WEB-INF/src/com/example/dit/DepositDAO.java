package com.example.dit;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Deposit> getAllDeposits(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits = em.createQuery("from Deposit").getResultList();
		em.getTransaction().commit();
		em.close();
		return deposits;
	}
	
	public Deposit getDepositByID(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Deposit d = em.createQuery("SELECT p FROM Deposit p WHERE p.id = :id", Deposit.class)
                .setParameter("id", id)
                .getSingleResult();
		em.getTransaction().commit();
		em.close();
		return d;
	}
	
}
