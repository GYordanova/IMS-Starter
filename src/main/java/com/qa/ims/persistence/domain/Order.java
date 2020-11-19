package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private long orderid;
	private Customer customer;
	private Item item;
	private List<Item> items = new ArrayList<>();
	private double price;

	


	public Order(Customer customer, List<Item> items) {
		this.customer = customer;
		this.items = items;
		
		for(Item item: items) {
			this.price += item.getItemprice();
		}
	}
	public Order(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order(long orderid, Customer customer, List<Item> items) {
		this.orderid = orderid;
		this.customer = customer;
		this.items = items;
		
		for(Item item: items) {
			this.price += item.getItemprice();
		}
		
	}
	

	public Order(Long orderid, Item item) {
		this.orderid=orderid;
		this.item=item;
	}
	public Order(Long orderid, Customer customer) {
		this.orderid=orderid;
		this.customer=customer;
	}
	
	
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		String itemsString = "";
		for(Item item: this.items) {
			itemsString += "- itemId:" + item.getItemid() +"\n";
		}
		String  orderString = "\nOrderid=" + orderid + "\ncustomerID=" + customer.getId() + "\nitems:\n" + itemsString + "Total price=" + price + "\n";
		return orderString;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderid != other.orderid)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
