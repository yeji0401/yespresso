package com.sh.yespresso.coffee.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.sh.yespresso.coffee.model.dao.CoffeeDao;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.ProductEntity;

public class CoffeeService {
	private CoffeeDao coffeeDao = new CoffeeDao();
	
	public int selectResult(Product product) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = coffeeDao.selectResult(conn, product);
			commit(conn);			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
}
