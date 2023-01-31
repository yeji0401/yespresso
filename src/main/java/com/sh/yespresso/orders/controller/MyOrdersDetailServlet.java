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


@WebServlet("/myPage/myOrdersDetail")
public class MyOrdersDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdersService ordersService = new OrdersService();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderDetail> myOrdersDetail = ordersService.selectMyOrdersDetail("orderNo");
		String orderNo = request.getParameter("orderNo");
		// jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/views/myPage/myOrdersDetail.jsp")
			.forward(request, response);
	}

}
