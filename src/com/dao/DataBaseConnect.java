package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
	public static final String DB_URL="jdbc:mysql://localhost:3306/onlinebookshop?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
	public static final String DB_DRIVER="com.mysql.jdbc.Driver";
	public static final String DB_ROOT="root";
	public static final String DB_PASSWORD="gpy1";
	private Connection con;
	public DataBaseConnect(){
		try {
			Class.forName(DB_DRIVER);
			con=DriverManager.getConnection(DB_URL, DB_ROOT, DB_PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库连接异常");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
				System.out.println("数据库驱动异常，未导入数据包");
				e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return con;
	}
}
