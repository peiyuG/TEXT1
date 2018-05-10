package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.modle.Book;

public interface BookDao {
	
/**
 * 图书添加
 * @param bookName
 * @param bookClass
 * @param bookPublishing
 * @param price
 * @param bookImg
 * @param introduction
 * @return
 * @throws SQLException
 */
	public int addBook(String bookName,String bookClass,String bookPublishing,double price,String bookImg,String introduction) throws SQLException;

	/**
	 * 图书信息修改
	 * @param bookId
	 * @param bookName
	 * @param bookClass
	 * @param price
	 * @param introduction
	 * @return
	 * @throws SQLException
	 */
	public int upDateBook(String bookId,String bookName,String bookClass,String price,String introduction) throws SQLException;
	/**
	 * 图书删除
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public int deletBook(int bookId) throws SQLException;
	
	/**
	 * 获取部分信息
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getSomeBookMsg() throws SQLException;
	
	/**
	 * 获取所有图书信息
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getAllBookMsg() throws SQLException;
}
