package com.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.DataBaseConnect;
import com.dao.UserDao;
import com.modle.User;

public class UserMapper implements UserDao {
	
	private Connection con;
	private PreparedStatement prs;
	private ResultSet rs;
	private String sql;
	private User user;
	/**
	 * 连接数据库
	 */
	public UserMapper(){
		user = new User();
		con = new DataBaseConnect().getConnection();
	}
	
	@Override
	public int signUp(String userName, String password) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="insert into user(user_name,user_password) values(?,?)";
			prs=con.prepareStatement(sql);
			prs.setString(1, userName);
			prs.setString(2, password);
			int result=prs.executeUpdate();
			return result;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("用户注册异常");
			throw e;
		}

	}

	@Override
	public User login(String userName, String password) throws SQLException{
		// TODO Auto-generated method stub
		try{
			sql="select * from user where user_name=? and user_password=?";
			prs=con.prepareStatement(sql);
			prs.setString(1,userName);
			prs.setString(2,password);
			rs=prs.executeQuery();
			if(rs.next()){
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserStatus(rs.getInt(4));
				return user;
			}else{
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("用户登录异常");
			throw e;
		}
	}

	@Override
	public int upDate(int userID,String userName, String password) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="update user set user_name=?,user_password=? where user_id=?";
			prs=con.prepareStatement(sql);
			prs.setString(1, userName);
			prs.setString(2, password);
			prs.setInt(3, userID);
			int Result=prs.executeUpdate();
			if(Result==1){
				return Result;
			}
			return 0;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("用户修改预处理异常");
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> userMsg(int userID) throws SQLException {
		// TODO Auto-generated method stub
		Map<String,Object> user=new HashMap();
		List<Map<String,Object>> list=new ArrayList();
		try{
			sql="select * from user where user_id=?";
			prs=con.prepareStatement(sql);
			prs.setInt(1, userID);
			rs=prs.executeQuery();
			if(rs.next()){
				user.put("userID",rs.getInt(1));
				user.put("userName", rs.getString(2));
				user.put("password", rs.getString(3));
				user.put("userStatus", rs.getInt(4));
				list.add(user);
				return list;
			}
		return null;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("用户信息查找异常");
			throw e;
		}
	}

	@Override
	public int userCheck(String userName) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="select user_name from user where user_name=?";
			prs=con.prepareStatement(sql);
			prs.setString(1, userName);
			rs=prs.executeQuery();
			if(rs.next()){
				return 0;
			}
			return 1;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("检验用户是否已存在预处理异常");
			throw e;
		}
	}

}
