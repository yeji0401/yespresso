package com.sh.yespresso.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class CoffeeListServlet
 */
@WebServlet("/product/coffeeList")
public class CoffeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> coffeeList = productService.selectAllProduct("CO");
		System.out.println(coffeeList);

		// view단 위임
		request.setAttribute("coffeeList", coffeeList);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeList.jsp").forward(request, response);
	}

	
}