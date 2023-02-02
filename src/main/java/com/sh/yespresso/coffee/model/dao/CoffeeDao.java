package com.sh.yespresso.coffee.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.ProductEntity;
import com.sh.yespresso.product.model.exception.ProductException;



public class CoffeeDao {
	private Properties prop = new Properties();
	
	public CoffeeDao() {
		String path = CoffeeDao.class.getResource("/sql/coffee/coffee-query.properties").getPath();
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[query load 완료!]" + prop);
	}


	public int selectResult (Connection conn, Product product ) {
		String sql = prop.getProperty("selectResult"); 
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, product.getType().name());
			pstmt.setString(2, product.getAroma().name());
			pstmt.setInt(3, product.getAcidity());
			pstmt.setInt(4, product.getRoasting());
			pstmt.setString(5, product.getCupSize().name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ProductException("결과 오류", e);
		}
		return result;
	}

}