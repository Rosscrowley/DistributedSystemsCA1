package com.example.dit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Customer;

public class CustomerDAO {

	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb"); 
	
public CustomerDAO() {
		
	}

public void persist(Customer customer) {
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	em.persist(customer);
	em.getTransaction().commit();
	em.close();
}

public void remove(Customer customer) {
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	em.remove(em.merge(customer));
	em.getTransaction().commit();
	em.close();
}

public Customer merge(Customer customer) {
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	Customer updatedSubscriber = em.merge(customer);
	em.getTransaction().commit();
	em.close();
	return updatedSubscriber;
}

public List<Customer> getAllCustomers() {
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	List<Customer> customersFromDB = new ArrayList<Customer>();
	customersFromDB = em.createNamedQuery("Customer.findAll").getResultList();
	em.getTransaction().commit();
	em.close();
	return customersFromDB;
}

public Customer getCustomerByName(String name){
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	Customer c = em.createQuery("SELECT p FROM Customer p WHERE p.name = :name", Customer.class)
            .setParameter("name", name)
            .getSingleResult();
	em.getTransaction().commit();
	em.close();
	return c;
	
}

public Customer getCustomerById(int id){
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	Customer c = em.createQuery("SELECT p FROM Customer p WHERE p.id = :id", Customer.class)
            .setParameter("id", id)
            .getSingleResult();
	em.getTransaction().commit();
	em.close();
	return c;
	
}


}
