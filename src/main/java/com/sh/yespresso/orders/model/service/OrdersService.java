package com.sh.yespresso.orders.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.orders.model.dao.OrdersDao;
import com.sh.yespresso.orders.model.dto.Orders;

public class OrdersService {
	private OrdersDao ordersDao = new OrdersDao();

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
	// 나의 주문 리스트보기
	public List<Orders> selectMyOrdersList(Map<String, Object> param, String orderMemberId) {
		Connection conn = getConnection();
		List<Orders> myOrdersList = ordersDao.selectMyOrdersList(conn, param, orderMemberId);
		close(conn);
		return myOrdersList;
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
