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



///////////////

<%
	for(int i = 0; i < coffeeList.size(); i++){
		if(i >= 0 && i <= 3){
%>
						<td>
							<div class="prod-cont">
								<div class="thumbnail">
									<img src="<%= request.getContextPath() %>/upload/product/<%= coffeeList.get(i).getThumbnailFilename() %>" alt="" style="width: 120px;"/>
								</div>
								<div class="prod-name"><%= coffeeList.get(i).getProductName() %></div>
								<p class="price"><%= coffeeList.get(i).getProductPrice() %>원</p>
							</div>
						</td>
<%
		}
%>
					</tr>
<%	
		else if (i >= 4 && i <= 7){
%>
		
				<tr>
					<td>
						<div class="prod-cont">
							<div class="thumbnail">
								<img src="<%= request.getContextPath() %>/upload/product/<%= coffeeList.get(i).getThumbnailFilename() %>" alt="" style="width: 120px;"/>
							</div>
							<div class="prod-name"><%= coffeeList.get(i).getProductName() %></div>
							<p class="price"><%= coffeeList.get(i).getProductPrice() %>원</p>
						</div>
					</td>
				</tr>
<%				
		} else {
%>	

				<tr>
					<td>
						<div class="prod-cont">
							<div class="thumbnail">
								<img src="<%= request.getContextPath() %>/upload/product/<%= coffeeList.get(i).getThumbnailFilename() %>" alt="" style="width: 120px;"/>
							</div>
							<div class="prod-name"><%= coffeeList.get(i).getProductName() %></div>
							<p class="price"><%= coffeeList.get(i).getProductPrice() %>원</p>
						</div>
					</td>
				</tr>
<% 		
		}
	}
%>