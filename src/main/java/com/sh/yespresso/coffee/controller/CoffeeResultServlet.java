package com.sh.yespresso.coffee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.coffee.model.service.CoffeeService;

/**
 * Servlet implementation class CoffeeResultServlet
 */
@WebServlet("/coffee/coffeeResult")
public class CoffeeResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CoffeeService coffeeService = new CoffeeService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object arr = request.getParameter("dataArr");
		
		System.out.println(arr + "fdfssd");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
