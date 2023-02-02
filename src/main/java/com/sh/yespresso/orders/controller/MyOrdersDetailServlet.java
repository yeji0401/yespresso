package com.sh.yespresso.orders.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.member.model.dto.Member;
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
		// 1. 사용자입력값 처리 : id 가져오기
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String orderMemberId = loginMember.getMemberId();
		String orderNo = request.getParameter("orderNo");
		
		// 2. 업무로직
		List<OrderDetail> myOrdersDetail = ordersService.selectMyOrdersDetail("orderNo");
		
		// 개행문자 변환처리
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("orderMemberId", orderMemberId);
		request.setAttribute("myOrdersDetail", myOrdersDetail);
		
		// jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/views/myPage/myOrdersDetail.jsp")
			.forward(request, response);
	}

}
