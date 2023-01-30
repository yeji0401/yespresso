<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<section id="product-enroll-container">
	<h2>제품 관리 - 제품 등록</h2>
	<form 
		name="ProductEnrollFrm"
		action="<%=request.getContextPath() %>/admin/adminProductEnroll" 
		enctype="multipart/form-data"
		method="post">
		<table id="tbl-product-enroll">
		<tr>
			<th>제품명</th>
			<td><input type="text" name="prduct_name" required /></td>
			<th>제품등록일</th>
			<td><input type="text" name="product_date" /></td>
		</tr>
		<tr>
			<th>제품번호</th>
			<td><input type="text" name="product_no" requried/></td>
			<th>카테고리번호</th>
			<td><input type="text" name="category_no"/></td>
		</tr>
		<tr>
			<th>아로마</th>
			<td><input type="radio" name="aroma" /></td>
		</tr>
		</table>
	</form>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>