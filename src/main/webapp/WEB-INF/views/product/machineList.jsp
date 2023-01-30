<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> machineList = (List<Product>) request.getAttribute("machineList");
%>

<%
	for(Product machine : machineList){
%>	
						<div class="prod-cont">
							<div class="thumbnail">
								<img src="<%= request.getContextPath() %>/upload/product/<%= machine.getThumbnailFilename() %>" alt="" style="width: 120px;"/>
							</div>
							<div class="prod-name"><%= machine.getProductName() %></div>
							<p class="price"><%= machine.getProductPrice() %>원</p>
						</div>

<%
	}
%>