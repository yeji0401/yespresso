package com.sh.yespresso.cart.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.cart.model.dto.Cart;
import com.sh.yespresso.cart.model.service.CartService;

/**
 * Servlet implementation class MyCartDeleteServlet
 */
@WebServlet("/cart/cartDelete")
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

		int result = cartService.deleteCart(cartProductNo);

		// 3. redirect : /board/boardList
		request.getSession().setAttribute("msg", "품목을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/cart/cart");

	}

}
