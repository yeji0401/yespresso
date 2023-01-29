package com.sh.yespresso.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcTemplate {
	
	private static String driverClass;
	private static String url; // 접속프로토콜@url:port:sid
	private static String user;
	private static String password;
	
	static {
		final String datasourceConfigPath = JdbcTemplate.class.getResource("/datasource.properties").getPath();
		System.out.println(datasourceConfigPath);
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(datasourceConfigPath));
			driverClass = prop.getProperty("driverClass");
			url =  prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		try {
			//1. driverClass 등록 : 프로그램 실행 시 최초 1회만 처리
			Class<?> driverClassInstance = Class.forName(driverClass); 
			System.out.println(driverClassInstance);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			System.out.println("2. conn 생성 성공");

		} catch (Exception e) {
			System.out.println("2. conn 실패");

			e.printStackTrace();
		}
		return conn;
	}

	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}