package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.modle.User;

public interface UserDao {
	/**
	 * 用户注册
	 * @param userName
	 * @param password
	 * @return  int : 1 注册成功，0 注册失败
	 */
	public int signUp(String userName,String passwor) throws SQLException;
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return  
	 */
	public User login(String userName,String password) throws SQLException;
	
	/**
	 * 用户修改
	 * @param userID
	 * @param userName
	 * @param password
	 * @return int : 1 修改成功，0 修改失败
	 */
	public int upDate(int userID,String userName,String password) throws SQLException;
	
	/**
	 * 用户信息显示
	 * @param userID
	 * @return 
	 */
	public List<Map<String, Object>> userMsg(int userID) throws SQLException;
	
	/**
	 * 检验用户是否已存在
	 * @param userName
	 * @return int : 1 用户不存在，0 用户已存在
	 * @throws SQLException
	 */
	public int userCheck(String userName) throws SQLException;


}
