package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.AccountClient;
import com.revature.models.Account;
import com.revature.models.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private Logger log = Logger.getLogger(CustomerController.class);

	@Autowired
	private AccountClient accountClient;
	
	private List<Customer> customers = new ArrayList<>();
	
	public CustomerController() {
		customers.add(new Customer(1,"Sally Jenkins","sjenkins35@gmail.com",null));
		customers.add(new Customer(2,"Nicholas Mitchell","nmitch394@gmail.com",null));
		customers.add(new Customer(3, "Todd Hoyt", "thoyt333@gmail.com", null));
		customers.add(new Customer(4, "Paula Smith", "pjsmith@gmail.com", null));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		log.info("GET /customers - getting all customers");
		return customers;
	}
	
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId")Integer customerId) {
		log.info("GET /customers/"+customerId+" - getting customer by id: "+customerId);
		Customer customer = null;
		for(Customer c: customers) {
			if(c.getCustomerId() == customerId) {
				customer = c;
				List<Account> customerAccounts = accountClient.getAccountsByCustomerId(customerId);
				customer.setAccounts(customerAccounts);
			}
		}
		return customer;
	}
	
	
	
}
