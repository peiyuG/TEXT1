package com.controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.OrderService;
import com.service.Impl.OrderServiceImpl;


/**
 * 支付                                /order/PayForOrder
 * @author LENOVO
 * @return 
 * {"msg":"支付成功","code":1,"data":1}
 */
public class PayForBookServlet extends HttpServlet{
	private OrderService order;
	public PayForBookServlet(){
		order = new OrderServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		try {
			pw.println(order.payForBook(req, resp));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
