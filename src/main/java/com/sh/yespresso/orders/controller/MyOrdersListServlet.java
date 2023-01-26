package com.sh.yespresso.orders.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.orders.model.service.OrdersService;

/**
 * Servlet implementation class myOrdersListServlet
 */
@WebServlet("/myPage/myOrders/myOrdersList")
public class MyOrdersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/myPage/myOrders/myOrdersList.jsp").forward(request, response);

	}

}
