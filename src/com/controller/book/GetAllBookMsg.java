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
 * 获得所有图书      /book/GetAllBookMsg
 * @author LENOVO
 * @return
 * {"msg":"显示全部图书","code":1,"data":[{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/booklmages/computer_introduction.jpg","bookIntroduction":"本书是大学计算机相关专业的基础课教材，涉及计算机科学的各个方面。本书着重讲解基本概念而不是数学模型和技术细节，通过大量的图表和演示范例讲解计算机科学的基础知识。","bookPublishing":"机械工业出版社","bookPrice":18,"bookName":"计算机科学导论","bookClass":"计算机","bookId":1},{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/bookImages/data_structure.jpg","bookIntroduction":"《数据结构》（C语言版）是为“数据结构”课程编写的教材，也可作为学习数据结构及其算法的C程序设计的参数教材。本书的前半部分从抽象数据类型的角度讨论各种基本类型的数据结构及其应用。","bookPublishing":"清华大学出版社","bookPrice":22,"bookName":"数据结构","bookClass":"数据","bookId":2},{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/bookImages/C_program.jpg","bookIntroduction":"（1）基于C11标准，涵盖精准语法、高级特性、主流C语言编译器支持，配合大量示例与讲解（How-Why），掌握C魔法精髓。（2）C语言与汇编语言重度用户与拥趸者撰写，10余年开发经验结晶，讲究透彻而实用，字字珠玑。","bookPublishing":"机械工业出版社","bookPrice":25.5,"bookName":"C语言编程","bookClass":"编程","bookId":3},{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/bookImages/operation_system.jpg","bookIntroduction":"操作系统是计算机系统的核心系统软件，负责控制和管理整个系统，使之协调工作。本书不仅全面地讲述了操作系统的基本概念、原理和方法，还清楚地展现了当代操作系统的本质和特点。","bookPublishing":"机械工业出版社","bookPrice":49.8,"bookName":"操作系统","bookClass":"计算机","bookId":4},{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/bookImages/JAVA.jpg","bookIntroduction":"《Java从入门到精通（第4版）》从初学者角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用Java语言进行程序开发需要掌握的知识。","bookPublishing":"机械工业出版社","bookPrice":21.6,"bookName":"JAVA从入门到精通","bookClass":"编程","bookId":5},{"bookImg":"http://localhost:8080/bookshop/图书售卖网站/bookImages/computer_culture.jpg","bookIntroduction":"（美）帕森斯、奥贾编著的这本《计算机文化(原 书第15版)》采用*的方法和技术讲述计算机基 础知识，涉及面之广、内容之丰富、方法之独特，令 人叹为观止，堪称计算机基础知识的百科全书。","bookPublishing":"机械工业出版社","bookPrice":20,"bookName":"计算机文化","bookClass":"计算机","bookId":6},{"bookImg":"2","bookIntroduction":"该书暂时没有介绍信息2","bookPublishing":"2","bookPrice":2,"bookName":"2","bookClass":"2","bookId":7}]}
 */
public class GetAllBookMsg extends HttpServlet{
	private BookService book;
	public GetAllBookMsg(){
		book=new BookServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.addHeader("Content-type", "text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		try {
			pw.println(book.getAllBookMsg(req, resp));
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
