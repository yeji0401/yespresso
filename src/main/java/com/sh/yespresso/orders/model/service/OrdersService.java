package com.sh.yespresso.orders.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.orders.model.dao.OrdersDao;
import com.sh.yespresso.orders.model.dto.OrderDetail;
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
	public List<Orders> selectAllOrders(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Orders> orders = ordersDao.selectAllOrders(conn, param);
		close(conn);
		return orders;
	}
	
	public List<Orders> searchOrders(Map<String, String> param) {
		Connection conn = getConnection();
		List<Orders> orders = ordersDao.searchOrders(conn, param);
		close(conn);
		return orders;
	}
	/**
	 * yeji end
	 */

	/**
	 * awon start
	 */
	// 나의 주문 내역 리스트보기
	public List<Orders> selectMyOrdersList(Map<String, Object> param, String orderMemberId) {
		Connection conn = getConnection();
		List<Orders> myOrdersList = ordersDao.selectMyOrdersList(conn, param, orderMemberId);
		close(conn);
		return myOrdersList;
	}

	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = ordersDao.selectTotalCount(conn);
		close(conn);
		return totalCount; 
	}
	
	// 나의 주문 상세 내역 보기
	public List<OrderDetail> selectMyOrderDetail(String orderMemberId) {
		Connection conn = getConnection();
		List<OrderDetail> myOrderDetail = ordersDao.selectMyOrderDetail(conn, orderMemberId);
		close(conn);
		return myOrderDetail;
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
