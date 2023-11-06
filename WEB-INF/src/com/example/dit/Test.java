package com.example.dit;

import java.util.ArrayList;
import java.util.List;

import com.example.dit.CustomerDAO;
import com.example.dit.DepositDAO;
import com.example.dit.LoanDAO;
import com.example.dit.model.Customer;
import com.example.dit.model.Deposit;
import com.example.dit.model.Loan;


public class Test {

	
	public Test() {
		CustomerDAO cDAO = new CustomerDAO();
		LoanDAO lDAO = new LoanDAO();
		DepositDAO dDAO = new DepositDAO();
		
		
		Deposit dep1 = new Deposit("01/12/2022", 1000);
		Deposit dep2 = new Deposit("01/12/2022", 1500);
		Deposit dep3 = new Deposit("01/12/2022", 900);
		dDAO.persist(dep1);
		dDAO.persist(dep2);
		dDAO.persist(dep3);
		
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits.add(dep1);
		deposits.add(dep2);
		deposits.add(dep3);
		
		Loan loan = new Loan("Car loan", 10000, deposits);
		lDAO.persist(loan);
		
		// String name, String phoneNumber, String address, String salary, Loan loan
		Customer cus1 = new Customer("Bob", "0858733367", "19 Bray", "190,000", loan);
		cDAO.persist(cus1);
		
		ArrayList<Customer> customers = (ArrayList<Customer>) cDAO.getAllCustomers();
		
		for(Customer c : customers) {
			
			System.out.println("Customers name is "+c.getCustomerName());
			
		}
		
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
