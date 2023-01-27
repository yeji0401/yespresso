<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css" />
<div class="productList-container">
	<!-- 상단 꾸미기 영역 -->
	<div class="addition">
		<div class="add-img">
			<img src="/yespresso/images/productList_coffee.jpg" style="width: 1000px;" />
		</div>
		<div class="add-text">
			<h2>COFFEE</h2>
		</div>
	</div>
	<div class="category-list">
		<ul>
			<li><span>필터</span></li>
			<li><span>버츄오</span></li>
			<li><span>오리지널</span></li>
			<li><span>낮은가격순</span></li>
			<li><span>높은강도순</span></li>
			<li><span>정렬</span></li>
		</ul>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>