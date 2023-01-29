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
			<img src="/yespresso/images/productList_coffee.jpg" style="width: 1200px;" />
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
	for(Product co : coffeeList){

%>	
	<div class="product-list">
			<ul>
				<li style="width:25%;">
					<div class="productList-cont">
						<div class="product-img"></div>
						<div class="product-info">
							<a href="">
								<span class="product_name"><%= co.getProductName() %></span>
							</a>
							<span class="product_price"><%= co.getProductPrice() %>원</span>
						</div>
					</div>
				</li>
			</ul>
	</div>
<%
	}
%>

</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>