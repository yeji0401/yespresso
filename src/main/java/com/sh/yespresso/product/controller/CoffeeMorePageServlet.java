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

import com.google.gson.Gson;
import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class CoffeeMorePageServlet
 */
@WebServlet("/coffee/morePage")
public class CoffeeMorePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = 12;

		int start = (page-1) * limit + 1;
		int end = page * limit;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		// 2. 업무로직
		List<Product> coffeeList = productService.selectCoffeeList(param);
//		System.out.println("coffeeList= " + coffeeList);
		
		// 3. 응답처리 - json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(coffeeList, response.getWriter());
	}

}
