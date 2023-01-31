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

import com.sh.yespresso.orders.model.dto.Orders;
import com.sh.yespresso.orders.model.service.OrdersService;

/**
 * Servlet implementation class AdminOrdersFinderServlet
 */
@WebServlet("/admin/adminOrdersFinder")
public class AdminOrdersFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 - searchType, searchKeyword
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param = " + param);
		
		// 2. 업무로직 - 주문 검색 
		List<Orders> orders = ordersService.searchOrders(param);
		System.out.println("orders = " + orders);
		
		// 3. forward (view단처리)
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminOrdersList.jsp")
			.forward(request, response);
	}

}
