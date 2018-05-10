package com.service.Impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.mapper.UserMapper;
import com.modle.User;
import com.service.UserService;
import com.util.Common;
import com.util.EnumUtil;

import net.sf.json.JSONObject;

public class UserServiceImpl implements UserService {
	
	private UserDao user;
	public UserServiceImpl(){
		user = new UserMapper();
	}
	@Override
	public JSONObject signUp(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stu
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userName=null;
		String password=null;
		try{	
			userName=req.getParameter("userName");
			password=req.getParameter("password");
			if(userName==null||"".equals(userName)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "userName参数出错", null);
			}else if(password==null||"".equals(password)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "password参数出错", null);
			}
		} catch (Exception e){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		int result = user.userCheck(userName);
		if(result==0){
			return new Common().constractResponse(new EnumUtil().DATA_REPEAT, "userName参数已存在", null);
		}
		try{
			result = user.signUp(userName, password);
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "注册成功", result);
			}else if(result==0){
				return new Common().constractResponse(new EnumUtil().FAILED, "注册失败", result);
			}
		} catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

	@Override
	public JSONObject login(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userName=null;
		String password=null;
		try{	
			userName=req.getParameter("userName");
			password=req.getParameter("password");
			if(userName==null||"".equals(userName)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "userName参数出错", null);
			}else if(password==null||"".equals(password)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "password参数出错", null);
			}
		} catch (Exception e){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		try{
			User result=user.login(userName, password);
			if(result!=null){
				req.getSession().setAttribute("user", result);
				return new Common().constractResponse(new EnumUtil().OK, "登录成功", result);
			}else if(result==null){
				return new Common().constractResponse(new EnumUtil().FAILED, "登录失败", null);
			}
		} catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

	@Override
	public JSONObject userMsg(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User users=null;
		users=(User) req.getSession().getAttribute("user");
		if(users==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户没登录", null);
		}
		try{
			List<Map<String,Object>> list=new ArrayList();
			list=user.userMsg(users.getUserID());
			if(list==null||list.size()<=0){
				return new Common().constractResponse(new EnumUtil().DATA_EMPTY, "无数据", null);
			}else{
				return new Common().constractResponse(new EnumUtil().OK, "查询成功", list);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}	

	}

	@Override
	public JSONObject upDate(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userName=null;
		String password=null;
		try{	
			userName=req.getParameter("userName");
			password=req.getParameter("password");
			if(userName==null||"".equals(userName)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "userName参数出错", null);
			}else if(password==null||"".equals(password)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "password参数出错", null);
			}
		} catch (Exception e){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		try{
			User users=(User) req.getSession().getAttribute("user");
			int result=user.upDate(users.getUserID(), userName, password);
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "修改成功", result);
			}else if(result==0){
				return new Common().constractResponse(new EnumUtil().FAILED, "修改失败", result);
			}
		} catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

}
