package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderdao;
	private CustomerDAO customerdao; 
	private ItemDAO itemdao; 
	private Utils utils;
	//private Item item;
	private Order order;
	
	public OrderController(OrderDAO orderdao, Utils utils) {
		super();
		this.orderdao = orderdao;
		this.utils = utils;
	}


	public OrderController(OrderDAO orderdao, ItemDAO itemdao, CustomerDAO customerdao, Utils utils) {
		super();
		this.orderdao = orderdao;
		this.itemdao = itemdao;
		this.customerdao = customerdao;
		this.utils = utils;
	}
	
	public Order create() {
		LOGGER.info("Please enter customer id");
		Long Customerid = utils.getLong();
		Order orderCreated =orderdao.create( new Order(new Customer(Customerid)));
		String response = "no";
		do {
			LOGGER.info("Please enter item id");
			Long Itemid = utils.getLong();
			orderCreated.setItem(itemdao.readItem(Itemid));
			orderdao.createOrderlineTable(orderCreated);
			LOGGER.info("Do you want to add another?");
			response = utils.getString();
		}while (response.toLowerCase().equals("yes"));
		return order;
	}
	
	public Order update() {
		System.out.println(orderdao.readAll());
		LOGGER.info("Please enter order id");
		Long orderId = utils.getLong(); 
		LOGGER.info("Do you want to ADD an item to an order or DELETE ITEM from an order");
		String response= utils.getString();
		while (response.toLowerCase().equals("add") || response.toLowerCase().equals("yes")) {
			LOGGER.info("Please enter the item id of the item you want to add:");
			Long itemId=utils.getLong(); 
			Order order = orderdao.addItem(orderId, itemId);
			System.out.println(orderdao.orderitem(orderId));
			LOGGER.info("Would you like to add another?");
			response = utils.getString();
		}
		while(response.toLowerCase().equals("deleteitem") || response.toLowerCase().equals("Delete Item")|| response.toLowerCase().equals("yes")) {
			System.out.println(orderdao.readAll() + "Order/item id"+ orderdao.orderitem(orderId));
			LOGGER.info("Please enter the item id of the item you want to delete:");
			Long itemId=utils.getLong();
			LOGGER.info("Please enter the orderline id of the item you want to delete:");
			Long orderline=utils.getLong();
			orderdao.deleteItem(orderline,itemId);
			LOGGER.info("Would you like to delete another?");
			response = utils.getString();
			
		}
		LOGGER.info("Order updated");
		return order;
		
	}

	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderid = utils.getLong();
		return orderdao.delete(orderid);
	}

	public List<Order> readAll() {
		List<Order> orders;
		orders = orderdao.readAll();
		for(Order order:orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

}
//	public Order create() {
//		LOGGER.info("Please enter customer id");
//		Long Customerid = utils.getLong();
//		LOGGER.info("Please enter item id");
//		Long Itemid = utils.getLong();
//		
//		List<Item> items = new ArrayList<>(); 
//		items.add(itemdao.readItem(Itemid));
//		
//		String response;
//		LOGGER.info("Would you like to add another?");
//		response = utils.getString();
//		while(response.equals("yes") || response.equals("YES")) {
//			LOGGER.info("Please enter item id");
//			Long item = utils.getLong();			
//			items.add(itemdao.readItem(item));
//			LOGGER.info("Would you like to add another?");
//			response = utils.getString();
//		}
//		
//		Order order = orderdao.create(new Order(customerdao.readCustomer(Customerid),items));
//		
//		LOGGER.info("Order created");
//		return order;
//	}