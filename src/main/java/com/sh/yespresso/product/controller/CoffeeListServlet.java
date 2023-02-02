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
		// 1. 업무로직 - 전체게시물수 조회 -> 전체페이지수 구하기
		// 전체 게시물수 조회
		int totalCount = productService.getTotalCntById("CO");
		// 전체 페이지수 조회
		int limit = 12;
		int totalPage = (int) Math.ceil((double) totalCount / limit);
		
		// 2. 응답처리
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeList.jsp") // jsp를 view단으로 사용해 html응답
			.forward(request, response);
	}

	
}