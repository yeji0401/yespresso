package com.sh.yespresso.coffee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.yespresso.product.model.dto.Aroma;
import com.sh.yespresso.product.model.dto.CupSize;
import com.sh.yespresso.product.model.dto.Product;
import com.sh.yespresso.product.model.dto.Type;
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
		
			Type type;
			Aroma aroma;
			int acidity;
			int roasting;
			CupSize cupsize;
			
			if(ajaxMsg[0].equals("1")) {
				type = Type.vertuo;
			}else {
				type= Type.original;
			}
			
			if(ajaxMsg[1].equals("1")) {
				aroma = Aroma.cocoa;
			}else if(ajaxMsg[1].equals("2")) {
				aroma = Aroma.biscuit;
			}else {
				aroma= Aroma.fruit;
			}
			
			if(ajaxMsg[2].equals("1")) {
				acidity=1;
			}else {
				acidity = 2;
			}
			
			if(ajaxMsg[3].equals("1")) {
				roasting = 1;
			}else {
				roasting = 2;
			}
			
			if(ajaxMsg[4].equals("1")) {
				//cupsize = "s";
				cupsize = CupSize.S;
			}else if(ajaxMsg[4].equals("2")) {
				//cupsize="m";
				cupsize = CupSize.M;
			}else {
				//cupsize="l";
				cupsize = CupSize.L;
			}
		    
			 
	        
			
			List<Product> list = productService.selectResult(new Product(type, aroma, acidity, roasting, cupsize));
		
			
			//System.out.println(product);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//response.setContentType("application/x-json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
			
	}


}
