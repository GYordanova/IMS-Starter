package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	private CustomerDAO customerdao;
	private ItemDAO itemdao;

	public OrderDAO(CustomerDAO customerdao, ItemDAO itemdao) {
		this.customerdao = customerdao;
		this.itemdao = itemdao;
	}

	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderid = resultSet.getLong("Orderid");
		Customer customer = new Customer(resultSet.getLong("Customerid"),null, null);
		List<Item> items = orderitem(resultSet.getLong("Orderid"));
		return new Order(orderid, customer, items);
	}
	public Order modelFromResultSet1(ResultSet resultSet) throws SQLException {
		Long orderid = resultSet.getLong("Orderid");
		Item item= itemdao.readItem(resultSet.getLong("Itemid"));
		//new Item(resultSet.getLong("Itemid"),resultSet.getString("Itemname"),null,resultSet.getDouble("price"));
		//itemdao.readItem(resultSet.getLong("Itemid"));
		return new Order(orderid, item);
	}
	
//	public List<Item> itemGet(long id) {
//		List<Long> itemID = new ArrayList<>();
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();
//				ResultSet resultset = statement.executeQuery("SELECT i.* FROM ims.items i, ims.orderline ol WHERE i.Itemid = ol.Itemid and orderid = " + id);) {
//			while (resultset.next()) {
//				itemID.add(resultset.getLong("Itemid"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return itemID.stream().map(this.itemdao::readItem).collect(Collectors.toList());
//	}
	
	public List<Item> orderitem(Long orderid){
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery("SELECT * FROM ims.orderline WHERE Orderid = " + orderid);) {
			List<Item> itemorder = new ArrayList<>();
			while (resultset.next()) {
				itemorder.add(itemdao.readItem(resultset.getLong("Itemid")));
			}
			return itemorder;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ims.order ;");) {
				
			List<Order> orders = new ArrayList<>();
		while (resultSet.next()) {
			orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ims.order ORDER BY Orderid DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatest1() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ims.orderline ORDER BY Orderlineid DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet1(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO ims.order(Customerid) VALUES (" + order.getCustomer().getId() + ")");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order createOrderlineTable(Order order) {
		try(Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
				statement.executeUpdate(
						"INSERT INTO ims.orderline(Orderid,Itemid) VALUES(" + order.getOrderid() + ", " + order.getItem().getItemid() + ")");
				return readLatest1();
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		
		return null;
	}

	
	public Order addItem(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				PreparedStatement stmt = connection.prepareStatement("Insert into ims.orderline (Orderid, Itemid) Values(?,?)")) {
				stmt.setLong(1, orderId);
				stmt.setLong(2, itemId);
				stmt.executeUpdate();
			}catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return readLatest();
	}

	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ims.order where Orderid = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public int deleteItem(Long Orderlineid,Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from ims.orderline where Itemid = " + itemId + " AND Orderlineid= " + Orderlineid);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public Order update(Order order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();){
//			statement.executeUpdate("UPDATE ims.order(Customerid) VALUES (" + order.getCustomer().getId() + ")");
//			statement.executeUpdate("UPDATE ims.orderline(Orderid,Itemid) VALUES (" + order.getOrderid() + "," +order.getItem().getItemid());
//			return readOrder(order.getOrderid());
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return null;
		
	}
	
	
	

//	public Order update(Order order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();) {
//			statement.executeUpdate("update ims.order set Customerid = "+ order.getCustomer().getId() + "',price= '"
//					+ order.getPrice() + ")", statement.RETURN_GENERATED_KEYS );
//			ResultSet rs = statement.getGeneratedKeys();
//			long key = 0;
//			if (rs != null && rs.next()) {
//			    key = rs.getLong(1);
//			}
//
//			{
//			statement.executeUpdate(
//					"update ims.orderline set Orderid=" + key + "',Itemid'" + order.getItem().getItemid() + "')");
//			}
//			return readLatest();
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//	}

	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from ims.orderline where Orderid = " + id);
			return statement.executeUpdate("delete from ims.order where Orderid = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}


}
