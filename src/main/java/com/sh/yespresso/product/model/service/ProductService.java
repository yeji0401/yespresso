package com.sh.yespresso.product.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.yespresso.product.model.dao.ProductDao;
import com.sh.yespresso.product.model.dto.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	/**
	 * hj start
	 */
	// 제품 LIST 반환 메소드
	// 매개인자로 제품카테고리ID 받아옴 (CO, MA, AC)
	public List<Product> selectAllProduct(String categoryID) {
		Connection conn = getConnection();
		List<Product> productList = productDao.selectAllProduct(conn, categoryID);
		close(conn);
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
