package com.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public interface OrderService {
	/**
	 * 购买图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject buyBook(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	/**
	 * 支付
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject payForBook(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	
	/**
	 * 获得用户图书订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getOrder(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
}
