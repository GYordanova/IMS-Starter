package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;
	
	@Mock
	private ItemDAO itemdao;
	
	@Mock
	private CustomerDAO custdao;
	
	@Mock
	private String response;
	
	@Mock
	private Item item;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		Long customerId = 1L;
		Order order = new Order(new Customer(customerId));
		Long Itemid = 1L;
		String response = "no";
		
		when(utils.getLong()).thenReturn(customerId, Itemid);
		when(dao.create(order)).thenReturn(order);
		when(dao.createOrderlineTable(order)).thenReturn(order);
		when(utils.getString()).thenReturn(response);
		
		assertEquals(order, controller.create());
		
		
		
		
//		final long custid=1L;
//		Order ordercreate=new Order(new Customer(custid));
//		final long itemid=1L;
//		final Item item=new Item(1L,"hi","bye",1);
//		List<Item> items= new ArrayList<>();
//		items.add(item);
//		final String response="no";
//		final Order order=new Order(1L,custid,items);
//		
//		when(utils.getLong()).thenReturn(custid,itemid);
//		when(itemdao.create(item)).thenReturn(item);
//		when(dao.create(order)).thenReturn(order);
//		when(utils.getDouble()).thenReturn(price);
//		when(utils.getString()).thenReturn(response);
//		
//		assertEquals(order,controller.create());
//		
//		verify(utils, times(1)).getLong();
//		verify(utils, times(1)).getString();
//		verify(utils, times(1)).getDouble();
//		verify(dao,times(1)).create(order);
//		verify(custdao,times(1)).create(cust);
//		verify(itemdao,times(1)).create(item);
//		
	}

}
