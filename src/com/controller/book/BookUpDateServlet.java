package com.controller.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BookService;
import com.service.Impl.BookServiceImpl;
/**
 * 修改图书信息           /user/UserUpData
 * @param bookId    图书id
 * @param bookName  图书名称
 * @param bookClass 图书分类
 * @param bookPrice     图书价钱
 * @param bookIntroduction   图书简介
 * @return
 * {"msg":"图书修改成功","code":1,"data":1}
 */
public class BookUpDateServlet extends HttpServlet{
	private BookService book;
	public BookUpDateServlet(){
		book=new BookServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		try {
			pw.println(book.upDateBook(req, resp));
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
