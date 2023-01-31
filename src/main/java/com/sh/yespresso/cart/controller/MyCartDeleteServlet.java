package com.sh.yespresso.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.cart.model.service.CartService;

/**
 * Servlet implementation class MyCartDeleteServlet
 */
@WebServlet("/cart/deleteMyCartList")
public class MyCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int cartProductNo = Integer.parseInt(request.getParameter("cartProductNo"));
		System.out.println("cartProductNo = " + cartProductNo);

		int result = cartService.deleteMyCartListbyProductNo(cartProductNo);

		// 3. redirect : /cart/cart
		request.getSession().setAttribute("msg", "품목을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/cart/cart");

	}

}
