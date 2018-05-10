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
 * 通过用户Id查订单          /order/getOrder
 *@return 
 *{"msg":"成功查询数据","code":1,"data":[{"orderId":"1","orderStatus":"1","bookName":"text"},{"orderId":"2","orderStatus":"1","bookName":"text1"},{"orderId":"3","orderStatus":"1","bookName":"计算机文化"},{"orderId":"4","orderStatus":"1","bookName":"计算机文化"},{"orderId":"5","orderStatus":"1","bookName":"计算机文化"},{"orderId":"6","orderStatus":"1","bookName":"计算机科学导论"},{"orderId":"7","orderStatus":"1","bookName":"计算机科学导论"},{"orderId":"8","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"9","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"10","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"11","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"12","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"13","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"14","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"15","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"16","orderStatus":"0","bookName":"计算机科学导论"},{"orderId":"17","orderStatus":"1","bookName":"计算机科学导论"},{"orderId":"19","orderStatus":"1","bookName":"1"}]}
 */
public class GetUserOrderServlet extends HttpServlet {
	private OrderService order;
	public GetUserOrderServlet(){
		order = new OrderServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		try {
			pw.println(order.getOrder(req, resp));
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
