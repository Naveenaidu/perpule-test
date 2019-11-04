package com.naveen;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/")
public class CustomerResource {
	CustomerRepository repo = new CustomerRepository();
	
	// Get all the customers
	@GET
	@Secured
	@Path("customers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers() {
		
		// Get all the customers from the list
		return repo.getCustomers();
	}
	
	// Get Customer by their ID
	@GET
	@Secured
	@Path("get-customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") int id) {
		return repo.getCustomer(id);
	
	}
	
	// Post method to update customers database
	@POST
	@Secured
	@Path("add-customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCustomer(Customer c) {
		repo.createCustomer(c);
	}
}