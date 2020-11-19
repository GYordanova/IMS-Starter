package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class OrderDAOTest {
	
	ItemDAO itemdao = new ItemDAO();
	
	OrderDAO dao = new OrderDAO(new CustomerDAO(), itemdao);
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "16021998");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Customer cust= new Customer(1L,null, null);
		final Order order=new Order(cust);
		order.setOrderid(2);
		
		assertEquals(order, dao.create(order));
	}
	

	@Test
	public void testReadLatest() {
		final Customer cust= new Customer(1L,null, null);
		final Item item=itemdao.readItem(1L);
		List<Item> items=new ArrayList<>();
		items.add(item);
		final Order order=new Order(1L,cust,items);
				
		assertEquals(order,dao.readLatest());
	}
	
	@Test
	public void testOrderItemcreate() {
		final long orderid=1L;
		final Item item=itemdao.readItem(1L);
		final Order order=new Order(orderid,item);
		
		assertEquals(order,dao.createOrderlineTable(order));
		
	}
	
	@Test
	public void readOrderTest() {
		final long Orderid=1L;
		final Customer cust= new Customer(1L,null, null);
		final Item item=itemdao.readItem(1L);
		List<Item> items=new ArrayList<>();
		items.add(item);
		final Order order=new Order(1L,cust,items);
		
		assertEquals(order,dao.readOrder(Orderid));
	}
	
	@Test
	public void testDelete() {
		final long Orderid=1L;
		final Customer cust= new Customer(1L,null, null);
		final Item item=itemdao.readItem(1L);
		List<Item> items=new ArrayList<>();
		items.add(item);
		final Order order=new Order(1L,cust,items);
		
		assertEquals(1,dao.delete(Orderid));
	}
	
	@Test
	public void TestdeleteItem() {
		final long orderid=1L;
		final long orderlineid=2L;
		final Item item=itemdao.readItem(1L);
		final Order order=new Order(orderid,item);
		
		assertEquals(0,dao.deleteItem(orderlineid,1L));
	}
	
	@Test
	public void testAddItem() {
		final long orderid=1L;
		final Customer cust= new Customer(1L,null, null);
		final Item item=itemdao.readItem(1L);
		final Item item1=itemdao.readItem(2L);
		List<Item> items=new ArrayList<>();
		items.add(item);
		items.add(item1);
		final Order order=new Order(1L,cust,items);
		
		assertEquals(order,dao.addItem(orderid, 2L));
	
	}
	
	@Test
	public void testereadAll() {
		final long orderid=1L;
		final Customer cust= new Customer(1L,null, null);
		final Item item=itemdao.readItem(1L);
		List<Item> items=new ArrayList<>();
		items.add(item);
		final Order order=new Order(orderid,cust,items);
		List<Order> orders=new ArrayList<>();
		orders.add(order);
		assertEquals(orders,dao.readAll());
	}
	
//	@Test
//	public void testOrderItemcreate() {
//		final long orderid=1L;
//		final Item item=new Item(1L,"chrisps","walkers",0.79);
//		List<Item> items = new ArrayList<Item>();
//		items.add(item);
//		final Order order=new Order(orderid, item);
//		order.setCustomer(new Customer(1L, null, null));
//		order.setPrice(0.79);
//		Order orderExpected = new Order(orderid, item);
//		orderExpected.setPrice(0.79);
//		orderExpected.setItems(items);
//		orderExpected.setCustomer(new Customer(1L, null, null));
//		assertEquals(orderExpected,dao.createOrderlineTable(order));
//	}
	
	


}
