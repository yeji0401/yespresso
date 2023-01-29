package com.sh.yespresso.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.orders.model.dto.Orders;
import com.sh.yespresso.orders.model.service.OrdersService;

/**
 * Servlet implementation class AdminOrdersListServlet
 */
@WebServlet("/admin/adminOrdersList")
public class AdminOrdersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		final int limit = 5;
		int page = 1; // 기본값
		try {
			page = Integer.parseInt(request.getParameter("page"));			
		} catch (NumberFormatException e) {
			// NumberFormatException이 발생했지만, 기본값은 1로 처리.
		}
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		// a. content 영역
		List<Orders> orders = ordersService.selectAllOrders(param);
		System.out.println(orders);
		// b. pagebar 영역
		int totalCount = ordersService.selectTotalCount();
		System.out.println(totalCount);
		String url = request.getRequestURI(); // /yespresso/admin/adminOrdersList
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		// 3. view단 처리
		request.setAttribute("orders", orders);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/adminOrdersList.jsp")
		.forward(request, response);	
	}

}
