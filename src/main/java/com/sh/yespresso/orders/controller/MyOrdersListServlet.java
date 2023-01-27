package com.sh.yespresso.orders.controller;

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
 * Servlet implementation class myOrdersListServlet
 */
@WebServlet("/myPage/myOrdersList")
public class MyOrdersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
				final int limit = 5;
				int page = 1;
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException e) {}
				
				Map<String, Object> param = new HashMap<>();
				String orderMemberId = request.getParameter("order_member_id");
				param.put("page", page);
				param.put("limit", limit);
				
				// 2. 업무로직
				// a. db에서 목록조회(페이징)
				List<Orders> myOrdersList = ordersService.selectMyOrdersList(param, orderMemberId);
				System.out.println(myOrdersList);
				// b. 페이지바
				int totalCount = ordersService.selectTotalCount(); // select count(*) from board
				System.out.println(totalCount);
				
				String url = request.getRequestURI();
				String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
				System.out.println(pagebar);
				
				// 3. view단 위임.
				request.setAttribute("myOrdersList", myOrdersList);
				request.setAttribute("pagebar", pagebar);
				request.getRequestDispatcher("/WEB-INF/views/myPage/MyOrdersList.jsp").forward(request, response);

	}

}
