package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.modle.Order;

public interface OrderDao {
	
	/**
	 * 购买图书
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public int buyBook(Order order)throws SQLException;
	
	/**
	 * 付款
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	public int payForBook(int orderId)throws SQLException;
	
	/**
	 * 获得用户的订单
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getOrder(int userId)throws SQLException;
}
