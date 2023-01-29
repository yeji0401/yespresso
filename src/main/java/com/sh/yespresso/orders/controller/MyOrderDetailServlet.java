package com.sh.yespresso.orders.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.orders.model.dto.OrderDetail;
import com.sh.yespresso.orders.model.service.OrdersService;

/**
 * Servlet implementation class MyOrderDetailServlet
 */
@WebServlet("/myPage/myOrderDetail")
public class MyOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdersService ordersService = new OrdersService();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderDetail> myOrderDetail = ordersService.selectMyOrderDetail("orderMemberId");
		String orderMemberId = request.getParameter("orderMemberId");
		// jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/views/myPage/myOrderDetail.jsp")
			.forward(request, response);
	}

}
