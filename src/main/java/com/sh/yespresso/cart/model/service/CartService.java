package com.sh.yespresso.cart.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.yespresso.cart.model.dao.CartDao;
import com.sh.yespresso.cart.model.dto.Cart;
import com.sh.yespresso.cart.model.dto.CartProduct;

public class CartService {

	private CartDao cartDao = new CartDao();

	/**
	 * hj start
	 */
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
	 * 
	 * @param orderMemberId
	 */
	public List<CartProduct> selectMyCartList(String cartMemberId) {
		Connection conn = getConnection();
		List<CartProduct> myCartList = cartDao.selectMyCartList(conn, cartMemberId);

		close(conn);
		return myCartList;
	}
	

	public int deleteMyCartListbyProductNo(int cartProductNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// dao요청
			result = cartDao.deleteMyCartListbyProductNo(conn, cartProductNo);
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
	 * awon end
	 */


	/**
	 * jooh start
	 */
	/**
	 * jooh end
	 */

}
