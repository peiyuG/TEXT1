package com.service.Impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.mapper.OrderMapper;
import com.modle.Order;
import com.modle.User;
import com.service.OrderService;
import com.util.Common;
import com.util.EnumUtil;

import net.sf.json.JSONObject;

public class OrderServiceImpl implements OrderService{
	private OrderDao orderdao;
	private Order order;
	public OrderServiceImpl(){
		orderdao = new OrderMapper();
		order = new Order();
	}
	@Override
	public JSONObject buyBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}
		String bookId = req.getParameter("bookId");
		String bookName = req.getParameter("bookName");
		int userId = user.getUserID();
		if(bookId==null||"".equals(bookId)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookId参数异常", null);
		}else if(bookName==null||"".equals(bookName)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookName参数异常", null);
		}
		order.setBookId(Integer.parseInt(bookId));
		order.setBookName(bookName);
		order.setUserId(userId);
		try{
			int result=orderdao.buyBook(order);
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "添加账单成功", result);
			}else {
				return new Common().constractResponse(new EnumUtil().FAILED, "添加失败", result);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}

	}

	@Override
	public JSONObject payForBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}
		try{
			String orderId=req.getParameter("orderId");
			int result=orderdao.payForBook(Integer.parseInt(orderId));
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "支付成功", result);
			}else if(result==0){
				return new Common().constractResponse(new EnumUtil().FAILED, "支付失败", result);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

	@Override
	public JSONObject getOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");	
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}
		try{
			List<Map<String,Object>>list = new ArrayList();
			list = orderdao.getOrder(user.getUserID());
			if(list==null||list.size()<0){
				return new Common().constractResponse(new EnumUtil().FAILED, "无订单数据", null);
			}else {
				return new Common().constractResponse(new EnumUtil().OK, "成功查询数据", list);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
	}

}
