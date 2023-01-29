package com.sh.yespresso.product.controller;

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

		final int limit = 12; 
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(NumberFormatException e) {}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		
		// 1. DB에서 목록조회
		List<Product> coffeeList = productService.selectCoffeeList(param);
		System.out.println(coffeeList);
		
		// 2. 페이지바
		int totalCount = productService.getTotalCntById("CO");
		System.out.println(totalCount);
		
		String url = request.getRequestURI(); // yespresso/product/productList 
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);

		// 3. view단 위임
		request.setAttribute("coffeeList", coffeeList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeList.jsp").forward(request, response);
	}

	
}