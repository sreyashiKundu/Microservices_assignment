package com.spring.sales.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.sales.service.ServiceClass.SalesService;
import com.spring.sales.service.UserData.SalesOrderRequest;
import com.spring.sales.service.dto.Item;
import com.spring.sales.service.entity.CustomerSOS;
import com.spring.sales.service.entity.SalesOrderId;
import com.spring.sales.service.repository.SalesOrderRepository;

@RestController
public class SalesOrderController {

	@Autowired
	private SalesService salesService;
		
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/service3-itemData/")
	public Item getItemNames(){
		Item item = new Item();
		List<Object> items = restTemplate.getForObject("http://item-service/service2/items/", List.class);
		item.setItems(items);
		return item;
	}
	

	@PostMapping("/service3/orders")
	public SalesOrderId createOrder(@RequestBody SalesOrderRequest orderRequest){
		System.out.println("in create order>>>");
		SalesOrderId saveResponse = salesService.saveOrder(orderRequest);
		System.out.println("saveResponse>>"+saveResponse);
		return saveResponse;
		
	}
	@PostMapping("/service2/items/itemname") 
	public Item validateItemName(@RequestBody SalesOrderRequest orderRequest){
		Item validItem = salesService.validateItem(orderRequest);
		return validItem;
	}
	
	@RequestMapping("/test")
	public String testing(){
		return "Tested sales service";
	}
	/*@GetMapping("/service3-feign/customer")
	public CustomerSOS saveCustomerData(){
		CustomerSOS responseSOS = customerServiceProxy.retrieveCustomerInfo();
		logger.info("{}", responseSOS	);
		return new CustomerSOS(responseSOS.getId(),responseSOS.getEmail(),responseSOS.getFirstName(),responseSOS.getLastName());
		
	}*/
	
}
