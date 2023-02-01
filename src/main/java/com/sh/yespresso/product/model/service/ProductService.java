package com.sh.yespresso.product.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.product.model.dao.ProductDao;
import com.sh.yespresso.product.model.dto.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	/**
	 * **********************   hj start   **********************
	 */
	/* DQL 페이지에 해당하는 카테고리별 제품 리스트 불러오기 */
	// 1. 커피 제품 리스트
	public List<Product> selectCoffeeList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> coffeeList = productDao.selectCoffeeList(conn, param);
		close(conn);
		return coffeeList;
	}
	// 2. 머신 제품 리스트
	public List<Product> selectMachineList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> machineList = productDao.selectMachineList(conn, param);
		close(conn);
		return machineList;
	}
	
	// 3. 악세사리 제품 리스트 
	public List<Product> selectAccList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> accList = productDao.selectAccList(conn, param);
		close(conn);
		return accList;
	}
	
	// DQL categoryId로 카테고리별 제품수 구하기
	public int getTotalCntById(String categoryId) {
		Connection conn = getConnection();
		int totalCount = productDao.getTotalCntById(conn, categoryId);
		close(conn);
		return totalCount;
	}
	
	/**
	 * *********************   hj end   **********************
	 */




	/**
	 * yeji start
	 */
	public List<Product> selectAllProduct(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> products = productDao.selectAllProduct(conn, param);
		close(conn);
		return products;
	}
	
	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = productDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	
	public List<Product> searchProduct(Map<String, String> param) {
		Connection conn = getConnection();
		List<Product> products = productDao.searchProduct(conn, param);
		close(conn);
		return products;
	}
	
	public int insertProduct(Product product) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.insertProduct(conn, product);
			commit(conn);			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
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
	/**
	 * jooh end
	 */

}
