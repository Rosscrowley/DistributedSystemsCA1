package com.example.dit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.dit.model.Customer;
import com.example.dit.model.Deposit;
import com.example.dit.model.Loan;


@Path("/loanService")
public class loanService {
	
	@GET
    @Path("/customersFromDBXML")
    @Produces("application/xml")
    public List<Customer> getCustomerFromDBXML(){
		CustomerDAO dao = new CustomerDAO();
        return dao.getAllCustomers();
    }
	
	@GET
    @Path("/customersFromDBJSON")
    @Produces("application/json")
    public List<Customer> getCustomerFromDBJSON(){
		CustomerDAO dao = new CustomerDAO();
        return dao.getAllCustomers();
    }
	
	@POST
	@Path("/newCustomer")
    @Consumes("application/json")
    public String addEmployeeToDBJSON(Customer customer){
		CustomerDAO dao = new CustomerDAO();
		dao.persist(customer);
		return "Employee added to DB from JSON Param "+customer.getCustomerName();	
    }
	
	@DELETE
    @Path("/deleteCustomer/{id}")
    @Produces("text/plain")
    public String deleteCustomer(@PathParam("id")int id){
		CustomerDAO dao = new CustomerDAO();
		Customer cus = dao.getCustomerById(id);
		dao.remove(cus);	
		return "Customer "+cus+" deleted";
    }
	
	@GET
    @Path("/customerfromDBXML/{id}")
    @Produces("application/xml")
    public Customer getCustomerByNameFromDBXML(@PathParam("id")int id){
		CustomerDAO dao = new CustomerDAO();
		return dao.getCustomerById(id);	
    }
	
	@GET
    @Path("/depositsFromDBJSON")
    @Produces("application/json")
    public List<Deposit> depositsFromDBJSON(){
		DepositDAO dao = new DepositDAO();
        return dao.getAllDeposits();
    }
	
	@DELETE
    @Path("/deleteDeposit/{id}")
    @Produces("text/plain")
    public String deleteDeposit(@PathParam("id")int id){
		DepositDAO dao = new DepositDAO();
		Deposit dep = dao.getDepositByID(id);
		dao.remove(dep);	
		return "Deposit "+dep+" deleted";
    }
	
	@GET
    @Path("/depositfromDBXML/{id}")
    @Produces("application/xml")
    public Deposit depositfromDBXML(@PathParam("id")int id){
		DepositDAO dao = new DepositDAO();
		return dao.getDepositByID(id);	
    }
	
	@GET
    @Path("/loansFromDBJSON")
    @Produces("application/json")
    public List<Loan> loansFromDBJSON(){
		LoanDAO dao = new LoanDAO();
        return dao.getAllLoans();
    }
	
	@DELETE
    @Path("/deleteLoan/{id}")
    @Produces("text/plain")
    public String deleteLoan(@PathParam("id")int id){
		LoanDAO dao = new LoanDAO();
		Loan loan = dao.getLoanByID(id);
		dao.remove(loan);	
		return "Deposit "+loan+" deleted";
    }
	
	@GET
    @Path("/loanfromDBXML/{id}")
    @Produces("application/xml")
    public Loan loanfromDBXML(@PathParam("id")int id){
		LoanDAO dao = new LoanDAO();
		return dao.getLoanByID(id);	
    }
	
	@POST
	@Path("/newCustomer")
    @Consumes("application/json")
    public String newCustomer(Customer customer){
		CustomerDAO userDao = new CustomerDAO();
		LoanDAO loanDao = new LoanDAO();
		Loan loan = customer.getLoan();
		customer.setLoan(loan);
        userDao.persist(customer);
        loanDao.persist(loan);
		return "customer added to DB "+customer.getId();		
    }
	
	@PUT
    @Path("/updateCustomer/")
    @Produces("application/json")
    public Customer updateEmployee(Customer customer){
		CustomerDAO dao = new CustomerDAO();
		return dao.merge(customer);	
    }
	
	@PUT
    @Path("/updateLoan/")
    @Produces("application/json")
    public Loan updateLoan(Loan loan){
		LoanDAO dao = new LoanDAO();
		return dao.merge(loan);	
    }
	
	@POST
    @Path("/newDeposit/{id}")
	@Consumes("application/json")
    public String newDeposit(@PathParam("id") int id, Deposit deposit) {
		LoanDAO loanDao = new LoanDAO();
		DepositDAO depositDao = new DepositDAO();
		Loan loan = loanDao.getLoanByID(id);
		if (loan != null) {
			depositDao.persist(deposit);
            loan.addDeposit(deposit);
            loanDao.merge(loan);
		}
		return "New deposit added to Loan "+id;
	}
	

}
