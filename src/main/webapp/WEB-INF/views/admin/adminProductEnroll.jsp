<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/productEnroll.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<section id="product-enroll-container">
	<h2>제품 등록</h2>
	<form 
		name="ProductEnrollFrm"
		action="<%=request.getContextPath() %>/admin/adminProductEnroll" 
		enctype="multipart/form-data"
		method="post">
		<table id="tbl-product-enroll">
		<tr>
			<th>제품명</th>
			<td><input type="text" name="productName" required /></td>
			<th>제품등록일</th>
			<td><input type="text" name="productDate" readonly /></td>
		</tr>
		<tr>
			<th>제품번호</th>
			<td><input type="text" name="productNo" readonly /></td>
			<th>제품타입</th>
			<td>
				<input type="radio" name="type" value="vertuo" required/>
				<label for="vertuo">버츄오</label>
				<input type="radio" name="type" value="original" />
				<label for="original">오리지널</label>
			</td>			
		</tr>
		<tr>
			<th>카테고리번호</th>
			<td>
				<input type="radio" name="category" value="CO" required/>
				<label for="CO">커피</label>
				<input type="radio" name="category" value="MA" />
				<label for="MA">머신</label>
				<input type="radio" name="category" value="AC" />
				<label for="AC">액세서리</label>
			</td>
			<th>아로마</th>
			<td>
				<input type="radio" name="aroma" value="cocoa" />
				<label for="cocoa">코코아</label>
				<input type="radio" name="aroma" value="biscuit" />
				<label for="biscuit">비스킷</label>
				<input type="radio" name="aroma" value="fruit" />
				<label for="fruit">과일</label>
			</td>
		</tr>
		<tr>
			<th>산미감</th>
			<td>
				<input type="number" name="acidity" max = "5" min ="1" value="1" />			
			</td>
			<th>로스팅</th>
			<td><input type="number" name="roasting" max = "13" min ="1" value="1" /></td>
		</tr>
		<tr>
			<th>컵사이즈</th>
			<td>
				<input type="radio" name="cupSize" value="S" />
				<label for="S">S</label>
				<input type="radio" name="cupSize" value="M" />
				<label for="M">M</label>
				<input type="radio" name="cupSize" value="L" />
				<label for="L">L</label>
			</td>
			<th>가격</th>
			<td>
				<input type="text" name="productPrice" required/>
			</td>

		</tr>
		<tr>
			<th>썸네일 파일</th>
			<td>			
				<input type="file" name="upFile">
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value="등록">
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