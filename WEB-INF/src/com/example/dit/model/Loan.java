package com.example.dit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loan")
@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String description;
	private int amount;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Deposit> deposits = new ArrayList<Deposit>();
	
	public Loan() {

	}

	public Loan( String description, int amount, List<Deposit> deposits) {
		super();
		this.description = description;
		this.amount = amount;
		this.deposits = deposits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@XmlElement
	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	public void addDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		
		this.deposits.add(deposit);
		
	}
	
	
	
	

}
