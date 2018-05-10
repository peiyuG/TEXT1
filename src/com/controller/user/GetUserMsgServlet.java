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
 * 通过ID查询获取用户信息             /user/UserMsg
 * @param   userName  用户名
 * @param   password  用户密码
 * @return 
 * {"msg":"查询成功","code":1,"data":[{"password":"text2","userStatus":1,"userName":"text2","userID":4}]} 
 *
 */
public class GetUserMsgServlet extends HttpServlet{
	
	private UserService user;
	public GetUserMsgServlet(){
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
			pw.println(user.userMsg(req, resp));
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
