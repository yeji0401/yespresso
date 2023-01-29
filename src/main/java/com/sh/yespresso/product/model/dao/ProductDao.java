package com.sh.yespresso.product.model.dao;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.exception.MemberException;
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
	public List<Product> selectAllProduct(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectAllProduct"); // select * from (select row_number() over(order by product_date desc) rnum, p.* from PRODUCT p) where rnum between ? and ?
		List<Product> products = new ArrayList<>();
		int page = (int) param.get("page");
		int limit = (int) param.get("limit");
		int start = (page - 1) * limit + 1; 
		int end = page * limit;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			try(ResultSet rset = pstmt.executeQuery();){
				
				while(rset.next()) {
					Product product = handleProductResultSet(rset);
					products.add(product);
				}
			}
			
			
		} catch (SQLException e) {
			throw new ProductException("관리자 제품 목록 조회 오류!", e);
		}
				
		return products;
	}
	
	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount"); // select COUNT(*) from PRODUCT
		int totalCount = 0;
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();	
		){
			while(rset.next())
				totalCount = rset.getInt(1);
	
		} catch (SQLException e) {
			throw new ProductException("전체 제품 수 조회 오류", e);
		}	
		
		return totalCount;
	}
	
	public List<Product> searchProduct(Connection conn, Map<String, String> param) {
		List<Product> products = new ArrayList<>();
		String searchType = param.get("searchType"); // product_name
		String searchKeyword = param.get("searchKeyword");
		String sql = prop.getProperty("searchProduct"); // select * from PRODUCT where PRODUCT_NAME like ?
		System.out.println(sql);
		
		// 1. PreaparedStatement 객체 생성 & 미완성 쿼리 값대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%" + searchKeyword + "%"); 
			// 2. 실행 & ResultSet 반환
			try(ResultSet rset = pstmt.executeQuery()){				
				// 3. ResultSet -> List<Product>
				while(rset.next())
					products.add(handleProductResultSet(rset));
			}
		} catch (SQLException e) {
			throw new MemberException("관리자 회원 검색 오류", e);
		}
		
		return products;
	}
	
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
