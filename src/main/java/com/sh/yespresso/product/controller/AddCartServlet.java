package com.sh.yespresso.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/product/addCart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자입력값
		String memberId = request.getParameter("memberId");
		String pdNo = request.getParameter("pdNo");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		System.out.println(memberId);
		System.out.println(pdNo);
		System.out.println(amount);
	}

}
