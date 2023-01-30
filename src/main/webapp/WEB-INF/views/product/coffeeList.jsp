<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> coffeeList = (List<Product>) request.getAttribute("coffeeList");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<div class="productList-container">
	<!-- 상단 꾸미기 영역 -->
	<div class="addition">
		<div class="add-img">
			<img src="<%= request.getContextPath() %>/images/product/productList_coffee.jpg" style="width: 1200px;" />
		</div>
		<div class="add-text">
			<h2>COFFEE</h2>
		</div>
	</div>
	<div class="category-list">
		<ul>
			<li class="pad1">
			<span>필터</span>
			<span class="material-symbols-outlined">tune</span>
			</li>
			<li class="pad1"><span>버츄오</span></li>
			<li class="pad1"><span>오리지널</span></li>
			<li class="pad2"><span>낮은가격순</span></li>
			<li class="pad2"><span>높은강도순</span></li>
			<li class="pad1"><span>정렬</span></li>
		</ul>
	</div>
	<!-- 상품리스트 -->
	
<%
	for(int i = 0; i < coffeeList.size(); i++){
		coffeeList.get(i); // 첫번째 제품
	}

%>


	<div class="product-list">
		<table id="coffee-list">
			<tbody>
<%
	for(Product coffee : coffeeList){
%>	
				<tr>
					<td>
						<div class="prod-cont">
							<div class="thumbnail">
								<img src="<%= request.getContextPath() %>/upload/product/<%= coffee.getThumbnailFilename() %>" alt="" style="width: 120px;"/>
							</div>
							<div class="prod-name"><%= coffee.getProductName() %></div>
							<p class="price"><%= coffee.getProductPrice() %>원</p>
						</div>
					</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
				
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
				
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
<%
	}
%>
			</tbody>			
		</table>
		
	</div>
	<div id="pagebar">
	
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>