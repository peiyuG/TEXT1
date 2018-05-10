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
 * 用户登录                   /user/UserLoginServlet
 * @param   String userName    用户名
 * @param   String passwrod    用户密码
 * @return
 * {"msg":"登录成功","code":1,"data":{"userPassword":"text2","userStatus":1,"userName":"text2","userId":4}}
 *
 */
public class UserLoginServlet extends HttpServlet{
	
	private UserService user;
	public UserLoginServlet (){
		user=new UserServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			PrintWriter pw=resp.getWriter();
			pw.println(user.login(req, resp));
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
