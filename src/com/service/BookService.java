package com.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public interface BookService {
	/**
	 * 添加图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject addBook(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	/**
	 * 删除图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject deleteBook(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	/**
	 * 修改图书信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject upDateBook(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	
	/**
	 * 查询所有图书信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getAllBookMsg(HttpServletRequest req,HttpServletResponse resp) throws SQLException;

	/**
	 * 显示推荐的图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getSomeBookMsg(HttpServletRequest req,HttpServletResponse resp) throws SQLException;
	

}
