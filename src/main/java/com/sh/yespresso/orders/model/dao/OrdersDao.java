package com.sh.yespresso.orders.model.dao;

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

import com.sh.yespresso.orders.model.dto.Orders;
import com.sh.yespresso.orders.model.exception.OrdersException;

public class OrdersDao {
	private Properties prop = new Properties();

	public OrdersDao() {
		System.out.println("path 가져오기 시도 중");
		String path = OrdersDao.class.getResource("/sql/orders/orders-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[ordersDao query load 완료!] " + prop);
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
	 * 
	 * @throws OrdersException
	 */
	// 마이페이지 - 주문리스트 보기.
	public List<Orders> selectMyOrdersList(Connection conn, Map<String, Object> param, String orderMemberId) {
		String sql = prop.getProperty("selectMyOrdersList");
		List<Orders> myOrdersList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, orderMemberId);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Orders orders = handleOrdersResultSet(rset);
					myOrdersList.add(orders);
				}
			}
		} catch (SQLException e) {
			throw new OrdersException("주문 목록 조회 오류!", e);
		}
		return myOrdersList;
	}

	private Orders handleOrdersResultSet(ResultSet rset) {
		// 1.26 여기 만들기.
		return null;
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
