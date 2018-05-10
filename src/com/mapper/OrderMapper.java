package com.mapper;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.DataBaseConnect;
import com.dao.OrderDao;
import com.modle.Order;

public class OrderMapper implements OrderDao{
	
	private Connection con=null;
	private PreparedStatement prs=null;
	private ResultSet rs=null;
	private String sql=null;
	public OrderMapper(){
		con=new DataBaseConnect().getConnection();
	}
	@Override
	public int buyBook(Order order) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql = "insert into bookorder(user_id,book_id,book_name) values(?,?,?)";
			prs = con.prepareStatement(sql);
			prs.setInt(1, order.getUserId());
			prs.setInt(2, order.getBookId());
			prs.setString(3, order.getBookName());
			int result = prs.executeUpdate();
			return result;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("添加账单异常");
			throw e;
		}
		
	}

	@Override
	public int payForBook(int orderId) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="update bookorder set order_status=1 where order_id=?";
			prs=con.prepareStatement(sql);
			prs.setInt(1, orderId);
			int result = prs.executeUpdate();
			return result;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("支付异常");
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> getOrder(int userId) throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList();
		try{
			sql="select * from bookorder where user_id=?";
			prs=con.prepareStatement(sql);
			prs.setInt(1, userId);
			rs= prs.executeQuery();
			while(rs.next()){
				Map<String,Object> order = new HashMap();
				order.put("orderId", rs.getString("order_id"));
				order.put("bookName", rs.getString("book_name"));
				order.put("orderStatus", rs.getString("order_status"));
				list.add(order);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("获得账单异常");
			throw e;
		}
	}

}
