package com.text;

import java.sql.SQLException;

import org.junit.Test;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.mapper.BookMapper;
import com.mapper.OrderMapper;
import com.mapper.UserMapper;
import com.modle.Order;

public class MapperText {
	private UserDao user;
	private BookDao book;
	private OrderDao order;
	public MapperText(){
		user = new UserMapper();
		book = new BookMapper();
		order = new OrderMapper();
	}
	@Test
	public void textUser() throws SQLException{
	//	System.out.println(user.login("text2", "text2"));
	//	System.out.println(user.signUp("text6", "text6"));
		System.out.println(user.userMsg(1));
		System.out.println(user.upDate(9, "12", "12"));
	}
	@Test
	public void textBook() throws SQLException{
		//System.out.println(book.deletBook(13));
		//System.out.println(book.upDateBook(14, "1", "1", "1", 1, "1"));
		System.out.println(book.getAllBookMsg());
		//System.out.println(book.getSomeBookMsg());
	}
	@Test
	public void textOrder() throws SQLException{
		Order order2 = new Order();
		order2.setBookId(15);
		order2.setBookName("1");
		order2.setUserId(2);
	//	System.out.println(order.buyBook(order2));
	//	System.out.println(order.payForBook(21));
		System.out.println(order.getOrder(2));
	}
}
