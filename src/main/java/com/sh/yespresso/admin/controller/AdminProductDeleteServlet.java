package com.sh.yespresso.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class AdminProductDeleteServlet
 */
@WebServlet("/admin/adminProductDelete")
public class AdminProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String productNo = request.getParameter("productNo");
		System.out.println("productNo = " + productNo);
		
		// 2. 업무 로직
		String saveDirectory = getServletContext().getRealPath("/upload/product");
		Product product = new Product();
		File delFile = new File(saveDirectory, product.getThumbnailFilename());
		boolean bool = delFile.delete();
		System.out.println(bool ? "파일 삭제 성공!" : "파일 삭제 실패!");
		
		int result = productService.deleteProduct(productNo);
		
		// 3. redirect
		request.getSession().setAttribute("msg", "제품을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/admin/adminProdustList");
	}

}
