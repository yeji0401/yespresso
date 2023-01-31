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

import com.sh.yespresso.orders.model.dto.OrderDetail;
import com.sh.yespresso.orders.model.dto.OrderState;
import com.sh.yespresso.orders.model.dto.Orders;
import com.sh.yespresso.orders.model.exception.OrdersException;

public class OrdersDao {
	private Properties prop = new Properties();

	public OrdersDao() {
		System.out.println("path 가져오기 시도 중");
		String path = OrdersDao.class.getResource("/sql/order/orders-query.properties").getPath();
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
		// selectMyOrdersList = select * from (select row_number() over(order by no
		// desc) rnum, b.* from orders o where order_member_id = ?) where rnum between ?
		// and ?
		String sql = prop.getProperty("selectMyOrdersList");
		List<Orders> myOrdersList = new ArrayList<>();

		int page = (int) param.get("page");
		int limit = (int) param.get("limit"); // 5

		int start = (page - 1) * limit + 1; // 1, 6, 11, 16, ...
		int end = page * limit; // 5, 10, 15, 20, ...

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, orderMemberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

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

	private Orders handleOrdersResultSet(ResultSet rset) throws SQLException {
		Orders orders = new Orders();
		orders.setOrderNo(rset.getString("order_no"));
		orders.setOrderMemberId(rset.getString("order_member_id"));
		orders.setOrderDate(rset.getDate("order_date"));
		orders.setTotalPrice(rset.getDouble("totalprice"));
		orders.setOrderState(OrderState.valueOf(rset.getString("order_state")));
		return orders;
	}

	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount"); // select count(*) from orders
		int totalCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();) {
			while (rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new OrdersException("전체 주문내역 조회 오류!", e);
		}
		return totalCount;
	}

	// 주문 상세내역 보기
	public List<OrderDetail> selectMyOrdersDetail(Connection conn, String orderNo) {
		String sql = prop.getProperty("selectMyOrdersDetail");
		List<OrderDetail> myOrdersDetail = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, orderNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					OrderDetail od = handleOrderDetailResultSet(rset);
					myOrdersDetail.add(od);
				}
			}
		} catch (Exception e) {
			throw new OrdersException("주문 상세 내역 조회 오류!", e);
		}

		return myOrdersDetail;
	}

	private OrderDetail handleOrderDetailResultSet(ResultSet rset) throws SQLException {
		OrderDetail od = new OrderDetail();
		od.setDetailProductNo(rset.getString("order_detail_no"));
		od.setOrderDetailNo(rset.getInt("order_detail_no"));
		od.setOrderDetailAmount(rset.getInt("order_detail_amount"));
		od.setOrderNo(rset.getString("order_no"));
		return od;
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
