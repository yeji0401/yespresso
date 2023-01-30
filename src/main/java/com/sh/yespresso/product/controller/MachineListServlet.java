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
 * Servlet implementation class MachineListServlet
 */
@WebServlet("/product/machineList")
public class MachineListServlet extends HttpServlet {
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
		
		List<Product> machineList = productService.selectMachineList(param);
		
		System.out.println(machineList);
		
		int totalCount = productService.getTotalCntById("MA");
		
		String url = request.getRequestURI(); // yespresso/product/productList 
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		request.setAttribute("machineList", machineList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product/machineList.jsp").forward(request, response);
	}

}
