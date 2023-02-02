package com.sh.yespresso.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class AdminUpdateOrderStateServlet
 */
@WebServlet("/admin/adminUpdateOrderState")
public class AdminUpdateOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			// 1. 사용자 입력값 - orderNo, orderState
			String orderNo = request.getParameter("orderNo");
			String orderState = request.getParameter("orderState");

			// 2. 업무로직 - DB 해당 배송 상태 수정
			int result = memberService.updateOrderState(orderNo, orderState);

			// 2.5 사용자 메세지 전송
			session.setAttribute("msg", "배송 상태를 성공적으로 변경했습니다.");

		} catch (Exception e) {
			session.setAttribute("msg", "배송 상태 변경 중 오류가 발생했습니다.");
			e.printStackTrace();
		}

		// 3. 리다이렉트 - url변경
		response.sendRedirect(request.getContextPath() + "/admin/adminOrdersList");
	}

}
