package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	private final ItemDAO itemdao = new ItemDAO();

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
		final Item created = new Item(3L, "laptop", "dell", 0.75);
		assertEquals(created, itemdao.create(created));
	}
	
	@Test
	public void testCreateFail() {
		final Item created = new Item(2L, " 'chrisps", "walkers",0.79);
		assertNull( itemdao.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "chrisps", "walkers",0.79));
		expected.add(new Item(2L,"chrispy", "walky",1));
		assertEquals(expected,itemdao.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(2L,"chrispy", "walky",1), itemdao.readLatest());
	}
	
	@Test
	public void testRead() {
		final long Itemid = 1L;
		assertEquals(new Item(Itemid,"chrisps", "walkers",0.79), itemdao.readItem(Itemid));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "HI", "BYE",5);
		assertEquals(updated, itemdao.update(updated));
	}
	
	@Test
	public void testUpdatefail() {
		final Item updated = new Item(1L, "HI'", "BYE",0.78);
		assertNull(itemdao.update(updated));
	}
	
	@Test
	public void testReadFail() {
		final Item updated = new Item(10L, "HI", "BYE",0.78);
		assertNull(itemdao.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, itemdao.delete(1));
	}
	
}
