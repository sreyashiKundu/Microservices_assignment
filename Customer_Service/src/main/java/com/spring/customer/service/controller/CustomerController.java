package com.spring.customer.service.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.customer.service.entity.Customer;
import com.spring.customer.service.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/service1/customers")
	/*@HystrixCommand(fallbackMethod = "fallback_retrieve", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
			})*/
	public List<Customer> retrieveAllCustomerDetails(){
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
	@PostMapping("/service1/customer")
	public Customer createCustomer(@RequestBody Customer customer){
		
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}
	
	private String fallback_retrieve() {
	      return "Request fails. It takes long time to retrieve customer details";
	   }
}
