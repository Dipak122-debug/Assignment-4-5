package com.JPA_Example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.JPA_Example.dao.CustomerRepository;
import com.JPA_Example.exceptionHandling.CustomerNotFoundException;
import com.JPA_Example.model.Customer;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	

	public List<Customer> getAllCustomer(){
		
		return customerRepository.findAll();
	}
	
	public Customer getCustomerByID(int id) {
		try {
		
			return customerRepository.findById(id).get();
		
		}
		catch(Exception e) {
			throw new CustomerNotFoundException();
		}
		
	}
	
	public String updateCustomer(Customer data) {
		customerRepository.save(data);
		return "Data saved...";
	}
	
	public ResponseEntity<Object> createCustomer(Customer custom) {
		Optional<Customer> customer = customerRepository.findById(custom.getId());
		if(customer.isPresent()==false) {
			customerRepository.save(custom);
			return new ResponseEntity<Object>("New Customer Added Successfully!",HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Customer Already Exsists!",HttpStatus.BAD_REQUEST);
	}
	
	public String deleteCustomer(int id) {
		customerRepository.deleteById(id);
		return "customer deleted....";
	}
}
