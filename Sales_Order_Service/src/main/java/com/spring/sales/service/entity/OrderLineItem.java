package com.spring.sales.service.entity;

import javax.persistence.Id;

public class OrderLineItem {
	
	@Id
	private long itemId;
	private String itemName;
	private long quantity;
	private SalesOrderId orderId;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public SalesOrderId getOrderId() {
		return orderId;
	}
	public void setOrderId(SalesOrderId orderId) {
		this.orderId = orderId;
	}
	
}
