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
 * 获得4本推荐图书       /book/GetSomeBookMsg
 *@return
 *{"msg":"显示推荐图书","code":1,"data":[{"bookImg":"3","bookIntroduction":"1","bookPublishing":"1","bookPrice":1,"bookName":"1","bookClass":"1","bookId":14},{"bookImg":"http://localhost:8080/bookshop/jsp图书售卖网站/bookImages/computer_culture.jpg","bookIntroduction":"（美）帕森斯、奥贾编著的这本《计算机文化(原 书第15版)》采用*的方法和技术讲述计算机基 础知识，涉及面之广、内容之丰富、方法之独特，令 人叹为观止，堪称计算机基础知识的百科全书。","bookPublishing":"机械工业出版社","bookPrice":20,"bookName":"计算机文化","bookClass":"计算机","bookId":6},{"bookImg":"http://localhost:8080/bookshop/jsp图书售卖网站/bookImages/JAVA.jpg","bookIntroduction":"《Java从入门到精通（第4版）》从初学者角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用Java语言进行程序开发需要掌握的知识。","bookPublishing":"机械工业出版社","bookPrice":21.6,"bookName":"JAVA从入门到精通","bookClass":"编程","bookId":5},{"bookImg":"http://localhost:8080/bookshop/jsp图书售卖网站/bookImages/operation_system.jpg","bookIntroduction":"操作系统是计算机系统的核心系统软件，负责控制和管理整个系统，使之协调工作。本书不仅全面地讲述了操作系统的基本概念、原理和方法，还清楚地展现了当代操作系统的本质和特点。","bookPublishing":"机械工业出版社","bookPrice":49.8,"bookName":"操作系统","bookClass":"计算机","bookId":4}
 */
public class GetSomeBookMsg extends HttpServlet{
	private BookService book;
	public GetSomeBookMsg(){
		book=new BookServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter pw=resp.getWriter();
		try {
			pw.println(book.getSomeBookMsg(req, resp));
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
