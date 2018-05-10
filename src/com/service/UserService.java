package com.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public interface UserService {
	
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject signUp(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject login(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
	
	/**
	 * 用户根据用户ID信息显示
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject userMsg(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
	
	/**
	 * 用户信息修改
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject upDate(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
