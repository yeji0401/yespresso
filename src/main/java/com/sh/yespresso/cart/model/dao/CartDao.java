package com.sh.yespresso.cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.yespresso.cart.model.dto.CartProduct;
import com.sh.yespresso.cart.model.exception.CartException;

public class CartDao {

	private Properties prop = new Properties();

	public CartDao() {
		String path = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CartDao 쿼리 로드 완료! - " + prop);
	}

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
	 */
	public List<CartProduct> selectMyCartList(Connection conn, String cartMemberId) {
		String sql = prop.getProperty("selectMyCartList");
		List<CartProduct> myCartList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cartMemberId);

			try (ResultSet rset = pstmt.executeQuery()) {

				while (rset.next()) {
					CartProduct cart = handleCartResultSet(rset);
					myCartList.add(cart);
					System.out.println(myCartList);
				}
			}

		} catch (SQLException e) {
			throw new CartException("장바구니 조회 오류!", e);
		}

		return myCartList;

	}

	private CartProduct handleCartResultSet(ResultSet rset) throws SQLException {
		CartProduct cart = new CartProduct();
		cart.setCartNo(rset.getInt("cart_list_no"));
		cart.setProductName("product_name");
		cart.setProductPrice(rset.getInt("product_price"));
		cart.setCartMemberId(rset.getString("cart_list_member_id"));
		cart.setCartProductNo(rset.getString("product_no"));
		cart.setAmount(rset.getInt("amount"));
		cart.setTotalPrice(rset.getInt("total_price"));
		return cart;
	}

	public int deleteMyCartListbyProductNo(Connection conn, int cartProductNo) {
		String sql = prop.getProperty("deleteMyCartListbyProductNo"); // delete from cart where cartProductNo = ?
		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cartProductNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new CartException("품목 삭제 오류!", e);
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
