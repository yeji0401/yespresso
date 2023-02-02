package com.sh.yespresso.admin.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.yespresso.common.YespressoFileRenamePolicy;
import com.sh.yespresso.product.model.dto.Aroma;
import com.sh.yespresso.product.model.dto.CupSize;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.ProductCategory;
import com.sh.yespresso.product.model.dto.Type;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class AdminProductUpdateServlet
 */
@WebServlet("/admin/adminProductUpdate")
public class AdminProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 처리
		String productNo = request.getParameter("productNo");
		System.out.println(productNo);
		
		// 2. 업무로직
		Product product = productService.selectProduct(productNo);
		
		// 3. view단 처리
		request.setAttribute("product", product);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminProductUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/upload/product");
			int maxPostsize = 10 * 1024 * 1024;
			String encoding = "utf-8";
			FileRenamePolicy policy = new YespressoFileRenamePolicy();
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostsize, encoding, policy);
			
			// 1. 사용자 입력값 처리
			String _category = multiReq.getParameter("category");
			String productName = multiReq.getParameter("productName");
			int productPrice = Integer.parseInt(multiReq.getParameter("productPrice"));
			String _type = multiReq.getParameter("type");
			String _aroma = multiReq.getParameter("aroma");
			int acidity = Integer.parseInt(multiReq.getParameter("acidity"));
			int roasting = Integer.parseInt(multiReq.getParameter("roasting"));
			String _cupSize = multiReq.getParameter("cupSize");
			int productStock = Integer.parseInt(multiReq.getParameter("productStock"));
		
			ProductCategory category = _category != null ? ProductCategory.valueOf(_category) : null;
			Type type = _type != null ? Type.valueOf(_type) : null;
			Aroma aroma = _aroma != null ? Aroma.valueOf(_aroma) : null;
			CupSize cupSize = _cupSize != null ? CupSize.valueOf(_cupSize) : null;
			
			Product product = new Product();
			product.setProductName(productName);
			product.setProductCategory(category);
			product.setType(type);
			product.setAroma(aroma);
			product.setAcidity(acidity);
			product.setRoasting(roasting);
			product.setCupSize(cupSize);
			product.setProductPrice(productPrice);
			product.setProductStock(productStock);
			
			// 썸네일 처리
			if(multiReq.getFile("upFile") != null) {
				product.setThumbnailFilename("upFile");
			}
			
			// 2. 업무 로직
			int result = productService.updateProduct(product);
			
			// 3. 응답 처리
			response.sendRedirect(request.getContextPath() + "/admin/adminProductList");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "제품 수정 중 오류가 발생했습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/adminProductUpdate");
		}
	}

}
