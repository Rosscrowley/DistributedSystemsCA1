package com.example.dit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    @Path("/deleteCustomer/{name}")
    @Produces("text/plain")
    public String deleteCustomer(@PathParam("name")String name){
		CustomerDAO dao = new CustomerDAO();
		Customer cus = dao.getCustomerByName(name);
		dao.remove(cus);	
		return "Customer "+cus+" deleted";
    }
	
	@GET
    @Path("/customerfromDBXML/{name}")
    @Produces("application/xml")
    public Customer getCustomerByNameFromDBXML(@PathParam("name")String name){
		CustomerDAO dao = new CustomerDAO();
		return dao.getCustomerByName(name);	
    }

}
