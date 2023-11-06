package com.example.dit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="select o from Customer o")
})

@XmlRootElement(name = "customer")
@Entity
public class Customer {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String phoneNumber;
	private String address;
	private String salary;
	
	@OneToOne
	private Loan loan;
	
	public Customer() {

	}

	public Customer(String name, String phoneNumber, String address, String salary, Loan loan) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
		this.loan = loan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getCustomerName() {
		return name;
	}

	public void setCustomerName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@XmlElement
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	
}
