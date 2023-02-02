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
import com.sh.yespresso.product.model.dto.ProductDetail;
import com.sh.yespresso.product.model.dto.Type;
import com.sh.yespresso.product.model.exception.ProductException;

public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
													// build path
		String path = ProductDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("[query load 완료!] " + prop);
	}
	

	/**
	 * **********************   hj start   **********************
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
		
		String aroma = rset.getString("AROMA");
		int acidity = rset.getInt("ACIDITY");
		int roasting = rset.getInt("ROASTING");
		String cupSize = rset.getString("CUP_SIZE");

		if(aroma != null) {
			product.setAroma(Aroma.valueOf(rset.getString("AROMA")));	
		} else {
			product.setAroma(Aroma.valueOf("none")); // enum - null인경우 none으로 처리
		}
		
		if(acidity == 0) { // NUM null일때는 0으로 받아짐
			product.setAcidity(0);
		} else {
			product.setAcidity(rset.getInt("ACIDITY"));
		}

		if(roasting == 0) { // NUM null일때는 0으로 받아짐
			product.setRoasting(0);
		} else {
			product.setRoasting(rset.getInt("ROASTING"));			
		}
		
		if(cupSize != null) {
			product.setCupSize(CupSize.valueOf(rset.getString("CUP_SIZE")));	
		} else {
			product.setCupSize(CupSize.valueOf("none")); // enum - null인경우 none으로 처리
		}
		
		return product;
	}
	
	/* DQL 페이지에 해당하는 카테고리별 제품 리스트 불러오기 */
	// 1. 커피 제품 리스트
	public List<Product> selectCoffeeList(Connection conn, Map<String, Integer> param) {
		String sql = prop.getProperty("selectCoffeeList");
		List<Product> coffeeList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Product product = handleProductResultSet(rset);
					coffeeList.add(product);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("커피페이지 조회 오류!", e);
		}
		return coffeeList;
	}

	// 2. 머신 제품 리스트
	public List<Product> selectMachineList(Connection conn, Map<String, Integer> param) {
		String sql = prop.getProperty("selectMachineList");
		List<Product> machineList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Product product = handleProductResultSet(rset);
					machineList.add(product);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("머신페이지 조회 오류!", e);
		}
		return machineList;
	}

	// 3. 악세사리 제품 리스트 
	public List<Product> selectAccList(Connection conn, Map<String, Integer> param) {
		String sql = prop.getProperty("selectAccList");
		List<Product> accList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));


			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Product product = handleProductResultSet(rset);
					accList.add(product);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("악세페이지 조회 오류!", e);
		}
		return accList;
	}

	
	// DQL categoryId로 카테고리별 제품수 구하기
	public int getTotalCntById(Connection conn, String categoryId) {
		String sql = prop.getProperty("getTotalCntById");
		int totalCount = 0;
		String msg = "";
		
		if("CO".equals(categoryId)) {
			msg = "커피 제품 수 조회 오류!";
		} 
		else if ("MA".equals(categoryId)) {
			msg = "머신 제품 수 조회 오류!";
		}
		else {
			msg = "악세사리 제품 수 조회 오류!";
		}
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			 pstmt.setString(1, categoryId);
			 
			 try(ResultSet rset = pstmt.executeQuery()){
				 if(rset.next()) {
					totalCount = rset.getInt(1);
					}
			 	}
			} catch (Exception e) {
				throw new ProductException(msg, e);
			}
			return totalCount;
		}

	// DQL product_no로 제품 하나 가져오기
	public Product selectOneProduct(Connection conn, String prodNo) {
		String sql = prop.getProperty("selectOneProduct");
		Product product = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, prodNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					product = handleProductResultSet(rset);
				}
			}
		} catch (Exception e) {
			throw new ProductException("제품 한개 조회 오류!", e);
		}
		
		return product;
	}

	// DQL product_no로 첨부파일 가져오기 
	public List<ProductDetail> selectDetailByProductNo(Connection conn, String prodNo) {
		String sql = prop.getProperty("selectDetailByProductNo");
		List<ProductDetail> attachments = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, prodNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					ProductDetail detail = new ProductDetail();
					detail.setDetailsNo(rset.getInt("DETAILS_NO"));
					detail.setProductAttachmentNo(rset.getString("PRODUCT_ATTACHMENT_NO"));
					detail.setProductFilename(rset.getString("PRODUCT_FILENAME"));
					detail.setReProductFilename(rset.getString("RE_PRODUCT_FILENAME"));
					detail.setProductFileDate(rset.getDate("PRODUCT_FILE_DATE"));
					attachments.add(detail);
				}
			}
		} catch (Exception e) {
			throw new ProductException("제품 한개 조회 오류!", e);
		}
		
		return attachments;
	}




	
	/**
	 * *********************   hj end   **********************
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
	
	public int insertProduct(Connection conn, Product product) {
		
		// insert into PRODUCT values 
		// ('p' || lpad(seq_product_no.nextval, 4, '0'), ?, ?, ?, ?, default, default, 'p' || lpad(seq_product_no.nextval, 4, '0') || '.png', ?, ?, ?, ?, ?)
		String sql = prop.getProperty("insertProduct"); 
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductCategory().name());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setInt(4, product.getProductStock());
			pstmt.setString(5, product.getType().name());
			pstmt.setString(6, product.getAroma().name());
			pstmt.setInt(7, product.getAcidity());
			pstmt.setInt(8, product.getRoasting());
			pstmt.setString(9, product.getCupSize().name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ProductException("제품 등록 오류", e);
		}
		return result;
	}
	
	public Product selectProduct(Connection conn, String productNo) {
		String sql = prop.getProperty("selectOneProduct");
		Product product = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, productNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					product = handleProductResultSet(rset);
				}
			}
		} catch (Exception e) {
			throw new ProductException("제품 한 개 조회 오류!", e);
		}
		
		return product;
	}
	
	public int updateProduct(Connection conn, Product product) {
		
		// update PRODUCT set FK_CATEGORY_ID = ?, PRODUCT_NAME = ?, PRODUCT_PRICE = ?, PRODUCT_STOCK = ?, TYPE = ?, AROMA = ?, ACIDITY = ?, ROASTING = ?, CUP_SIZE = ? WHERE PRODUCT_NO = ?
		String sql = prop.getProperty("updateProduct"); 
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductCategory().name());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setInt(4, product.getProductStock());
			pstmt.setString(5, product.getType().name());
			pstmt.setString(6, product.getAroma().name());
			pstmt.setInt(7, product.getAcidity());
			pstmt.setInt(8, product.getRoasting());
			pstmt.setString(9, product.getCupSize().name());
			pstmt.setString(10, product.getProductNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ProductException("제품 등록 오류", e);
		}
		return result;
	}
	
	public int deleteProduct(Connection conn, String productNo) {
		
		String sql = prop.getProperty("deleteProduct"); // delete from PRODUCT where PRODUCT_NO = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, productNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ProductException("제품 삭제 오류", e);
		}
		return result;
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
	public List<Product> selectResult (Connection conn, Product product ) {
		String sql = prop.getProperty("selectResult"); 
		int result = 0;
		//ResultSet rset =  null;
		List<Product> list = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

//select * from product where type = ? and aroma = ? and  acidity in ? and roasting in ? cup_size = ?
			// and acidity in ? and roasting in ?
			
			pstmt.setObject(1,  product.getType().toString());
			pstmt.setObject(2,  product.getAroma().toString());
			pstmt.setObject(3, product.getCupSize().toString());
			//String query = "";
			if(product.getAcidity() == 1) {
				sql += " and acidity in (1, 2)";
			}else {
				sql += " and acidity in (3, 4, 5)";
			}
			if(product.getRoasting() == 1) {
				sql += " and roasting in (1, 2, 3, 4, 5, 6)";
			}else {
				sql += " and roasting in (7, 8, 9, 10, 11, 12, 13)";
			}

			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Product _product = new Product();
					_product.setProductNo(rset.getString("product_no"));
					_product.setProductName(rset.getString("product_name"));
					_product.setProductPrice(rset.getInt("product_price"));
					_product.setThumbnailFilename(rset.getString("thumbnail_filename"));
					
					list.add(_product);
				}
			}
			
		} catch (SQLException e) {
			throw new ProductException("결과 오류", e);
		}
		return list;
	}
	/**
	 * jooh end
	 */

	
}
