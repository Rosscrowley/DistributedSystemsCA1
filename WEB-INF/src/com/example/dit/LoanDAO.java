package com.example.dit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Loan;

public class LoanDAO {

	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb"); 	
	
	public LoanDAO() {
		
	}
	
	public void persist(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(loan);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(loan));
		em.getTransaction().commit();
		em.close();
	}
	
	public Loan merge(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Loan updatedLoan = em.merge(loan);
		em.getTransaction().commit();
		em.close();
		return updatedLoan;
	}
	
	public List<Loan> getAllLoans() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Loan> loans = new ArrayList<Loan>();
		loans = em.createQuery("from Loan").getResultList();
		em.getTransaction().commit();
		em.close();
		return loans;
	}
	
	public Loan getLoanByID(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Loan l = em.createQuery("SELECT p FROM Loan p WHERE p.id = :id", Loan.class)
                .setParameter("id", id)
                .getSingleResult();
		em.getTransaction().commit();
		em.close();
		return l;
	}
	
}
