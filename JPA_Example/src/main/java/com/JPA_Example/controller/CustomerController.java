package com.JPA_Example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JPA_Example.exceptionHandling.CustomerNotFoundException;
import com.JPA_Example.model.Customer;
import com.JPA_Example.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getAll")
	public List<Customer> getAllCustomers(){
		
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/getCustomerById/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		
		Customer custom = customerService.getCustomerByID(id);
		if(null!=custom) {
		
			return custom;
		}
		else {
		throw new CustomerNotFoundException();
			
		} 
		
	}
	
	@GetMapping("/getCustomerById_Hecteoas/{id}")
	public EntityModel <Customer> getCustomerById_Hecteos(@PathVariable(value="id", required=false) int id){
		Customer custom = customerService.getCustomerByID(id);
		if(null!=custom) {
			EntityModel <Customer> customer = EntityModel.of(custom);
			WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).getAllCustomers());
			customer.add(link.withRel("url-allCustomer"));
			return customer;
		}
		else {
			throw new CustomerNotFoundException();
				
			} 
	} 
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer){
		
		return customerService.createCustomer(customer);
	}
	

	@PostMapping("/updateCustomer")
	public int updateCustomer(@RequestBody Customer custom) {
		customerService.updateCustomer(custom);
		return custom.getId();
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable("id") int id) {
		
		 customerService.deleteCustomer(id);
	}

}
