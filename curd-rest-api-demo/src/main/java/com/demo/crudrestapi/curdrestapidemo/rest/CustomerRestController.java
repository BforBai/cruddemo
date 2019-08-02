package com.demo.crudrestapi.curdrestapidemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.crudrestapi.curdrestapidemo.entity.Customer;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	private List<Customer> theCustomers;
	
	@PostConstruct
	public void loadCustomers() {
		
		theCustomers = new ArrayList<>();
		
		theCustomers.add(new Customer(1, "AB", "AB@outlook.com"));
		theCustomers.add(new Customer(2, "CD", "CD@outlook.com"));
		theCustomers.add(new Customer(3, "EF", "EF@outlook.com"));
	}
	
	@GetMapping("/customers")
	public List<Customer> getAll(){
		
		return theCustomers;
	}
	
	@GetMapping("customers/{customerID}")
	public Customer getById(@PathVariable("customerID") int theID) {
		
		for(Customer c : theCustomers) {
			if(c.getId()==theID) {
				return c;
			}
			
			throw new RuntimeException("Customer not found!");
		}
		
		return null;
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer customer) {
		
		theCustomers.add(customer);
		
		return "Success!";
	}
	
	@PutMapping("/customers")
	public String updateCustomer(@RequestBody Customer customer) {
		
		for(Customer c : theCustomers) {
			if(c.getId() == customer.getId()) {
				theCustomers.set(theCustomers.indexOf(c), customer);
				return "Success! Updated customer: " + customer.getName(); 
			}
		}
		
		return "Customer does not exist!";
	}
	
	@DeleteMapping("/customers")
	public String deleteCustomer(@RequestParam(value = "theID") int theID) {
		
		for(Customer c : theCustomers) {
			if(c.getId() == theID) {
				System.out.println("Customer found!");
				theCustomers.remove(c);
				return "Delete customer successfully!";
			}
		}
		
		throw new RuntimeException("Delete customer failed. Invalid ID or customer does not exist!");
	}
	
}
