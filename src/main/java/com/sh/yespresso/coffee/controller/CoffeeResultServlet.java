package com.sh.yespresso.coffee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.coffee.model.service.CoffeeService;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.service.ProductService;

/**
 * Servlet implementation class CoffeeResultServlet
 */
@WebServlet("/coffee/coffeeResult")
public class CoffeeResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService productService = new ProductService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Object arr = request.getParameter("dataArr");
//		
//		System.out.println(arr + "fdfssd");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//1. 사용자 입력값 처리
		String[] ajaxMsg = request.getParameterValues("dataArr");
		int size = ajaxMsg.length;
		for(int i=0; i<size; i++) {
	        System.out.println("JSP에서 받은 MSG : "+ajaxMsg[i]);
	    }
	        
	    String resultMsg = "AJAX Success";
	    System.out.println("Controller에서 보낸 MSG : "+ resultMsg);
	        
	    //2.업무로직
		
			String type;
			String aroma;
			int acidity;
			int roasting;
			String cupsize;
			
			if(ajaxMsg[0].equals("1")) {
				type = "vertuo";
			}else {
				type="original";
			}
			
			if(ajaxMsg[1].equals("1")) {
				aroma = "cocoa";
			}else if(ajaxMsg[1].equals("2")) {
				aroma="biscuit";
			}else {
				aroma="fruit";
			}
			
			if(ajaxMsg[2].equals("1")) {
				acidity=1;
			}else {
				acidity = 3;
			}
			
			if(ajaxMsg[3].equals("1")) {
				roasting = 6;
			}else {
				roasting = 8;
			}
			
			if(ajaxMsg[1].equals("1")) {
				cupsize = "s";
			}else if(ajaxMsg[1].equals("2")) {
				cupsize="m";
			}else {
				cupsize="l";
			}
		    
//			Product product = coffeeService.selectResult(ajaxMsg);
			
		   
			System.out.println(ajaxMsg.getClass().getName());
			int product = productService.selectResult(ajaxMsg);
			System.out.println(product);
	}


}
