package com.sh.yespresso.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class CoffeeDetailsServlet
 */
@WebServlet("/product/coffeeDetails")
public class CoffeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String prodNo = request.getParameter("no");
		System.out.println(prodNo);
		
		// 2. 업무로직 - product / 첨부파일 테이블 조회
		// selectOneProduct = select * from PRODUCT where PRODUCT_NO = ?
		// selectDetailByProductNo = select * from PRODUCT_DETAIL where PRODUCT_ATTACHMENT_NO = ? 
		Product product = productService.selectOneProduct(prodNo);
//		System.out.println(product);
		System.out.println("getDetails=" + product.getDetails());
		System.out.println(product.getDetails().get(0).getReProductFilename());

		// 3. view단 위임
		request.setAttribute("product", product);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeDetails.jsp").forward(request, response);
	}

}
