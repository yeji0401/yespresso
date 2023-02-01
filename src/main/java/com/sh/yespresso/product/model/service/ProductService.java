package com.sh.yespresso.product.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.product.model.dao.ProductDao;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.ProductDetail;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	/**
	 * **********************   hj start   **********************
	 */
	/* DQL 페이지에 해당하는 카테고리별 제품 리스트 불러오기 */
	// 1. 커피 제품 리스트
	public List<Product> selectCoffeeList(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Product> coffeeList = productDao.selectCoffeeList(conn, param);
		close(conn);
		return coffeeList;
	}
	// 2. 머신 제품 리스트
	public List<Product> selectMachineList(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Product> machineList = productDao.selectMachineList(conn, param);
		close(conn);
		return machineList;
	}
	
	// 3. 악세사리 제품 리스트 
	public List<Product> selectAccList(Map<String, Integer> param) {
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
	
	// DQL product_no로 제품 하나 가져오기
	public Product selectOneProduct(String prodNo) {
		Connection conn = getConnection();
		Product product = productDao.selectOneProduct(conn, prodNo);
		List<ProductDetail> attachments = productDao.selectDetailByProductNo(conn, prodNo);
		product.setDetails(attachments);
		close(conn);
		return product;
	}
	
	/**
	 * *********************   hj end   **********************
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
