package com.service.Impl;

import java.io.UnsupportedEncodingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.mapper.BookMapper;
import com.modle.User;
import com.service.BookService;
import com.util.Common;
import com.util.EnumUtil;
import com.util.FileUploadTools;

import net.sf.json.JSONObject;

public class BookServiceImpl implements BookService{

	private BookDao bookDao;
	public BookServiceImpl(){
		bookDao=new BookMapper();
	}



	@Override
	public JSONObject addBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}else if(user.getUserStatus() !=2){
			return new Common().constractResponse(new EnumUtil().FAILED, "没有管理员权限", null);
		}
		String bookName = null;
		String bookClass = null;
		String bookPublishing = null;
		String bookPrice = null;
		String bookIntroduction = null;
		String bookURL=null;
		String name=null;
		FileUploadTools fileUploadTools = null;
		List<String> photo = null;
		try {
			fileUploadTools= new FileUploadTools(req,500*1024*1024, "D:/java学习/Bookshops/WebContent/图书售卖网站/bookImages/");
			name= fileUploadTools.getParameter("name");
			photo =fileUploadTools.saveAll("D:/java学习/Bookshops/WebContent/图书售卖网站/bookImages/");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			System.out.println(1);
		}

		try{
			bookName=fileUploadTools.getParameter("bookName");
			bookClass=fileUploadTools.getParameter("bookClass");
			bookPublishing=fileUploadTools.getParameter("bookPublishinghouse");
			bookPrice=fileUploadTools.getParameter("bookPrice");
			bookIntroduction=fileUploadTools.getParameter("bookIntroduction");
			bookURL="http://localhost:8080/Bookshops/图书售卖网站/bookImages/"+photo.get(0);
//			System.out.println(bookName+bookClass+bookPublishing+bookPrice+bookIntroduction+bookURL);
		if(bookName==null||"".equals(bookName)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookName参数出错", null);
		}else if(bookClass==null||"".equals(bookClass)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookClass参数出错", null);
		}else if(bookPublishing==null||"".equals(bookPublishing)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookPublishing参数出错", null);
		}else if(bookPrice==null||"".equals(bookPrice)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookPrice参数出错", null);
		}else if(bookIntroduction==null||"".equals(bookIntroduction)){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookIntroduction参数出错", null);
		}else if(photo==null||photo.isEmpty()){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "上传失败", null);
		}
	}catch(Exception e){
		return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		try{
			int result = bookDao.addBook(bookName, bookClass, bookPublishing, Double.parseDouble(bookPrice),bookURL , bookIntroduction);
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "添加成功", result);
			}else{
				return new Common().constractResponse(new EnumUtil().FAILED, "添加失败", result);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
	}

	@Override
	public JSONObject deleteBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}else if(user.getUserStatus() !=2){
			return new Common().constractResponse(new EnumUtil().FAILED, "没有管理员权限", null);
		}
		String bookId=null;
		try{
			bookId=req.getParameter("bookId");
			if(bookId==null){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookId参数出错", null);
			}
		}catch(Exception e){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		try{
			int result=bookDao.deletBook(Integer.parseInt(bookId));
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "删除成功", result);
			}else if(result==0){
				return new Common().constractResponse(new EnumUtil().FAILED, "删除失败", null);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

	@Override
	public JSONObject upDateBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return new Common().constractResponse(new EnumUtil().NO_LOGIN, "用户还未登录", null);
		}else if(user.getUserStatus() !=2){
			return new Common().constractResponse(new EnumUtil().FAILED, "没有管理员权限", null);
		}
		String bookId=null;
		String bookName=null;
		String bookClass=null;
		String bookPrice=null;
		String bookIntroduction=null;
		FileUploadTools fileUploadTools = null;
		List<String> photo = null;
		try {
			fileUploadTools= new FileUploadTools(req,500*1024*1024, "D:/java学习/Bookshops/WebContent/图书售卖网站/bookImages/");
			photo =fileUploadTools.saveAll("D:/java学习/Bookshops/WebContent/图书售卖网站/bookImages/");	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try{
			bookId=fileUploadTools.getParameter("bookId");
			bookName=fileUploadTools.getParameter("bookName");
			bookClass=fileUploadTools.getParameter("bookClass");
			bookPrice=fileUploadTools.getParameter("bookPrice");
			String temp =fileUploadTools.getParameter("bookIntroduction");
			bookIntroduction= new String(temp.getBytes("ISO-8859-1"),"UTF-8");//处理前端传过来的中文乱码
			if(bookId==null||"".equals(bookId)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookId参数出错", null);
			}else if(bookName==null||"".equals(bookName)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookName参数出错", null);
			}else if(bookClass==null||"".equals(bookClass)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookClass参数出错", null);
			}else if(bookPrice==null||"".equals(bookPrice)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookPrice参数出错", null);
			}else if(bookIntroduction==null||"".equals(bookIntroduction)){
				return new Common().constractResponse(new EnumUtil().DATA_ERROR, "bookIntroduction参数出错", null);
			}
		}catch(Exception e){
			return new Common().constractResponse(new EnumUtil().DATA_ERROR, "无参数匹配", null);
		}
		try{
			int result=bookDao.upDateBook(bookId, bookName, bookClass, bookPrice, bookIntroduction);
			if(result==1){
				return new Common().constractResponse(new EnumUtil().OK, "图书修改成功", result);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
		return null;
	}

	@Override
	public JSONObject getAllBookMsg(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		List<Map<String, Object>>list=new ArrayList();
		try{
			list=bookDao.getAllBookMsg();
			if(list==null||list.size()<=0){
				return new Common().constractResponse(new EnumUtil().FAILED, "无数据", null);
			}else{
				return new Common().constractResponse(new EnumUtil().OK, "显示全部图书", list);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}

	}

	@Override
	public JSONObject getSomeBookMsg(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("utf-8");
		List<Map<String, Object>>list=new ArrayList();
		try{
			list=bookDao.getSomeBookMsg();
			if(list==null||list.size()<=0){
				return new Common().constractResponse(new EnumUtil().FAILED, "无数据", null);
			}else{
				return new Common().constractResponse(new EnumUtil().OK, "显示推荐图书", list);
			}
		}catch(SQLException e){
			return new Common().constractResponse(new EnumUtil().DB_ERROR, "数据库系统出错", null);
		}
	}


}
