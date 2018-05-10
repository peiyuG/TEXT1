package com.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.BookDao;
import com.dao.DataBaseConnect;
import com.modle.Book;

public class BookMapper implements BookDao{
	private Connection con=null;
	private PreparedStatement prs;
	private ResultSet rs;
	private String sql;
	
	public BookMapper(){
		con=new DataBaseConnect().getConnection();
	}
	@Override
	public int addBook(String bookName, String bookClass, String bookPublishing, double price, String bookImg,
			String introduction) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="insert into book(book_name,book_class,book_publishinghouse,book_price,book_imgurl,book_introduction) values"
					+"(?,?,?,?,?,?)";
			prs=con.prepareStatement(sql);
			prs.setString(1, bookName);
			prs.setString(2, bookClass);
			prs.setString(3, bookPublishing);
			prs.setDouble(4, price);
			prs.setString(5, bookImg);
			prs.setString(6, introduction);
			int result=prs.executeUpdate();
			if(result==1){
				return result;
			}
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("添加图书异常");
			throw e;
		}
		return 0;
	}

	@Override
	public int upDateBook(String bookId,String bookName, String bookClass, String price, String introduction)
			throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="update book set book_name=?,book_class=?,book_price=?,book_introduction=? where book_id=?";
			prs=con.prepareStatement(sql);
			prs.setString(1, bookName);
			prs.setString(2, bookClass);
			prs.setDouble(3, Double.parseDouble(price));
			prs.setString(4, introduction);
			prs.setInt(5, Integer.parseInt(bookId));
			int result=prs.executeUpdate();
			if(result==1){
				return result;
			}
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("修改图书信息异常");
			throw e;
		}
		return 0;
	}

	@Override
	public int deletBook(int bookId) throws SQLException {
		// TODO Auto-generated method stub
		try{
			sql="delete from book where book_id=?";
			prs=con.prepareStatement(sql);
			prs.setInt(1, bookId);
			int result = prs.executeUpdate();
			if(result==1){
				return result;
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("删除图书异常");
			throw e;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getAllBookMsg() throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=new ArrayList();
		try{
			sql="select * from book";
			prs=con.prepareStatement(sql);
			rs=prs.executeQuery();
			while(rs.next()){
				Map<String,Object> book=new HashMap();
				book.put("bookId", rs.getInt("book_id"));
				book.put("bookName", rs.getString("book_name"));
				book.put("bookClass", rs.getString("book_class"));
				book.put("bookPublishing", rs.getString("book_publishinghouse"));
				book.put("bookPrice", rs.getDouble("book_price"));
				book.put("bookImg", rs.getString("book_imgurl"));
				book.put("bookIntroduction", rs.getString("book_introduction"));
				list.add(book);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("获取图书信息异常");
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> getSomeBookMsg() throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=new ArrayList();
		try{
			sql="select * from book order by book_id desc limit 4";
			prs=con.prepareStatement(sql);
			rs=prs.executeQuery();
			while(rs.next()){
				Map<String,Object> book=new HashMap();
				book.put("bookId", rs.getInt("book_id"));
				book.put("bookName", rs.getString("book_name"));
				book.put("bookClass", rs.getString("book_class"));
				book.put("bookPublishing", rs.getString("book_publishinghouse"));
				book.put("bookPrice", rs.getDouble("book_price"));
				book.put("bookImg", rs.getString("book_imgurl"));
				book.put("bookIntroduction", rs.getString("book_introduction"));
				list.add(book);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("获取图书信息异常");
		}
		return null;
	}

}
