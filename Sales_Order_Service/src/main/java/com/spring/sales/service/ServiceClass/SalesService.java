package com.spring.sales.service.ServiceClass;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.sales.service.UserData.SalesOrderRequest;
import com.spring.sales.service.dto.Item;
import com.spring.sales.service.entity.SalesOrderId;
import com.spring.sales.service.repository.SalesOrderRepository;

@Service
public class SalesService {

	@Autowired
	private SalesOrderRepository salesOrderRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	public SalesOrderId saveOrder(SalesOrderRequest orderRequest)
	{
		SalesOrderId salesOrder = new SalesOrderId();
		Item item = new Item();
		salesOrder.setCustId(orderRequest.getCustId());
		salesOrder.setOrderDate(orderRequest.getOrderDate());
		salesOrder.setOrderDesc(orderRequest.getOrderDesc());
		item.setItemNames(orderRequest.getItemNames());
		System.out.println("custid>>"+salesOrder.getCustId());
		System.out.println("orderDate>>"+salesOrder.getOrderDate());
		System.out.println("orderDesc>>"+salesOrder.getOrderDesc());
		System.out.println("itemName list>>"+item.getItemNames());
		
		SalesOrderId saveOrderData = salesOrderRepository.save(salesOrder);
		salesOrder.setOrderId(saveOrderData.getOrderId());
		System.out.println("order id>>"+salesOrder.getOrderId());
		return saveOrderData;
	}
	
	public Item validateItem(SalesOrderRequest orderRequest){
		Item item = new Item();
		item.setItemNames(orderRequest.getItemNames());
		List<Object> items = restTemplate.getForObject("http://item-service/service2/items/", List.class);
		item.setItems(items);
		
		/*for (int i=0; i<items.size(); i++){
			Object[] row = (Object[]) items.get(i);
			System.out.println("Item for loop>> "+i+Arrays.toString(row));
		}*/
		System.out.println("item list from item service>>"+items);
		System.out.println("item name list in validateItem method>>"+item.getItemNames());
		return item;
	}
}
