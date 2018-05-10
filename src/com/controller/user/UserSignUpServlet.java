package com.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;
import com.service.Impl.UserServiceImpl;

/**
 * 用户注册           /user/UserSignUpServlet
 * @param  String userName 用户名
 * @param  String password 用户密码
 * @return
 * {"msg":"注册成功","code":1,"data":1} 
 *
 */
public class UserSignUpServlet extends HttpServlet {
	
	private UserService user;
	public UserSignUpServlet(){
		user = new UserServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		try {
			pw.println(user.signUp(req, resp));
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
