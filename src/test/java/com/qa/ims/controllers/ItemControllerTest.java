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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;


@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;
	
	@Test
	public void testCreate() {
		final String Itemname="hi";
		final String Itemmake="bye";
		final double price=0.79;
		final Item item = new Item(Itemname,Itemmake,price);
		
		when(utils.getString()).thenReturn(Itemname,Itemmake);
		when(utils.getDouble()).thenReturn(price);
		when(dao.create(item)).thenReturn(item);
		
		assertEquals(item, controller.create());

		verify(utils, times(1)).getDouble();
		verify(utils, times(2)).getString();
		verify(dao, times(1)).create(item);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items= new ArrayList<>();
		items.add(new Item(1L,"hi", "bye", 0.79));
		
		when(dao.readAll()).thenReturn(items);
		
		assertEquals(items,controller.readAll());
		
		verify(dao,times(1)).readAll();
		
	}
	
	@Test
	public void testUpdate() {
		final Long id=1L;
		final String Itemname="hi";
		final String Itemmake="bye";
		final double price=0.79;
		final Item updated = new Item(id, Itemname,Itemmake,price);
		
		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(Itemname,Itemmake);
		when(utils.getDouble()).thenReturn(price);
		when(dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated,controller.update());
		
		verify(utils, times(1)).getLong();
		verify(utils, times(2)).getString();
		verify(utils, times(1)).getDouble();
		verify(dao, times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long id=1L;
		
		when(utils.getLong()).thenReturn(id);
		when(dao.delete(id)).thenReturn(1);
		
		assertEquals(1L,controller.delete());
		
		verify(utils,times(1)).getLong();
		verify(dao,times(1)).delete(id);
	}


}
