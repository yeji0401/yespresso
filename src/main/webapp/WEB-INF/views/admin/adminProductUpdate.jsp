<%@page import="com.sh.yespresso.product.model.dto.ProductCategory"%>
<%@page import="com.sh.yespresso.product.model.dto.CupSize"%>
<%@page import="com.sh.yespresso.product.model.dto.Aroma"%>
<%@page import="com.sh.yespresso.product.model.dto.Type"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Product product = (Product) request.getAttribute("product");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/product.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<section id="product-container">
	<h2>제품 수정</h2>
	<form 
		name="ProductUpdateFrm"
		action="<%=request.getContextPath() %>/admin/adminProductUpdate" 
		enctype="multipart/form-data"
		method="post">
		<table id="tbl-product-update">
		<tr>
			<th>제품번호</th>
			<td><input type="text" name="productNo" value="<%= product.getProductNo() %>" readonly /></td>
			<th>카테고리번호</th>
			<td>
				<input type="radio" name="category" value="CO" <%= product.getProductCategory() == ProductCategory.CO ? "checked" : "" %> required/>
				<label for="CO">커피</label>
				<input type="radio" name="category" value="MA" <%= product.getProductCategory() == ProductCategory.MA ? "checked" : "" %>/>
				<label for="MA">머신</label>
				<input type="radio" name="category" value="AC" <%= product.getProductCategory() == ProductCategory.AC ? "checked" : "" %>/>
				<label for="AC">액세서리</label>
			</td>

		</tr>
		<tr>
			<th>제품명</th>
			<td><input type="text" name="productName" value="<%= product.getProductName() %>" required /></td>
			<th>가격</th>
			<td><input type="text" name="productPrice" value="<%= product.getProductPrice() %>" /></td>
			
		</tr>
		<tr>
			<th>재고</th>
			<td><input type="number" name="productStock" min="1" max="100" value="<%= product.getProductStock() %>"/></td>
			<th>제품등록일</th>
			<td><input type="text" name="productDate" value="<%= product.getProductDate() %>" readonly /></td>
		</tr>
		<tr>
			<th>제품타입</th>
			<td>
				<input type="radio" name="type" value="vertuo" <%= product.getType() == Type.vertuo ? "checked" : "" %> required/>
				<label for="vertuo">버츄오</label>
				<input type="radio" name="type" value="original" <%= product.getType() == Type.original ? "checked" : "" %> />
				<label for="original">오리지널</label>
			</td>
			<th>아로마</th>
			<td>
				<input type="radio" name="aroma" value="cocoa" <%= product.getAroma() == Aroma.cocoa ? "checked" : "" %>/>
				<label for="cocoa">코코아</label>
				<input type="radio" name="aroma" value="biscuit" <%= product.getAroma() == Aroma.biscuit ? "checked" : "" %>/>
				<label for="biscuit">비스킷</label>
				<input type="radio" name="aroma" value="fruit" <%= product.getAroma() == Aroma.fruit ? "checked" : "" %>/>
				<label for="fruit">과일</label>
			</td>
		</tr>
		<tr>
			<th>산미감</th>
			<td>
				<input type="number" name="acidity" max = "5" min ="1" value="<%= product.getAcidity() %>" />			
			</td>
			<th>로스팅</th>
			<td><input type="number" name="roasting" max = "13" min ="1" value="<%= product.getRoasting() %>" /></td>
		</tr>
		<tr>
			<th>컵사이즈</th>
			<td>
				<input type="radio" name="cupSize" value="S" <%= product.getCupSize() == CupSize.S ? "checked" : "" %>/>
				<label for="S">S</label>
				<input type="radio" name="cupSize" value="M" <%= product.getCupSize() == CupSize.M ? "checked" : "" %>/>
				<label for="M">M</label>
				<input type="radio" name="cupSize" value="L" <%= product.getCupSize() == CupSize.L ? "checked" : "" %>/>
				<label for="L">L</label>
			</td>
			<th>썸네일 파일</th>
			<td>			
				<img src="<%= request.getContextPath() %>/upload/product/<%= product.getThumbnailFilename() %>" alt="" style="width: 50px;"/>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value="수정">
			</td>			
		</tr>
		</table>
	</form>
</section>
<script>
document.productEnrollFrm.onsubmit = (e) => {
	const productName = e.target.productName;
	const productPrice = e.target.productPrice;
	
	// 제품명을 작성하지 않은 경우 폼 제출 불가
	if(!/^.+$/.test(productName.value)){
		alert("제품명을 작성해 주세요.");
		title.select();
		return false;
	}
	
	// 가격은 무조건 숫자 값만
	if(!/^[0-9]+$/.test(productPrice.value)){
		alert("가격은 숫자만 입력 가능합니다.");
		title.select();
		return false;
	}
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>