package com.sh.yespresso.product.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.yespresso.product.model.dto.Aroma;
import com.sh.yespresso.product.model.dto.CupSize;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.ProductCategory;
import com.sh.yespresso.product.model.dto.Type;
import com.sh.yespresso.product.model.exception.ProductException;

public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
		
		String path = ProductDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("[query load 완료!] " + prop);
	}
	

	/**
	 * hj start
	 */
	private Product handleProductResultSet(ResultSet rset) throws SQLException{
		Product product = new Product();
		product.setProductNo(rset.getString("PRODUCT_NO"));
		product.setProductCategory(ProductCategory.valueOf(rset.getString("FK_CATEGORY_ID")));
		product.setProductName(rset.getString("PRODUCT_NAME"));
		product.setProductPrice(rset.getInt("PRODUCT_PRICE"));
		product.setProductStock(rset.getInt("PRODUCT_STOCK"));
		product.setProductSaleCnt(rset.getInt("PRODUCT_SALECNT"));
		product.setProductDate(rset.getDate("PRODUCT_DATE"));
		product.setThumbnailFilename(rset.getString("THUMBNAIL_FILENAME"));
		product.setType(Type.valueOf(rset.getString("TYPE")));
		product.setAroma(Aroma.valueOf(rset.getString("AROMA")));
		product.setAcidity(rset.getInt("ACIDITY"));
		product.setRoasting(rset.getInt("ROASTING"));
		product.setCupSize(CupSize.valueOf(rset.getString("CUP_SIZE")));
		
		return product;
	}
	
	public List<Product> selectAllProduct(Connection conn, String categoryID) {
		String sql = prop.getProperty("selectAllProduct"); 
		List<Product> productList = new ArrayList<>();
		
		// 1. PreparedStatement 객체 생성 & 미완성쿼리 값대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, categoryID); //select * from PRODUCT where FK_CATEGORY_ID = ?
				
			// 2. 실행 & ResultSet 반환
			try(ResultSet rset = pstmt.executeQuery()){
				
				// 3. ResultSet -> List<Product>
				while(rset.next()) {
					Product product = handleProductResultSet(rset);
					productList.add(product);
				}
					
			}
			
		} catch (SQLException e) {
			throw new ProductException("제품 목록 조회 오류!", e);
		}
		return productList;
	}
	
	/**
	 * hj end
	 */


	/**
	 * yeji start
	 */
	/**
	 * yeji end
	 */


	/**
	 * awon start
	 */
	/**
	 * awon end
	 */


	/**
	 * jooh start
	 */
	/**
	 * jooh end
	 */

}
