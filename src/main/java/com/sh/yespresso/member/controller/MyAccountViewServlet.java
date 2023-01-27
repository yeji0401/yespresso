package com.sh.yespresso.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyAccountViewServlet
 */
@WebServlet("/myPage/myAccountView")
public class MyAccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/views/myPage/myAccountView.jsp")
		.forward(request, response);
	}

}
