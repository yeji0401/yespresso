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

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/product/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int limit = 12;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e){}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		
		// 1. db에서 목록조회 (페이징)
//		List<Product> productList = productService.selectProductList(param);
		
		// 2. 페이지바
		
		// 3. view단 위임	
		request.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
		.forward(request, response);	
	}


}
