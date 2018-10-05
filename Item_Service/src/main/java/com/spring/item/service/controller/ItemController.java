package com.spring.item.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.item.service.entity.Items;
import com.spring.item.service.repository.ItemRepository;

@RestController
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	//@HystrixCommand(fallbackMethod = "fallbackRetrieve")
	@GetMapping("/service2/items")
	public List<Items> retrieveItems(){
		List<Items> itemList = itemRepository.findAll();
		return itemList;
	}
	
	@GetMapping("/service2/items/{itemname}")
	public Items retrieveItemByName(@PathVariable("itemname") String itemName){
		Items fetchItem = itemRepository.findByitemName(itemName);
		return fetchItem;
		
	}
	
	public String fallbackRetrieve(){
		return "This is fallback for item retrieve method";
				
	}
}
