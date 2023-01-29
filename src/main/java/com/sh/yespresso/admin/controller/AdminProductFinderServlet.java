package com.sh.yespresso.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class AdminProductFinderServlet
 */
@WebServlet("/admin/adminProductFinder")
public class AdminProductFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 - searchType, searchKeyword
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param = " + param);
		
		// 2. 업무로직 - 제품 검색 
		List<Product> products = productService.searchProduct(param);
		System.out.println("products = " + products);
		
		// 3. forward (view단처리)
		request.setAttribute("products", products);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminProductList.jsp")
			.forward(request, response);
	}

}
