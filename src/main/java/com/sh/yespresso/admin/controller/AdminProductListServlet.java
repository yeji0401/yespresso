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

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class AdminProductListServlet
 */
@WebServlet("/admin/adminProductList")
public class AdminProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		final int limit = 10;
		int page = 1; // 기본값
		try {
			page = Integer.parseInt(request.getParameter("page"));			
		} catch (NumberFormatException e) {
			// NumberFormatException이 발생했지만, 기본값은 1로 처리.
		}
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		// a. content 영역
		List<Product> products = productService.selectAllProduct(param);
		System.out.println(products);
		// b. pagebar 영역
		int totalCount = productService.selectTotalCount();
		System.out.println(totalCount);
		String url = request.getRequestURI(); // /yespresso/admin/adminProductList
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		// 3. view단 처리
		request.setAttribute("products", products);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/adminProductList.jsp")
		.forward(request, response);	
	}

}
